package test.java.Resilience4j;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "hello";
    }
}
