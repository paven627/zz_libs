import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MyTest {
    private static String SPLIT = "||";

    public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
    	FileInputStream fileInputStream = new FileInputStream("/C:/workspace/ad_launch/target/classes/ipRegion.xlsx");
    }

    private static int check(String string) {
        return Integer.parseInt(string);
    }
}