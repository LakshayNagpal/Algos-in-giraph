package giraph1;

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
    name = "Label Propogation Algorihm",
    description = "To Detect Communities in a Undirected Graph"
)
/**
 *  @param LongWritable I  Vertex Id
 *  @param DoubleWritable V Vertex data
 *  @param FloatWritable E  Edge data
 *  @param DoubleWritable M Message data
 */
public class LPAnik extends BasicComputation<
    LongWritable, DoubleWritable, FloatWritable, DoubleWritable> {
  /** The shortest paths id */
  /**
   * @param String  key 
   * @param long    defaultValue
   * @param String  description
   */
  
 
  /**
   * Is this vertex the source id?
   *
   * @param vertex Vertex
   * @return True if the source id
   */
  /**
   * Vertex class
   * @param LongWritavle I Vertex Id
   * @param ?   V          Vertex data
   * @param ?   E          Edge data
   */
 
  @Override
  /**
   * compte(Vertex<I,V,E> vertex, Iterable<M> messages)
   */
  public void compute(
      Vertex<LongWritable, DoubleWritable, FloatWritable> vertex,
      Iterable<DoubleWritable> messages) throws IOException {
    if (getSuperstep() == 0) {
    
    //	LPAvalue.set(vertex.getId().get(),vertex.getId().get());
    	vertex.setValue(new DoubleWritable(vertex.getId().get()));
    	sendMessageToAllEdges(vertex, vertex.getValue());
    	
    }
    else 
    	{
    	//HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
    	
    	int[] hmap = new int[100];
           for(int i=0;i<100;i++)
        	    hmap[i]=0;
           
    DoubleWritable getid = vertex.getValue();
          double getid1 = getid.get();
          int currid = (int) getid1;
    	
    	for (DoubleWritable message : messages) {
	        double val = message.get();
	        
	        int val1=(int) val;
	        
	      
	          int initial= hmap[val1];
	          hmap[val1]= initial+1;
	        
    	}
    		hmap[currid]=hmap[currid]+1;
    		
    	 int mflabel=currid;int maxfreq=hmap[currid];
    	 
    	 boolean change=false;
    	 
    	for (DoubleWritable message : messages) 
    {
	        double val = message.get();
	        int val1=(int) val;
	         
	     int currfreq =  hmap[val1];
	     
	     if(currfreq>maxfreq || ( (currfreq==maxfreq) && ( val1<mflabel) ))
	     {
	          maxfreq=currfreq;mflabel=val1;
	          change=true;
	     }
	     
	}
    	if(mflabel!=currid && change)
    	{
    		 int curr1 =mflabel;
    	     
    		 double curr2 = curr1;
    		 
    		 vertex.setValue( new DoubleWritable(curr2));
    	
      for (Edge<LongWritable, FloatWritable> edge : vertex.getEdges()) {
        
         sendMessage(edge.getTargetVertexId(),vertex.getValue() );
        
         }
    	}
          for(int i=0;i<100;i++)
        	  hmap[i]=0;
    	
    }
    vertex.voteToHalt();
  }
}