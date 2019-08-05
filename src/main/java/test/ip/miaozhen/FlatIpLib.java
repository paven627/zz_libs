package test.ip.miaozhen;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * user: weishishuo@miaozhen.com
 * date: 12/8/14 2:14 PM.
 * 简单说明： 这个ip库是扁平的ip库，地域只分“城市”和“省份”两个层级。
 * 格式为城市，省份，开始ip，结束ip，分隔符是英语的逗号。每行一个ip段。ip是点分隔的格式。
 * 地域判断的逻辑就是先看城市id是否匹配，再看省份id是否匹配，只要有一个是匹配的，就算是匹配成功。
 */
public class FlatIpLib {
    private static final Logger logger = Logger.getLogger(FlatIpLib.class);

    private static volatile FlatIpLib flatIpLib;

    public static final String IP_SEP = ",";
    public static final String CHINA = "86";
    public static final String OVER_SEA = "90";
    public static final Set<String> CHINA_PROV = getAllProv();
    public static final Set<String> CHINA_CITY = getAllCity();

    private TreeMap<Long,IpRecord> ipToRecord;

    private FlatIpLib(){
    }
    public static FlatIpLib getInstance(String filename){
        if(flatIpLib == null){
            synchronized (FlatIpLib.class){
                if(flatIpLib == null){
                    flatIpLib = new FlatIpLib();
                    flatIpLib.loadFromFile(filename);
                }
            }
        }
        return flatIpLib;
    }
    public static FlatIpLib getInstance(){
        if(flatIpLib == null){
//            logger.error("iplib has not been initialized!");
            throw new IllegalStateException("iplib has not been initialized!");
        }
        return flatIpLib;
    }
    public void loadFromStream(InputStream inputStream){
        ipToRecord = new TreeMap<>();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(IP_SEP);
                    if(fields != null && fields.length == 4){
                        IpRecord one = new IpRecord();
                        one.setCity(fields[0].intern());
                        one.setProv(fields[1].intern());
                        Long startIp = IpLib.ipToLong(fields[2]);
                        one.setStartIp(startIp);
                        one.setStopIp(IpLib.ipToLong(fields[3]));
                        ipToRecord.put(startIp,one);
                    }else{
                        logger.error("Ip Format Error: "+line);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                br.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void loadFromFile(String filename){
        try {
            loadFromStream(new FileInputStream(filename));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public IpRecord locateIp(String ip){
        if(!isIpv4(ip)){
            return null;
        }
        Long ipLong = IpLib.ipToLong(ip);
        Map.Entry<Long, IpRecord> found = ipToRecord.floorEntry(ipLong);
        // 找不到或者ip超出区间的结束ip
        if(found == null || found.getValue().getStopIp() < ipLong){
            return null;
        }
        return found.getValue();
    }
	public static final String IPADDRESS_PATTERN =
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	public static Pattern IPV4_PAT = Pattern.compile(IPADDRESS_PATTERN);


	public static boolean isIpv4(final String ip){
		Matcher matcher = IPV4_PAT.matcher(ip);
		return matcher.matches();
	}
    public boolean matchRegion(Set<String> targets, String ip){
//        if(Strings.isEmpty(ip)){
//            return false;
//        }
        if(!isIpv4(ip)){
            return false;
        }

        if (targets != null && !targets.isEmpty()) {
            boolean isToChina = false, isToOversea = false;
            if(targets.contains(CHINA)){
                isToChina = true;
            }
            if(targets.contains(OVER_SEA)){
                isToOversea = true;
            }
            if(isToChina && isToOversea){
                return true;
            }
            // 有地域定向，但请求无IP
            if(ip == null || ip.isEmpty()){
                return false;
            }
            FlatIpLib.IpRecord ipRecord = locateIp(ip);
            // ip查不到
            if (ipRecord == null) {
                return false;
            }
            String reqCity = ipRecord.getCity();
            String reqProv = ipRecord.getProv();
            if (!targets.contains(reqCity) && !targets.contains(reqProv)) {
                // 特殊id 全中国
                if(isToChina){
                    if(CHINA_PROV.contains(reqProv)|| CHINA_CITY.contains(reqCity)){
                        return true;
                    }
                }
                // 特殊id 国外
                if(isToOversea){
                    if(!CHINA_PROV.contains(reqProv) && !CHINA_CITY.contains(reqCity)){
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean matchRegion(Set<String> targets, IpRecord ipRecord){
        if(ipRecord == null){
            return false;
        }

        if (targets != null && !targets.isEmpty()) {
            boolean isToChina = false, isToOversea = false;
            if(targets.contains(CHINA)){
                isToChina = true;
            }
            if(targets.contains(OVER_SEA)){
                isToOversea = true;
            }
            if(isToChina && isToOversea){
                return true;
            }

            String reqCity = ipRecord.getCity();
            String reqProv = ipRecord.getProv();
            if (!targets.contains(reqCity) && !targets.contains(reqProv)) {
                // 特殊id 全中国
                if(isToChina){
                    if(CHINA_PROV.contains(reqProv)|| CHINA_CITY.contains(reqCity)){
                        return true;
                    }
                }
                // 特殊id 国外
                if(isToOversea){
                    if(!CHINA_PROV.contains(reqProv) && !CHINA_CITY.contains(reqCity)){
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public static class IpRecord{
        private String city;
        private String prov;
        private Long startIp;
        private Long stopIp;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public Long getStartIp() {
            return startIp;
        }

        public void setStartIp(Long startIp) {
            this.startIp = startIp;
        }

        public Long getStopIp() {
            return stopIp;
        }

        public void setStopIp(Long stopIp) {
            this.stopIp = stopIp;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IpRecord ipRecord = (IpRecord) o;

            if (city != null ? !city.equals(ipRecord.city) : ipRecord.city != null) return false;
            if (prov != null ? !prov.equals(ipRecord.prov) : ipRecord.prov != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = city != null ? city.hashCode() : 0;
            result = 31 * result + (prov != null ? prov.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "IpRecord{" +
                    "city='" + city + '\'' +
                    ", prov='" + prov + '\'' +
                    ", startIp=" + startIp +
                    ", stopIp=" + stopIp +
                    '}';
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(IpRecord one:ipToRecord.values()){
            sb.append(one.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String content = "C:\\workspace\\bazaro-auctionTrunk\\src\\main\\resources\\ip.txt";
		FileInputStream is = new FileInputStream(content);
        FlatIpLib flatIpLib = new FlatIpLib();
        flatIpLib.loadFromStream(is);
        System.out.println(flatIpLib.locateIp("36.17.63.221"));
        System.out.println("all:");
//        System.out.println(flatIpLib);
        System.out.println(CHINA_PROV.size());
        System.out.println(CHINA_CITY.size());
    }

    public static Set<String> getAllProv(){
        String[] all = {
                "11","12","13","14","15","21",
                "22","23","31","32","33","34",
                "35","36","37","41","42","43",
                "44","45","46","50","51","52",
                "53","54","61","62","63","64",
                "65","71","81","82","99",
        };
        return new HashSet<String>(Arrays.asList(all));
    }
    public static Set<String> getAllCity(){
        String[] all = {
                "110000","120000","130000","130100","130200","130300",
                "130400","130500","130600","130700","130800","130900",
                "131000","131100","140000","140100","140200","140300",
                "140400","140500","140600","140700","140800","140900",
                "141000","141100","150000","150100","150200","150300",
                "150400","150500","150600","150700","150800","150900",
                "152200","152500","152900","210000","210100","210200",
                "210300","210400","210500","210600","210700","210800",
                "210900","211000","211100","211200","211300","211400",
                "220000","220100","220200","220300","220400","220500",
                "220600","220700","220800","222400","230000","230100",
                "230200","230300","230400","230500","230600","230700",
                "230800","230900","231000","231100","231200","232700",
                "310000","320000","320100","320200","320300","320400",
                "320500","320600","320700","320800","320900","321000",
                "321100","321200","321300","330000","330100","330200",
                "330300","330400","330500","330600","330700","330800",
                "330900","331000","331100","340000","340100","340200",
                "340300","340400","340500","340600","340700","340800",
                "341000","341100","341200","341300","341400","341500",
                "341600","341700","341800","350000","350100","350200",
                "350300","350400","350500","350600","350700","350800",
                "350900","360000","360100","360200","360300","360400",
                "360500","360600","360700","360800","360900","361000",
                "361100","370000","370100","370200","370300","370400",
                "370500","370600","370700","370800","370900","371000",
                "371100","371200","371300","371400","371500","371600",
                "371700","410000","410100","410200","410300","410400",
                "410500","410600","410700","410800","410900","411000",
                "411100","411200","411300","411400","411500","411600",
                "411700","419000","420000","420100","420200","420300",
                "420500","420600","420700","420800","420900","421000",
                "421100","421200","421300","422800","429000","430000",
                "430100","430200","430300","430400","430500","430600",
                "430700","430800","430900","431000","431100","431200",
                "431300","433100","440000","440100","440200","440300",
                "440400","440500","440600","440700","440800","440900",
                "441200","441300","441400","441500","441600","441700",
                "441800","441900","442000","445100","445200","445300",
                "450000","450100","450200","450300","450400","450500",
                "450600","450700","450800","450900","451000","451100",
                "451200","451300","451400","460000","460100","460200",
                "469000","500000","510000","510100","510300","510400",
                "510500","510600","510700","510800","510900","511000",
                "511100","511300","511400","511500","511600","511700",
                "511800","511900","512000","513200","513300","513400",
                "520000","520100","520200","520300","520400","522200",
                "522300","522400","522600","522700","530000","530100",
                "530300","530400","530500","530600","530700","530800",
                "530900","532300","532500","532600","532800","532900",
                "533100","533300","533400","540000","540100","542100",
                "542200","542300","542400","542500","542600","610000",
                "610100","610200","610300","610400","610500","610600",
                "610700","610800","610900","611000","620000","620100",
                "620200","620300","620400","620500","620600","620700",
                "620800","620900","621000","621100","621200","622900",
                "623000","630000","630100","632100","632200","632300",
                "632500","632600","632700","632800","640000","640100",
                "640200","640300","640400","640500","650000","650100",
                "650200","652100","652200","652300","652700","652800",
                "652900","653000","653100","653200","654000","654200",
                "654300","659000","710000","810000","820000","990000",
        };
        return new HashSet<String>(Arrays.asList(all));
    }
}
