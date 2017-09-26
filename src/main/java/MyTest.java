import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.lang3.StringUtils;

public class MyTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String join = StringUtils.join(new Object[] { "http", null, "b" }, ";", 0, 3);
		System.out.println(join);

		ByteBuffer buff = ByteBuffer.allocate(1024);
		FileChannel channel = new FileInputStream("").getChannel();
		channel.write(buff);
	}
}