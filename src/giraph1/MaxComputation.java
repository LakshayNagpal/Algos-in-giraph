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
		name="Maximum value"
		)

public class MaxComputation extends BasicComputation<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{


@Override
public void compute(Vertex<LongWritable, DoubleWritable, FloatWritable> vertex, Iterable<DoubleWritable> messages) throws IOException{
	
	boolean x = false;
	if(getSuperstep() == 0){
		
		sendMessageToAllEdges(vertex, vertex.getValue());
	}
	else{
		for(DoubleWritable msg : messages){
			if(msg.get() > vertex.getValue().get()){
				vertex.setValue(msg);
				x = true;
			}
		}
		if(x){
		sendMessageToAllEdges(vertex, vertex.getValue());
		}
		vertex.voteToHalt();
	}
	
	
	/*
	boolean changed = false;
    for (DoubleWritable message : messages) {
      if (vertex.getValue().get() < message.get()) {
        vertex.setValue(message);
        changed = true;
      }
    }
    if (getSuperstep() == 0 || changed) {
      sendMessageToAllEdges(vertex, vertex.getValue());
    }
    vertex.voteToHalt(); 
	*/
}
}
