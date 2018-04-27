import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.DecimalFormat;

public class MyTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数
        String num = df.format(2091 / (double)459880);//返回的是String类型


        System.out.println(2091 / (double)459880);

        System.out.println(num);


        System.out.println(3/(double)4);
    }

}