package giraph1;

import java.io.IOException;

import org.apache.giraph.Algorithm;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;


@Algorithm(
		name = "OutDegree"
)

public class OutDegree extends BasicComputation<LongWritable, LongWritable, DoubleWritable, DoubleWritable>{

	
@Override
public void compute(Vertex<LongWritable, LongWritable, DoubleWritable> vertex,
					Iterable<DoubleWritable> messages) throws IOException{
			
	/* LongWritable Vertexvalue = vertex.getValue();
	Vertexvalue.set(vertex.getNumEdges());
	vertex.setValue(Vertexvalue);
	vertex.voteToHalt();
	*/
	
	vertex.setValue(new LongWritable(vertex.getNumEdges()));
	vertex.voteToHalt();
	}	
}
