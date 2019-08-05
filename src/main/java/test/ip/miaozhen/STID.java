package test.ip.miaozhen;

import java.io.Serializable;
import java.util.HashMap;

//not only for region id, some ids in panel also use id of this type
public class STID implements Serializable{

    private static final long serialVersionUID = 1L;

    public String id;
    public static final String NULL_STID_STR = "FFFFFFFFFFFFFFFFFFFFFFFF";
    public static final String GLOB_STID_STR = "000000000000000000000000"; //global
    public static HashMap<String, String> AllId = new HashMap<String, String>();

    public STID(){
        id = getSharedId(GLOB_STID_STR);
    }

    public STID(String s) throws Exception{
        id = new String(s.trim());
        if(id.length() != 24){
            throw new Exception("illegal stid: "+s);
        }
        id = getSharedId(id);
    }

    private static synchronized String getSharedId(String s){
        String toRet = AllId.get(s);
        if(toRet == null){
            toRet = s;
            AllId.put(toRet, toRet);
        }
        return toRet;
    }

    public static STID getGlobalSTID(){
        STID tmp = null;
        try{
            tmp = new STID(GLOB_STID_STR);
        }catch(Exception e){
            //should never happen
            e.printStackTrace();
        }
        return tmp;
    }

    public static STID getNullSTID(){
        STID tmp = null;
        try{
            tmp = new STID(NULL_STID_STR);
        }catch(Exception e){
            //should never happen
            e.printStackTrace();
        }
        return tmp;
    }

    public String toString(){
        return id;
    }

    public boolean equals(Object other){
        if(this == other) return true;
        if(other == null) return false;
        if(getClass() != other.getClass()) return false;
        STID tmp = (STID) other;
        return this.id.equals(tmp.id);
    }

    public int hashCode(){
        return this.id.hashCode();
    }

    public static void main(String[] args){
        System.out.println("global id is: " + new STID());
    }
}

