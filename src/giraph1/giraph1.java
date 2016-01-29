 package giraph1;
// to run the aggregrator class and the stochastic gradient descend algorithm, comment this driver function 
 // and uncomment the lower driver function for this particular use.
import org.apache.giraph.conf.GiraphConfiguration;
//import org.apache.giraph.examples.SimpleShortestPathsComputation; // to run sample example, import this class and edit giraphconf.setcomputationclass(SimpleShortestPathsComputation.class)
import org.apache.giraph.io.formats.GiraphFileInputFormat;
import org.apache.giraph.io.formats.IdWithValueTextOutputFormat;
import org.apache.giraph.io.formats.JsonLongDoubleFloatDoubleVertexInputFormat;
import org.apache.giraph.job.GiraphJob;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class giraph1 implements Tool{
	
	private Configuration conf;
	private String inputPath;
	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getOutputPath() {
		return OutputPath;
	}

	public void setOutputPath(String outputPath) {
		OutputPath = outputPath;
	}

	private String OutputPath;
	
	
	@Override
	public Configuration getConf() {
		// TODO Auto-generated method stub
		return conf;
	}

	@Override
	public void setConf(Configuration conf) {
		// TODO Auto-generated method stub
		this.conf = conf;
		
	}

	@Override
	public int run(String[] argss) throws Exception {
		// TODO Auto-generated method stub			
		setInputPath("/home/lakshay/workspace/giraph1/input/input-json.txt"); // [source_id,source_value,[[dest_id, edge_value],...]]
		setOutputPath("/home/lakshay/workspace/giraph1/output/in");
	
		GiraphConfiguration giraphConf = new GiraphConfiguration(getConf());
		
		//giraphConf.setComputationClass(SimpleShortestPathsComputation.class);
		
		giraphConf.setComputationClass(InDegree.class);
		
		giraphConf.setVertexInputFormatClass(JsonLongDoubleFloatDoubleVertexInputFormat.class); //Input format
		
		GiraphFileInputFormat.addVertexInputPath(giraphConf, new Path(getInputPath()));
		
		giraphConf.setVertexOutputFormatClass(IdWithValueTextOutputFormat.class);
		
		giraphConf.setWorkerConfiguration(0, 1, 100);	// 100 workers set for the job
		
		giraphConf.setLocalTestMode(true);
		
		giraphConf.setMaxNumberOfSupersteps(100);		// maximum 100 supersteps can be implemented
		
		giraphConf.SPLIT_MASTER_WORKER.set(giraphConf, false);	
		giraphConf.USE_OUT_OF_CORE_GRAPH.set(giraphConf, true);
		
		GiraphJob job = new GiraphJob(giraphConf, getClass().getName());
		
		FileOutputFormat.setOutputPath(job.getInternalJob(), new Path(getOutputPath()));
		job.run(true);
		
		return 1;
		
		}

	
	public static void main(String[] args) throws Exception{
		ToolRunner.run(new giraph1(), args);
		
	}
}

// Stochastic Gradient Descend

/*
package giraph1;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.io.formats.GiraphFileInputFormat;
import org.apache.giraph.io.formats.IdWithValueTextOutputFormat;
import org.apache.giraph.io.formats.JsonLongDoubleFloatDoubleVertexInputFormat;
import org.apache.giraph.job.GiraphJob;
import org.apache.giraph.master.AggregatorBroadcast;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.giraph.aggregators.AggregatorWriter;
import org.apache.giraph.aggregators.FloatSumAggregator;
import org.apache.giraph.aggregators.*;


public class giraph1 implements Tool{
	
	
	
	private Configuration conf;
	private String inputPath;
	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getOutputPath() {
		return OutputPath;
	}

	public void setOutputPath(String outputPath) {
		OutputPath = outputPath;
	}

	private String OutputPath;
	
	
	@Override
	public Configuration getConf() {
		// TODO Auto-generated method stub
		return conf;
	}

	@Override
	public void setConf(Configuration conf) {
		// TODO Auto-generated method stub
		this.conf = conf;
		
	}

	@Override
	public int run(String[] argss) throws Exception {
		// TODO Auto-generated method stub
		setInputPath("/home/lakshay/workspace/giraph1/input/input-json2.txt");
		setOutputPath("/home/lakshay/workspace/giraph1/output/StochasticGradientDescent");
		
		GiraphConfiguration giraphConf = new GiraphConfiguration(getConf());
	    	
	 	giraphConf.setComputationClass(SGD.class);
		
		giraphConf.setVertexInputFormatClass(JsonLongDoubleFloatDoubleVertexInputFormat.class);

		
		GiraphFileInputFormat.addVertexInputPath(giraphConf, new Path(getInputPath()));
		
		giraphConf.setVertexOutputFormatClass(IdWithValueTextOutputFormat.class);
		
		
		
		//giraphConf.setDoOutputDuringComputation(true);

		
		giraphConf.setWorkerConfiguration(0, 1, 100);
		
		giraphConf.setLocalTestMode(true);
		
		giraphConf.setMasterComputeClass(AggregratorClass.class);
		
		giraphConf.setMaxNumberOfSupersteps(100);
		
		giraphConf.SPLIT_MASTER_WORKER.set(giraphConf, false);
		giraphConf.USE_OUT_OF_CORE_GRAPH.set(giraphConf, true);
		
		GiraphJob job = new GiraphJob(giraphConf, getClass().getName());
		
		FileOutputFormat.setOutputPath(job.getInternalJob(), new Path(getOutputPath()));
		job.run(true);
		
		return 1;
		
		}

	
	public static void main(String[] args) throws Exception{
		ToolRunner.run(new giraph1(), args);
		
	}
}

*/