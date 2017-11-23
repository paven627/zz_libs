import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.moji.launchserver.AdCommonInterface.AdResponse;
import com.moji.launchserver.AdCommonInterface.AdType;
import com.moji.launchserver.AdCommonInterface.ResponseStat;
import com.moji.launchserver.AdCommonInterface.AdResponse.Builder;

public class MyTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
String s = "aBc";
System.out.println(s.replaceAll("abc", "ddd"));
System.out.println(s.replaceAll("aBc", "ddd"));
	}
}