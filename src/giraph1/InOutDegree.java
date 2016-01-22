package giraph1;

import java.io.IOException;

import org.apache.giraph.Algorithm;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;


@Algorithm(
		name = "InOutDegree"
)

public class InOutDegree extends BasicComputation<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{

	
@Override
public void compute(Vertex<LongWritable, DoubleWritable, FloatWritable> vertex,
					Iterable<DoubleWritable> messages) throws IOException{
			
	if(getSuperstep() == 0){
		
		vertex.setValue(new DoubleWritable(vertex.getNumEdges()));
		sendMessageToAllEdges(vertex, new DoubleWritable(1.0));
	}
	else{
		
		double indegree = 0;
		
		for(DoubleWritable msg : messages){
			indegree++;
		}
		
		//DoubleWritable indeg = vertex.getValue();
		//DoubleWritable outdeg = vertex.getValue();
		//indeg.set(indegree);
		double out = vertex.getNumEdges();
		double inout = indegree + out ;
		vertex.setValue(new DoubleWritable(inout));
		vertex.voteToHalt();
	}
	

}	
}
