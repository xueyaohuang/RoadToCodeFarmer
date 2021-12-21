class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : time) {
            int reminder = t % 60;
            // 不能是 map.containsKey(60 - reminder)，这样会漏掉reminder是0的情况，比如input是[60, 60, 60]
            if (map.containsKey((60 - reminder) % 60)) {
                count += map.get((60 - reminder) % 60);
            }
            map.put(reminder, map.getOrDefault(reminder, 0) + 1);
        }
        return count;
    }
}
