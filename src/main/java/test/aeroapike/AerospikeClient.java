package test.aeroapike;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;

public class AerospikeClient {

	public static void main(String[] args) {
		com.aerospike.client.AerospikeClient client = new com.aerospike.client.AerospikeClient("60.205.230.151", 3000);

		// key 的组成 ,namespace, set , key
		Key key = new Key("test", "demo", "putgetkey");
		
		//值的组成 , name, value
		Bin bin1 = new Bin("bin1", "value1");
		Bin bin2 = new Bin("bin2", "value2");

		
		client.put(null, key, bin1, bin2);

		// Read a record
		Record record = client.get(null, key);
		System.out.println(record.getString("bin1"));
		client.close();
	}

}
