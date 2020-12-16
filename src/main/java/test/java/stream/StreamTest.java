package test.java.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {

    private static List<Article> articles = new ArrayList<Article>();

    @Test
    public void testGroup (){
        Article a1 = new Article("Hello World", "Tom", Arrays.asList("Hello", "World", "Tom"), "CN", "GD");
        Article a2 = new Article("Thank you teacher", "Bruce", Arrays.asList("Thank", "you", "teacher", "Bruce"), "CN",
                "GX");
        Article a3 = new Article("Work is amazing", "Tom", Arrays.asList("Work", "amazing", "Tom"), "CN", "GD");
        Article a4 = new Article("New City", "Lucy", Arrays.asList("New", "City", "Lucy", "Good"), "US", "OT");
        articles.add(a1);
        articles.add(a2);
        articles.add(a3);
        articles.add(a4);

        Map<String, Map<String, List<Article>>> result = articles.stream()
                .collect(Collectors.groupingBy(Article::getCountryCode,
                        Collectors.groupingBy(Article::getProvince)));
        result.forEach((cc, map) -> {
            System.out.println("Country Code is:" + cc);
            map.forEach((pc, list) -> {
                System.out.println("    Province Code is:" + pc);
                list.forEach((article) -> {
                    System.out.println("        Article titile is:" + article.getTitle() + ",author is:"
                            + article.getAuthor());
                });
            });
        });


    }

    public static void testFilter() {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(arr).filter(x -> x > 3 && x < 8).forEach(System.out::println);
    }

    @Test
    public void map() {
        String[] words = new String[]{"Hello", "World"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        a.forEach(System.out::print);
    }

    // forEach, 流式单向的, 打印了就不能进行求和了, 只能使用peek
    // peek, 流中每个元素被消费时都会执行相应操作
    @Test
    public void peek() {
        IntStream range = IntStream.range(1, 10);
        int sum = range.peek(System.out::println).sum();
//        range.forEach(System.out::println);
        System.out.println("sum:" + sum);
    }

    //        对Stream进行截断操作，获取其前N个元素，如果原Stream中包含元素个数小于N，就获取其所有元素。
    @Test
    public void limit() {
        IntStream.range(1, 10).limit(5).forEach(System.out::println);
    }

    String[] arr1 = {"abc", "a", "bc", "abcd"};

    /**
     * Comparator.comparing是一个键提取的功能
     * 以下两个语句表示相同意义
     */
    @Test
    public void testSorted1_() {
        /**
         * 按照字符长度排序
         */
        Arrays.stream(arr1).sorted((x, y) -> {
            if (x.length() > y.length())
                return -1;
            else if (x.length() < y.length())
                return 1;
            else
                return 0;
        }).forEach(System.out::println);

        Arrays.stream(arr1).sorted(Comparator.comparing(String::length)).forEach(System.out::println);
    }

    /**
     * thenComparing
     * 先按照首字母排序
     * 之后按照String的长度排序
     */
    @Test
    public void testSorted3_(){
        Arrays.stream(arr1).sorted(Comparator.comparing(this::com1).thenComparing(String::length)).forEach(System.out::println);
    }
    public char com1(String x){
        return x.charAt(0);
    }
}
