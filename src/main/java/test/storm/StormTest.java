package test.storm;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.Scheme;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import storm.kafka.bolt.KafkaBolt;

public class StormTest {
	public static void main(String[] args) {
		BrokerHosts brokerHosts = new ZkHosts("192.168.1.181:2181");

		SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "tt", "/kafka", "kafkaspout");
		// 配置KafkaBolt中的kafka.broker.properties
		Config conf = new Config();
		Map<String, String> map = new HashMap<String, String>();

		map.put("metadata.broker.list", "127.0.0.1:9092");
		map.put("serializer.class", "kafka.serializer.StringEncoder");
		conf.put("kafka.broker.properties", map);
		conf.put("topic", "tt");

		spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());

		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("spout", new KafkaSpout(spoutConfig));
		builder.setBolt("bolt", new SenqueceBolt()).shuffleGrouping("spout");
		builder.setBolt("kafkabolt", new KafkaBolt<String, Integer>()).shuffleGrouping("bolt");

//		if (args != null && args.length > 0) {
//			// 提交到集群运行
//			try {
//				StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
//			} catch (AlreadyAliveException e) {
//				e.printStackTrace();
//			} catch (InvalidTopologyException e) {
//				e.printStackTrace();
//			}
//		} else {
			// 本地模式运行
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("tt", conf, builder.createTopology());
			Utils.sleep(1000000);
			cluster.killTopology("tt");
			cluster.shutdown();
//		}
	}

	public List<Object> deserialize(byte[] arg0) {
		try {
			String msg = new String(arg0, "UTF-8");
			return new Values(msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Fields getOutputFields() {
		return new Fields("msg");
	}

}

class SenqueceBolt extends BaseBasicBolt {

	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		String word = (String) arg0.getValue(0);
		String out = "output:" + word;
		System.out.println(out);
		arg1.emit(new Values(out));
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("message"));
	}
}

class MessageScheme implements Scheme {

	public List<Object> deserialize(byte[] arg0) {
		try {
			String msg = new String(arg0, "UTF-8");
			return new Values(msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Fields getOutputFields() {
		return new Fields("msg");
	}

}
