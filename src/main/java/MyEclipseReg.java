//1.新建一个Java类，名字随便起，然后将下面的代码复制进去，修改一下类名和构造方法名;
//
//2.在控制台输入Subscriber：任意字符；
//
//3.复制得到注册码。
 
import java.io.*;
import java.text.*;
import java.util.Calendar; 
public class MyEclipseReg/**类名如果任意取别忘了修改此处和构造方法*/
{

//    private static final String L = "Decompiling this copyrighted software is a violation of both your license agreement and the Digital Millenium Copyright Act of 1998 (http://www.loc.gov/copyright/legislation/dmca.pdf). Under section 1204 of the DMCA, penalties range up to a $500,000 fine or up to five years imprisonment for a first offense. Think about it; pay for a license, avoid prosecution, and feel better about yourself.";

    public MyEclipseReg()
    {
    }

    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userId = null;
        int intLicenseNum = 1;
        String strLicenseNum = null;
        boolean bProFlag = true;
        String strProFlag = null;
        while(userId == null || "".equals(userId.trim()))
        {
            System.out.print("Subscriber: ");
            try
            {
                userId = br.readLine();
            }
            catch(IOException ioexception) { }
        }

        System.out.print("Number of licenses(default 1, max 999): ");
        try
        {
            strLicenseNum = br.readLine();
        }
        catch(IOException ioexception1) { }
        NumberFormat nf = new DecimalFormat("000");
        if(strLicenseNum == null || "".equals(strLicenseNum.trim()))
            strLicenseNum = nf.format(intLicenseNum);
        else
            strLicenseNum = nf.format(Integer.parseInt(strLicenseNum));
        System.out.print("Professional or Standard(default pro, n=stand): ");
        try
        {
            strProFlag = br.readLine();
        }
        catch(IOException ioexception2) { }
        if(strProFlag != null && !"".equals(strProFlag.trim()))
            bProFlag = false;
        Calendar cal = Calendar.getInstance();
        cal.add(1, 2);
        cal.add(6, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String need = userId.substring(0, 1) + "Y" + (bProFlag ? "E3MP" : "E2MY") + "-100" + strLicenseNum + "-" + sdf.format(cal.getTime()) + "0";
        String dx = need + "Decompiling this copyrighted software is a violation of both your license agreement and the Digital Millenium Copyright Act of 1998 (http://www.loc.gov/copyright/legislation/dmca.pdf). Under section 1204 of the DMCA, penalties range up to a $500,000 fine or up to five years imprisonment for a first offense. Think about it; pay for a license, avoid prosecution, and feel better about yourself." + userId;
        int suf = decode(dx);
        String code = need + suf;
        System.out.println("Subscription Code: " + change(code));
    }

    static int decode(String s)
    {
        int i = 0;
        char ac[] = s.toCharArray();
        int j = 0;
        for(int k = ac.length; j < k; j++)
            i = 31 * i + ac[j];

        return Math.abs(i);
    }

    static String change(String s)
    {
        if(s == null || s.length() == 0)
            return s;
        byte abyte0[] = s.getBytes();
        char ac[] = new char[s.length()];
        int i = 0;
        for(int k = abyte0.length; i < k; i++)
        {
            int j = abyte0[i];
            if(j >= 48 && j <= 57)
                j = ((j - 48) + 5) % 10 + 48;
            else
            if(j >= 65 && j <= 90)
                j = ((j - 65) + 13) % 26 + 65;
            else
            if(j >= 97 && j <= 122)
                j = ((j - 97) + 13) % 26 + 97;
            ac[i] = (char)j;
        }
        return String.valueOf(ac);
    }
}
