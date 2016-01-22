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
		name="Indegree Count"
		)

public class InDegree extends BasicComputation<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{


@Override
public void compute(Vertex<LongWritable, DoubleWritable, FloatWritable> vertex, Iterable<DoubleWritable> messages) throws IOException{

	if(getSuperstep() == 0){
		
		Iterable<Edge<LongWritable, FloatWritable>> edges = vertex.getEdges();
		for(Edge<LongWritable, FloatWritable> edge : edges){
			sendMessage(edge.getTargetVertexId(), new DoubleWritable(1.0));
		}
	}
	else{
	long Indegree = 0;
	
	for(DoubleWritable msg: messages){
		Indegree++;
	}
	
	DoubleWritable vertexvalue = vertex.getValue();
	vertexvalue.set(Indegree);
	vertex.setValue(vertexvalue);	
	vertex.voteToHalt();
	}
}
}
