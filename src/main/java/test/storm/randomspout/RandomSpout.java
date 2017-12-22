package test.storm.randomspout;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class RandomSpout extends BaseRichSpout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SpoutOutputCollector collector;

	private Random rand;

	private static String[] sentences = new String[] { "edi:I'm happy", "marry:I'm angry", "john:I'm sad",
			"ted:I'm excited", "laden:I'm dangerous" };

	/**
	 * 为Spout提供执行环境。执行器将运行此方法来初始化喷头.
	 * 
	 * <pre>
	 * conf - 为此spout提供storm配置。 
	 * context - 提供有关拓扑中的spout位置，其任务ID，输入和输出信息的完整信息。
	 * collector - 使我们能够发出将由bolts处理的元组。
	 * </pre>
	 */
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.rand = new Random();
	}

	/**
	 * 通过收集器发出生成的数据。
	 * 
	 * nextTuple()从与ack()和fail()方法相同的循环中定期调用。它必须释放线程的控制，当没有工作要做，以便其他方法有机会被调用。
	 * 因此，nextTuple的第一行检查处理是否已完成。如果是这样，它应该休眠至少一毫秒，以减少处理器在返回之前的负载
	 * 
	 */
	@Override
	public void nextTuple() {
		String toSay = sentences[rand.nextInt(sentences.length)];
		this.collector.emit(new Values(toSay));
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 声明元组的输出模式。
	 * 
	 * @param declarer
	 *            它用于声明输出流id，输出字段等
	 *
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("sentence"));
	}

	/**
	 * spout关闭时调用
	 */
	@Override
	public void close() {
		System.out.println("spout 关闭 .....");
		super.close();
	}

	/**
	 * 确认处理了特定元组。
	 */
	@Override
	public void ack(Object msgId) {
		System.out.println("ack 调用");
		super.ack(msgId);
	}

	/**
	 * 此方法通知特定元组尚未完全处理。 Storm将重新处理特定的元组。
	 */
	@Override
	public void fail(Object msgId) {
		super.fail(msgId);
	}
	
	
}