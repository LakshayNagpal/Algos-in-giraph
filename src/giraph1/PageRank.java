package giraph1;

import java.io.IOException;

import org.apache.giraph.Algorithm;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;


@Algorithm(
		name="PageRank",
		description="Implementing Basic PageRank Algo without damping factor"
		)

public class PageRank extends BasicComputation<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{
	

	public static int maxsupersteps = 20; // we can modify the number of supersteps and accordingly the value of vertices will change
@Override
public void compute(Vertex<LongWritable, DoubleWritable, FloatWritable> vertex, Iterable<DoubleWritable> messages) throws IOException{
	
	if(getSuperstep() < maxsupersteps){
		int outedges = vertex.getNumEdges();
		DoubleWritable rank = new DoubleWritable(vertex.getValue().get() / outedges);
		for(Edge<LongWritable, FloatWritable> edge : vertex.getEdges()){
			sendMessage(edge.getTargetVertexId(), rank);
		}
	}
	
	if(getSuperstep() >= 1){
		
		double sum = 0;
		
		for(DoubleWritable message : messages){
			sum = sum + message.get();
		}
		vertex.setValue(new DoubleWritable(sum));
	}
	
	vertex.voteToHalt();
}
}