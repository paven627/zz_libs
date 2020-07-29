package test.lambda;

import java.util.Comparator;

/**
 * @author dengbin
 */
public class TestLambda {
    public static void main(String[] args) {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

    }
}
