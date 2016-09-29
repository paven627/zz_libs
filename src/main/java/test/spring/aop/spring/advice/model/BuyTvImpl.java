package test.spring.aop.spring.advice.model;


public class BuyTvImpl implements IBuyTV {

	@Override
	public void buyTv(String customer, String tv) throws Exception {
		if (tv.equals("LG")) {
//			throw new NoSuchTvException("对不起,没有 " + tv + "的电视了");
			throw new Exception("对不起,没有 " + tv + "的电视了");
		}
		System.out.println(customer + " :您购买了一台 " + tv + "电视");
	}

}
