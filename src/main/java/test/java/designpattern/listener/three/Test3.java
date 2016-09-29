package test.java.designpattern.listener.three;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * 妯℃嫙涓�釜灏忓,鐫¤閱掍簡涔嬪悗闇�鍚冧笢瑗�澶т汉鍠傚皬瀛╁悆涓滆タ
 * 
 * 绗笁绉嶈璁�		
 * 閽堝绗簩绉嶇殑闂,鍒涘缓鐩戝惉鍣ㄦ帴鍙�鎯宠鐩戝惉杩欎釜浜嬩欢鐨勫璞″疄鐜拌繖涓洃鍚櫒
 * 鎺ュ彛,杩欐牱,姣忔鎯虫坊鍔犱竴涓洃鍚�鏃�鍙渶瑕佸垱寤轰竴涓洃鍚�鐨勫璞�
 * 鐒跺悗璋冪敤灏忓鐨勬坊鍔犵洃鍚�鐨勬柟娉�灏嗙洃鍚�浼犺繘鍘�姣忔閮戒笉闇�鏀瑰彉灏忓绫�
 * 娣诲姞浜嗛厤缃枃浠�濡傛灉闇�娣诲姞鏂扮殑鐩戝惉鑰�鍙渶鍦ㄩ厤缃枃浠朵腑鍐欑浉搴旂殑绫诲悕
 * 
 * 
 */

//閱掓潵浜嬩欢
class WakenUpEvent{
	public WakenUpEvent(long time, String location, Child source) {
		super();
		this.time = time;
		this.location = location;
		this.source = source;
	}
	private long time;
	private String location;
	private Child source ; 	//鍙戠敓浜嬩欢鐨勬簮
	
	public WakenUpEvent() {
		super();
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Child getSource() {
		return source;
	}
	public void setSource(Child source) {
		this.source = source;
	}
}

class Child implements Runnable{
	private List<WakenUpListener> wakenUpListeners =
		new ArrayList<WakenUpListener>();
	
	public void addWakenUpListener(WakenUpListener l){
		wakenUpListeners.add(l);
	}
	
	//璋冪敤鐖朵翰鏂规硶
	void wakenUp(){
		for(int i= 0,size = wakenUpListeners.size(); i<size; i++){
			WakenUpListener l = wakenUpListeners.get(i);
			/*
			 * 姣忎竴涓洃鍚�璋冪敤鍚勮嚜鐨勬柟娉�鍙傛暟涓虹洃鍚殑浜嬩欢,浜嬩欢鍙傛暟
			 * 鏄渶瑕佷紶閫掔殑淇℃伅(褰撳墠鏃堕棿,閱掓潵鐨勫湴鐐�鍜岃嚜宸辫繖涓璞�
			 */
			l.actionToWakenUp(new WakenUpEvent(
					System.currentTimeMillis(),"bed",this));
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.wakenUp();
	}
	
}
//鎻愬彇鎺ュ彛,鐩戝惉 閱掓潵 浜嬩欢
interface WakenUpListener{
	//鍙傛暟鏄啋鏉ヤ簨浠�鑰屼笉鐢ㄥ叿浣撶殑灏忓绫�鎻愰珮閲嶇敤搴�鍏朵粬浜洪啋鏉ヤ篃鍙互浣跨敤姝や簨浠�
	public void actionToWakenUp(WakenUpEvent wakenUpEvent);
}


//鐖风埛绫�閱掓潵涔嬪悗鍔ㄤ綔鍜岀埗浜蹭笉鍚�
class GrandFather implements WakenUpListener{
	public void actionToWakenUp(WakenUpEvent event){
		System.out.println("GrandFather 鐨勭洃鍚�鏂规硶");
	}
}

//鐖朵翰绫�閱掓潵涔嬪悗鍔ㄤ綔鏄杺
class Dad implements WakenUpListener{
	public void actionToWakenUp(WakenUpEvent wakenUpEvent) {
		System.out.println("Dad 鐨勭洃鍚�鏂规硶");
	}
}

public class Test3 {
	public static void main(String[] args) {
		//浠庨厤缃枃浠惰鍙栫被
		Properties properties = new Properties();
		try {
//			properties.load(Test3.class.getClassLoader().
//					getResourceAsStream("observer.properties"));
			properties.load(new FileInputStream("observer.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] classNames = properties.getProperty("observers").split(",");
		/* 鍒涘缓灏忓鍜屽彂浜茬殑瀵硅薄涔嬪悗,涓哄皬瀛╂坊鍔犱竴涓洃鍚�-鐖朵翰
		 * addWakenUpListener 鏂规硶鍙傛暟鏄洃鍚�鎺ュ彛,鎵�互 
		 * 鐖朵翰蹇呴』瀹炵幇浜嗙洃鍚帴鍙�*/
		Child c = new Child();
		for(String ss : classNames){
			try {
				c.addWakenUpListener((WakenUpListener)Class.forName(ss).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		new Thread(c).start();
	}

}
