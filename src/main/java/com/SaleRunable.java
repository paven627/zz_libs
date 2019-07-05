package com;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class SaleRunable implements Runnable{
    String productKey="iphone8";//监视的key 当前秒杀商品的数量  
    Jedis jedis = new Jedis("localhost");
    String userName;
    public SaleRunable(String userName){
        this.userName = userName;
    }

    @Override
    public void run(){
        //商品的key  ， 秒杀有个数量
        //watch 监视一个key，当事务执行之前这个key发生了改变，事务会被打断
        jedis.watch(productKey);
        String value = jedis.get(productKey);
        int num = Integer.valueOf(value);
        System.out.println(num);
        //这次秒杀的商品是100个iphone8 
        if(num<=100&&num>=1){
            //开启事务
            Transaction tx = jedis.multi();
            //减少一个商品数量
            tx.incrBy(productKey, -1);
            //提交事务，如果商品数量发生了改动 则会返回null
            List<Object> list = tx.exec();
            if(list==null || list.size()==0){
                System.out.println(userName+"商品抢购失败！");
            }else{
                for(Object success : list){
                    System.out.println(userName+"("+success.toString()+")商品抢购成功,当前抢购成功的人数是："+(1-(num-100)));
                }
            }
        }else{
            System.out.println(userName+"商品已经被抢完了");
        }
        jedis.close();
    }

}