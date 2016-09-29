import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class T {
	private static final String LL = "Decompiling this copyrighted software is a violation of both your license agreement and the Digital Millenium Copyright Act of 1998 (http://www.loc.gov/copyright/legislation/dmca.pdf). Under section 1204 of the DMCA, penalties range up to a $500,000 fine or up to five years imprisonment for a first offense. Think about it; pay for a license, avoid prosecution, and feel better about yourself.";

	public String getSerial(String userId, String type) {
		NumberFormat nf = new DecimalFormat("000");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 3);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		String licenseNum = nf.format((int) (Math.random() * 1000));
		String expTime = new StringBuilder("-")
				.append(new SimpleDateFormat("yyMMdd").format(cal.getTime()))
				.append("0").toString();
		String need = new StringBuilder(userId.substring(0, 1)).append("Y")
				.append(type).append("-100").append(licenseNum).append(expTime)
				.toString();
		String dx = new StringBuilder(need).append(LL).append(userId)
				.toString();
		int suf = this.decode(dx);
		String code = new StringBuilder(need).append(String.valueOf(suf))
				.toString();
		return this.change(code);
	}

	private int decode(String s) {
		int i;
		char[] ac;
		int j;
		int k;
		i = 0;
		ac = s.toCharArray();
		j = 0;
		k = ac.length;
		while (j < k) {
			i = (31 * i) + ac[j];
			j++;
		}
		return Math.abs(i);
	}

	private String change(String s) {
		byte[] abyte0;
		char[] ac;
		int i;
		int k;
		int j;
		abyte0 = s.getBytes();
		ac = new char[s.length()];
		i = 0;
		k = abyte0.length;
		while (i < k) {
			j = abyte0[i];
			if ((j >= 48) && (j <= 57)) {
				j = (((j - 48) + 5) % 10) + 48;
			} else if ((j >= 65) && (j <= 90)) {
				j = (((j - 65) + 13) % 26) + 65;
			} else if ((j >= 97) && (j <= 122)) {
				j = (((j - 97) + 13) % 26) + 97;
			}
			ac[i] = (char) j;
			i++;
		}
		return String.valueOf(ac);
	}

	public static void main(String[] args) {
		try {
			System.out.println("please input register name:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			String userId = null;
			userId = reader.readLine();
			if (userId == null || "".equals(userId)) {
				System.out.println("name is null");
				System.exit(0);
			}
			T myeclipsegen = new T();
			String res = myeclipsegen.getSerial(userId, "E3MS");
			System.out.println("Serial:" + res);
			reader.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}