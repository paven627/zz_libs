package test.ip.miaozhen;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class IpLib implements Serializable{

    private static final long serialVersionUID = 1L;

    public static class RegionNode implements Serializable{
        private static final long serialVersionUID = 1L;
        public STID id;
        public long index;
        public HashSet<RegionNode> subRegions;
        public boolean equals(Object other){
            if(this == other) return true;
            if(other == null) return false;
            if(getClass() != other.getClass()) return false;
            RegionNode tmp = (RegionNode) other;
            return (this.id.equals(tmp.id) && this.index == tmp.index);
        }
        public int hashCode(){
            return (int) (id.hashCode() + index);
        }
        public RegionNode(){
            id = NULL_STID;
            subRegions = new HashSet<RegionNode>();
        }
    }

    /**
     * @brief IP库中每个ip之间以'\n'分隔， ip和region之间以SEPPattern = ","分隔。
    */
    public static final String SEPPattern = ",";
    public static final long MAX_IP = (2L << 31) - 1;
    public static final STID NULL_STID = STID.getNullSTID();
    public static final STID GLOB_STID = STID.getGlobalSTID();
    public RegionNode rootRegionNode;
    public HashMap<STID, STID> subToSup;
    public HashMap<STID, Long> regionToIndex;
    public HashMap<Long, STID> indexToRegion;
    public STID mainRegionID;
    private SortedMap<Long, STID> ipToRegionID;
    private SortedMap<Long, Long> ipToIndex;
    private long regionIndex = 0;

    public IpLib(String ipLibDir, String ipLibName) throws Exception{
        String[] fields = ipLibName.split("-");
        if(fields.length < 5){
            throw new Exception("ipLibName illegal: "+ipLibName);
        }
        String ipMainRegionStr = fields[2];
        String ipMeta = fields[1];
        for(int i = 2; i < 4; ++i){
            ipMeta = ipMeta + "-" + fields[i];
        }
        IpLibCommon(new FileInputStream(ipLibDir + "/" + ipLibName), new FileInputStream(ipLibDir + "/" + ipMeta), new STID(ipMainRegionStr));
    }
    public IpLib(InputStream ipLibStream, InputStream ipMetaStream, STID inMainRegionID) throws Exception{
        IpLibCommon(ipLibStream, ipMetaStream, inMainRegionID);
    }
    private void IpLibCommon(InputStream ipLibStream, InputStream ipMetaStream, STID inMainRegionID) throws Exception{
        rootRegionNode = new RegionNode();
        subToSup = new HashMap<STID, STID>();
        regionToIndex = new HashMap<STID, Long>();
        indexToRegion = new HashMap<Long, STID>();
        mainRegionID = inMainRegionID;
        initMeta(ipMetaStream);
        init(ipLibStream);
    }

    public int getDepth(final STID regionID){
        return getDepthImpl(regionID, 1);
    }
    private int getDepthImpl(final STID regionID, int currentDepth){
        STID supRegionID = subToSup.get(regionID);
        if(supRegionID == null){
            return -1;
        }
        if(supRegionID.equals(mainRegionID)){
            return currentDepth;
        }
        return getDepthImpl(supRegionID, ++currentDepth);
    }

    private void init(InputStream in) throws Exception{
        Scanner s = new Scanner(in);
        String line;
        ipToRegionID = new TreeMap<Long, STID>();
        ipToIndex = new TreeMap<Long, Long>();
        int lineno = 0;
        while(s.hasNextLine()){
            line = s.nextLine();
            ++lineno;
            String[] fields = line.split(SEPPattern);
            if(fields.length != 3){
                System.out.println("malformat in line: " + lineno);
                continue;
            }
            long ip = Long.parseLong(fields[1]);
            STID regionID = new STID(fields[2]);
            Long index = regionToIndex.get(regionID);
            if(index == null){
                System.out.println("cannot find region: " + regionID.toString() + " in Region Meta File. line : " + lineno);
                continue;
            }
            ipToRegionID.put(ip, regionID);
            ipToIndex.put(ip, index);
        }
        s.close();
        if(ipToRegionID.isEmpty()){
            throw new Exception("ipToRegionID is empty");
        }
        if(ipToRegionID.lastKey() != MAX_IP){
            throw new Exception("last != uint_max");
        }
    }

    public void setRegionIndex(final STID regionID){
        if(!regionToIndex.containsKey(regionID)){
            regionToIndex.put(regionID, regionIndex);
            indexToRegion.put(regionIndex, regionID);
            regionIndex++;
        }
    }

    //TODO, 估计要检查一下死循环
    private void initRegionNodeTree(RegionNode root) throws Exception{
        if(regionToIndex.containsKey(root.id)){
            root.index = regionToIndex.get(root.id);
        }else{
            throw new Exception("region: " + root.id + " not find in Region Meta File");
        }
        Iterator<Map.Entry<STID, STID>> iter = subToSup.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<STID, STID> entry = (Map.Entry<STID, STID>) iter.next();
            STID child = entry.getKey();
            STID father = entry.getValue();
            if(father.equals(root.id)){
                if(regionToIndex.containsKey(child)){
                    RegionNode childNode = new RegionNode();
                    childNode.id = child;
                    childNode.index = regionToIndex.get(child);
                    root.subRegions.add(childNode);
                }
            }
        }
        if(root.subRegions.isEmpty()){
            return;
        }
        for(RegionNode entry:root.subRegions){
            initRegionNodeTree(entry);
        }
    }

    private void initMeta(InputStream in) throws Exception{
        Scanner s = new Scanner(in);
        String line;
        rootRegionNode.id = NULL_STID;

        while(s.hasNextLine()){
            line = s.nextLine();
            String[] fields = line.split(SEPPattern);
            STID child;
            STID father;
            if(fields.length < 2 || fields[0].equals(fields[1])){
                System.out.println("skip line: " + line);
                continue;
            }
            try{
                child = new STID(fields[0]);
                father = new STID(fields[1]);
            }catch(Exception e){
                e.printStackTrace();
                continue;
            }
            if(father.equals(NULL_STID)){
                rootRegionNode.id = child;
                setRegionIndex(child);
            }else{
                setRegionIndex(father);
                setRegionIndex(child);
                subToSup.put(child, father);
            }
        }
        s.close();
        if(rootRegionNode.id.equals(NULL_STID)){
            throw new Exception("root is null: "+rootRegionNode.id.toString());
        }
        initRegionNodeTree(rootRegionNode);
    }

    public STID getRegionID(long ip){
        SortedMap<Long, STID> tail = ipToRegionID.tailMap(ip);
        if(tail.isEmpty()){
            return new STID(); //global
        }
        return tail.get(tail.firstKey());
    }

    public long getIndex(long ip){
        SortedMap<Long, Long> tail = ipToIndex.tailMap(ip);
        if(tail.isEmpty()){
            return regionToIndex.get(new STID()); //global
        }
        return tail.get(tail.firstKey());
    }

    public static long ipToLong(String ip) {
        if(ip.indexOf(".") == -1){
            return Long.parseLong(ip);
        }
        String[] octets = ip.trim().split("\\.");
        long result = 0;
        for (String octet : octets) {
            result <<= 8;
            result |= Integer.parseInt(octet) & 0xff;
        }
        return result;
    }

    public boolean ipIsFrom(String stid, String ip){
        boolean toRet = false;
        try{
            STID stidToQuery = new STID(stid);
            long ipLong = ipToLong(ip);
            STID ipFromStid = getRegionID(ipLong);
            while(true){
                if(stidToQuery.equals(ipFromStid)){
                    toRet = true;
                    break;
                }
                ipFromStid = subToSup.get(ipFromStid);
                if(ipFromStid == null || ipFromStid.equals(NULL_STID)){
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            toRet = false;
        }
        return toRet;
    }

    public static void main(String[] args){
		long l = ipToLong("61.243.67.0");
		System.out.println(l);
	}
}
