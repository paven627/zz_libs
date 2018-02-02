package test.storm.sample;

import java.util.*;
//import storm tuple packages
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

//import Spout interface packages
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;

//Create a class FakeLogReaderSpout which implement IRichSpout interface 
//   to access functionalities

public class FakeCallLogReaderSpout implements IRichSpout {
	private static final long serialVersionUID = 1L;
	
	// Create instance for SpoutOutputCollector which passes tuples to bolt.
	private SpoutOutputCollector collector;
	private boolean completed = false;

	// Create instance for TopologyContext which contains topology data.
	private TopologyContext context;

	// Create instance for Random class.
	private Random randomGenerator = new Random();
	private Integer idx = 0;
	
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.context = context;
		this.collector = collector;
		String componentId = context.getThisComponentId();
		System.out.println("componentId:" + componentId);
		List<Integer> componentTasks = context.getComponentTasks(componentId);
		System.out.println(componentTasks);
	}

	@Override
	public void nextTuple() {
		if (this.idx <= 100) {
			List<String> mobileNumbers = new ArrayList<String>();
			mobileNumbers.add("1111111111");
			mobileNumbers.add("2222222222");
			mobileNumbers.add("3333333333");
			mobileNumbers.add("4444444444");

			Integer localIdx = 0;
			while (localIdx++ < 10 && this.idx++ < 100) {
				String fromMobileNumber = mobileNumbers.get(randomGenerator.nextInt(4));
				String toMobileNumber = mobileNumbers.get(randomGenerator.nextInt(4));

				while (fromMobileNumber == toMobileNumber) {
					toMobileNumber = mobileNumbers.get(randomGenerator.nextInt(4));
				}

				Integer duration = randomGenerator.nextInt(30);
				// 随机生成 from , to ,duration 3个字段, 生成日志信息
				this.collector.emit(new Values(fromMobileNumber, toMobileNumber, duration));
			}
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("from", "to", "duration"));
	}

	// Override all the interface methods
	@Override
	public void close() {
	}

	public boolean isDistributed() {
		return false;
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

	@Override
	public void ack(Object msgId) {
	}

	@Override
	public void fail(Object msgId) {
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}