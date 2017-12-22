package test.storm.randomspout;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

/**
 * @author bin.deng
 *
 */
public class PrintBolt implements IRichBolt {

	/**
	 * 声明元组的输出模式。
	 * 
	 * @param declarer
	 *            用于声明输出流id，输出字段等
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// do nothing
	}

	/**
	 * 为bolt提供要执行的环境。执行器将运行此方法来初始化spout。
	 * 
	 * <pre>
	 * conf - 为此bolt提供Storm配置。
	 * context -提供有关拓扑中的bolt位置，其任务ID，输入和输出信息等的完整信息。
	 * collector -使我们能够发出处理的元组。
	 * </pre>
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub

	}

	/**
	 * 处理单个元组的输入
	 * 
	 */
	@Override
	public void execute(Tuple input) {
		String rec = input.getString(0);
		System.err.println("String recieved: " + rec);
	}

	/**
	 * 当spout要关闭时调用。
	 */
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}