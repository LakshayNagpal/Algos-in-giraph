package giraph1;

import org.apache.giraph.aggregators.LongSumAggregator;
import org.apache.giraph.master.DefaultMasterCompute;
import org.apache.giraph.aggregators.DoubleSumAggregator;
 
public class AggregratorClass extends DefaultMasterCompute { // master compute
// implementations are usually extending the DefaultMasterCompute abstract class
// just the same way that our basic BSP compute implementations extended
// BasicComputation. Unlike BasicComputation you have to provide implementations
// for two methods: compute() and initialize()
 
public static final String ID = "TotalSumAggregator"; // all registered
// aggregators are referenced by unique (within a given application) but arbitrary
// strings
@Override
public void compute() {
// this is a global master compute method that will be executed once
// before every superstep. In our case we're simply outputting the
// running tally of total number of edges in a graph
System.out.println("Maximum Value at superstep " +
getSuperstep() + " is " +
getAggregatedValue(ID));

}
 
@Override
// this method gets called during overall initialization of the Giraph
// BSP machinery. It is an ideal place to register all required
// aggregators.
public void initialize() throws InstantiationException,
IllegalAccessException {
registerAggregator(ID, DoubleSumAggregator.class); // this is how we associate
// an aggregator ID with a particular implementation of an aggregator:
// in our case we are using a built-in LongSumAggregator that simply
// sums all of the values sent to it
}
}
