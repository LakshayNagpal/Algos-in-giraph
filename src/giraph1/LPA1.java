package giraph1;

import java.io.IOException;

import org.apache.giraph.Algorithm;
import org.apache.giraph.edge.Edge;
//import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;


@Algorithm(
		name="Maximum value",
		description="This is the final LPA heuristic algorithm amongst LPA, LPA1 and LPAnik"
		)

public class LPA1 extends BasicComputation<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{


@Override
public void compute(Vertex<LongWritable, DoubleWritable, FloatWritable> vertex, Iterable<DoubleWritable> messages) throws IOException{
	
	
	if(getSuperstep() == 0){
		vertex.setValue(new DoubleWritable(vertex.getId().get()));
		//sendMessageToAllEdges(vertex, new DoubleWritable(vertex.getId().get()));
		sendMessageToAllEdges(vertex, vertex.getValue());
	}
	else{
		
		int[] array = new int[100];
		
		for(int i=0;i<100;++i)
			array[i] = 0;
		
		double id = vertex.getValue().get();
		int currentid = (int) id;
		
		for(DoubleWritable msg : messages){
			++array[(int) msg.get()];
		}
		
		++array[currentid];int mflabel = currentid;
		boolean change = false;
		int maxfreq = array[currentid];
		for(DoubleWritable msg : messages){
			double mes = msg.get();
			int mes1 = (int) mes;
			int currentfreq = array[mes1];
			
			if(currentfreq > maxfreq || (currentfreq == maxfreq && mes1<mflabel)){
				maxfreq = currentfreq;
				mflabel = mes1;
				change = true;
			}
		}
		
		if(mflabel!=currentid && change){
			int curr = mflabel;
			
			vertex.setValue(new DoubleWritable(curr));
			
			 for (Edge<LongWritable, FloatWritable> edge : vertex.getEdges()) {
			        
		         sendMessage(edge.getTargetVertexId(),vertex.getValue() );
		        
		         }
		}
	}
	vertex.voteToHalt();
}
}
