package test.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJson {
	public static void main(String[] args) {

		int num = 50;
		String s = "{\"campaign_id\":391,\"items\":[{\"attributes\":{\"spot_id\":13783,\"spot_id_str\":\"1LL\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1LL&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1LL&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13787,\"spot_id_str\":\"1LP\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1LP&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1LP&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13792,\"spot_id_str\":\"1LU\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1LU&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1LU&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13795,\"spot_id_str\":\"1LX\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1LX&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1LX&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13799,\"spot_id_str\":\"1Lb\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lb&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lb&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13804,\"spot_id_str\":\"1Lg\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lg&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lg&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13808,\"spot_id_str\":\"1Lk\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lk&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lk&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13812,\"spot_id_str\":\"1Lo\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lo&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lo&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13816,\"spot_id_str\":\"1Ls\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Ls&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Ls&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13820,\"spot_id_str\":\"1Lw\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lw&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Lw&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13824,\"spot_id_str\":\"1M+\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1M+&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1M+&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13828,\"spot_id_str\":\"1M2\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1M2&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1M2&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13832,\"spot_id_str\":\"1M6\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1M6&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1M6&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13836,\"spot_id_str\":\"1MA\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MA&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MA&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13840,\"spot_id_str\":\"1ME\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1ME&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1ME&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13844,\"spot_id_str\":\"1MI\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MI&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MI&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13848,\"spot_id_str\":\"1MM\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MM&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MM&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13852,\"spot_id_str\":\"1MQ\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MQ&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MQ&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13856,\"spot_id_str\":\"1MU\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MU&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MU&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13860,\"spot_id_str\":\"1MY\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MY&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1MY&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13864,\"spot_id_str\":\"1Mc\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mc&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mc&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13867,\"spot_id_str\":\"1Mf\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mf&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mf&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13871,\"spot_id_str\":\"1Mj\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mj&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mj&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13875,\"spot_id_str\":\"1Mn\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mn&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mn&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13879,\"spot_id_str\":\"1Mr\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mr&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mr&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13882,\"spot_id_str\":\"1Mu\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mu&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mu&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13885,\"spot_id_str\":\"1Mx\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mx&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1Mx&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13888,\"spot_id_str\":\"1N+\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1N+&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1N+&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13891,\"spot_id_str\":\"1N1\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1N1&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1N1&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}},{\"attributes\":{\"spot_id\":13895,\"spot_id_str\":\"1N5\"},\"tags\":{\"clk_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1N5&ro=sm&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\",\"imp_url\":\"http://g.cn.miaozhen.com/x.gif?k=391&p=1N5&rt=2&ns=[M_ADIP]&ni=[M_IESID]&v=[M_LOC]&o=\"}}],\"status\":200}";
		long start = System.currentTimeMillis();
		FastJson test = new FastJson();
		for (int i = 0; i < num; i++) {
			JSONObject a = test.alibaba(s);
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时: " + (end - start));

		
		long start2 = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			net.sf.json.JSONObject a = test.sfParse(s);
		}
		long end2 = System.currentTimeMillis();
		System.out.println("耗时: " + (end2 - start2));
		
		
	}

	private JSONObject alibaba(String json) {
		return JSON.parseObject(json);
	}

	private net.sf.json.JSONObject sfParse(String json) {
		return net.sf.json.JSONObject.fromObject(json);
	}

}
