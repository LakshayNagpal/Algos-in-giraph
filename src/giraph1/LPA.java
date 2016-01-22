package giraph1;

import java.io.IOException;

import org.apache.giraph.Algorithm;
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

public class LPA extends BasicComputation<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{


@Override
public void compute(Vertex<LongWritable, DoubleWritable, FloatWritable> vertex, Iterable<DoubleWritable> messages) throws IOException{
	
	
	if(getSuperstep() == 0){
		vertex.setValue(new DoubleWritable(vertex.getId().get()));
		//sendMessageToAllEdges(vertex, new DoubleWritable(vertex.getId().get()));
		sendMessageToAllEdges(vertex, vertex.getValue());
	}
	else{
		
		int[] array = new int[100];
		
		for(int i=0;i<100;++i){
			array[i] = 0;
		}
		
		for(DoubleWritable msg : messages){
			++array[(int) msg.get()];
		}
		int maxfrequency = Integer.MIN_VALUE;
		int x = 100;
		for(int i=0;i<100;++i){
			if(array[i] > maxfrequency || (array[i] == maxfrequency && i < x)){
				maxfrequency = array[i];
				x = i;
			}
		}
		DoubleWritable currentlabel = vertex.getValue();
		DoubleWritable lastlabel = null;
		if(getSuperstep() == 1){
			lastlabel = vertex.getValue();
		}
		else{
			lastlabel = new DoubleWritable(x);
		}
		
		if(array[(int) currentlabel.get()] < array[x] && x!=(int) lastlabel.get()){
			vertex.setValue(new DoubleWritable(x));
			sendMessageToAllEdges(vertex, new DoubleWritable(x));
		}
		vertex.voteToHalt();
	}
	
}
}
