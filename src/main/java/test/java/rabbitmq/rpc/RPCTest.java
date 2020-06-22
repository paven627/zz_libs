package test.java.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RPCTest {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        RPCClient rpcClient = new RPCClient();
        System.out.println(rpcClient.call("2"));
    }
 }