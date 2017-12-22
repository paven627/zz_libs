package test.storm.sample;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
//import Storm IRichBolt package
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;


/**
 * 呼叫日志创建
 *
 */
public class CallLogCreatorBolt implements IRichBolt {
	private static final long serialVersionUID = 1L;
	// Create instance for OutputCollector which collects and emits tuples to
	// produce output
	private OutputCollector collector;

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		String from = tuple.getString(0);
		String to = tuple.getString(1);
		Integer duration = tuple.getInteger(2);
		
		// 将接受日志重新组合为两个字段, 重新命名为 call 与 duration
		collector.emit(new Values(from + " - " + to, duration));
	}

	@Override
	public void cleanup() {
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("call", "duration"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}
