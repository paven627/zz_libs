
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class weightting {
    public static void main(String[] args) {
        new weightting().test();
    }

    public void test() {
//        Pair<String, Integer> pair1 = new Pair<>("1", 1);
//        Pair<String, Integer> pair2 = new Pair<>("2", 3);
//        Pair<String, Integer> pair3 = new Pair<>("3", 5);
//        Pair<String, Integer> pair4 = new Pair<>("4", 5);
//        List<Pair<String, Integer>> list = new ArrayList<>();
        AbstractMap.SimpleEntry<String, Integer> pair1 = new AbstractMap.SimpleEntry<>("1", 1);
        AbstractMap.SimpleEntry<String, Integer> pair2 = new AbstractMap.SimpleEntry<>("2", 2);
        AbstractMap.SimpleEntry<String, Integer> pair3 = new AbstractMap.SimpleEntry<>("3", 4);
        AbstractMap.SimpleEntry<String, Integer> pair4 = new AbstractMap.SimpleEntry<>("4", 3);
        List<AbstractMap.SimpleEntry<String, Integer>> list = new ArrayList<>();
        list.add(pair1);
        list.add(pair2);
        list.add(pair3);
        list.add(pair4);
        WeightRandom<String, Integer> random = new WeightRandom<>(list);

        String num;
        HashMap<String, Integer> totalCount = new HashMap<>();
        int count = 10000000;
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            num = random.random();
            if (totalCount.containsKey(num)) {
                totalCount.put(num, totalCount.get(num) + 1);
            } else {
                totalCount.put(num, 1);
            }
        }
        System.out.println(System.currentTimeMillis() - l1);
        System.out.println(totalCount.toString());
        for (Integer value : totalCount.values()) {
            System.out.println(value + ", " + value.doubleValue() / (double) count);
        }
    }
}


// class WeightRandom<K, V extends Number> {
//    private TreeMap<Integer, K> weightMap = new TreeMap<>();
//
//    public WeightRandom( List<Pair<K, V>> list) {
//        // 先排除权重为0的项
//        Iterator<Pair<K, V>> it = list.iterator();
//        while (it.hasNext()) {
//            if (it.next().snd.intValue() == 0) {
//                it.remove();
//            }
//        }
//
//        for (Pair<K, V> pair : list) {
//            int lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey();// 统一转为double
//            this.weightMap.put(pair.snd.intValue() + lastWeight, pair.fst);// 权重累加
//        }
//    }
//
//    public K random() {
//        int randomWeight = ThreadLocalRandom.current().nextInt(weightMap.lastKey()) + 1;
////        double randomWeight = this.weightMap.lastKey() * Math.random();
//        SortedMap<Integer, K> tailMap = this.weightMap.tailMap(randomWeight, true);
//        return this.weightMap.get(tailMap.firstKey());
//    }
//}


 class WeightRandom<K, V extends Number> {
    private TreeMap<Double, K> weightMap = new TreeMap<>();

    public WeightRandom( List<AbstractMap.SimpleEntry<K, V>> list) {
        // 先排除权重为0的项
        Iterator<AbstractMap.SimpleEntry<K, V>> it = list.iterator();
        while (it.hasNext()) {
            AbstractMap.SimpleEntry<K, V> next = it.next();
            V snd = next.getValue();
            if (snd.doubleValue() == 0) {
                it.remove();
            }
        }

        for (AbstractMap.SimpleEntry<K, V> pair : list) {
            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey();// 统一转为double
            this.weightMap.put(pair.getValue().doubleValue() + lastWeight, pair.getKey());// 权重累加
        }
    }
//weightMap , key是权重区间的结束值,0-1, 1.1-2
    public K random() {
        // 取 0 - 0.99 * 开始区间, 在权重值和之内
        double randomWeight = this.weightMap.lastKey() * Math.random();
        // key 大于给定key的子集
        SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight, false);
        return this.weightMap.get(tailMap.firstKey());
    }
}