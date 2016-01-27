package giraph2;

import java.util.*;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.Algorithm;
import org.apache.giraph.conf.LongConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;
 
import java.io.IOException;



@Algorithm(
    name = "SGD",
    description = "To Detect Communities in a Undirected Graph"
)



public class SGD extends BasicComputation<
    LongWritable, DoubleWritable, FloatWritable, DoubleWritable> {

  public static int No_of_iterations = 10;
  public static double alpha = 0.005;
  public static double lambda = 0.01;
  @Override
 
  public void compute(
      Vertex<LongWritable, DoubleWritable, FloatWritable> vertex,
      Iterable<DoubleWritable> messages) throws IOException {
    if (getSuperstep() == 0) {
    
      /* double val = Math.random();
       
       val*=50;
       val = (long) val;
       vertex.setValue(new DoubleWritable(val));
      */
       if(vertex.getId().get()== getSuperstep()%2)
       {
    	   
    	   for (Edge<LongWritable, FloatWritable> edge : vertex.getEdges()) {
    	        
    	    	  double LV = vertex.getValue().get();
    	    	  
    	    	  double r2 = edge.getValue().get();
    	    	  
    	    	    int r1 = (int)r2;
    	    	  
    	    	  String f1 = String.valueOf(r1);
    	    	  
    	           f1 = f1 + "0" + String.valueOf(LV);
    	           
    	           double f2 = Double.parseDouble(f1);
    	           
    	           
    	    	  
    	         sendMessage(edge.getTargetVertexId(), new DoubleWritable(f2));
    	   }          
       }   	
    }
    else if( getSuperstep() < No_of_iterations ) 
    	{
    	double rmse = 0;
    	
    	for(DoubleWritable msg : messages)
    	{
    		double newval1 = msg.get();
    		
    		String total2 = String.valueOf(newval1);
    		
    		char r1 = total2.charAt(0);
    		
    	total2 =	total2.substring(2);
    	
    	double lv = Double.parseDouble(total2);
    	
    	int rating = Integer.parseInt(String.valueOf(r1));
    	
    	double oldval = vertex.getValue().get();
    	
    	double prediction = oldval*lv;
    	
    	prediction = max(min(prediction,5),1) - rating;
    	
    	double newval =  oldval-alpha*(lambda*oldval - prediction*lv);
    	
    	double error = newval*lv;
    	
    	error = max(min(error,5),1) - rating;
    	
    	//System.out.println("hweefbjhfv");
    	
    	vertex.setValue(new DoubleWritable(newval));
    	rmse = rmse + Math.pow(error, 2);
    	}
    	
    	aggregate(AggregratorClass.ID,new DoubleWritable(rmse));
    	
    	
    	
    	
    	
      for (Edge<LongWritable, FloatWritable> edge : vertex.getEdges()) {
        
    	  double LV = vertex.getValue().get();
    	  
    	  double r2 = edge.getValue().get();
    	  
    	  int r1 = (int)r2;
    	  
    	  String f1 = String.valueOf(r1);
    	  
           f1 = f1 + "0" + String.valueOf(LV);
           
           double f2 = Double.parseDouble(f1);
           
           
    	  
         sendMessage(edge.getTargetVertexId(), new DoubleWritable(f2));
        
         }
    	
  }
    
    vertex.voteToHalt();
  }

double min(double a1, int  b)
{
  	if(a1>b)
  		return (double)b;
  	return a1;
}

double max(double w1,int s)
{
	if(w1>s)
		return w1;
	return (double)s;
}

}