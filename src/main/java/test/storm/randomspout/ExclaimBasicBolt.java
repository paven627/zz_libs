package test.storm.randomspout;

import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class ExclaimBasicBolt extends BaseBasicBolt{

	/**
	 * 处理单个元组的输入
	 */
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		// String sentence = tuple.getString(0);
		String sentence = (String) tuple.getValue(0);
		String out = sentence + "!";
		collector.emit(new Values(out));
	}

	/**
	 * 声明元组的输出模式。
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("excl_sentence"));
	}

	/**
	 * 为bolt提供要执行的环境。执行器将运行此方法来初始化spout。
	 * 
	 * @param stormConf
	 *            为此bolt提供Storm配置。
	 * @param context
	 *            提供有关拓扑中的bolt位置，其任务ID，输入和输出信息等的完整信息。
	 * 
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		System.out.println("ExclaimBasicBolt  prepare ........");
	}

	/**
	 * 当spout要关闭时调用。
	 */
	@Override
	public void cleanup() {
		System.out.println("ExclaimBasicBolt    cleanup ......");
		super.cleanup();
	}
}