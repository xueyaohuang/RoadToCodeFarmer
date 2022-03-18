class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        int groups = n / k;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        while (groups > 0) {
            Integer smallest = map.firstKey();
            for (int i = smallest; i < smallest + k; i++) {
                if (!map.containsKey(i)) {
                    return false;
                }
                if (map.get(i) > 1) {
                    map.put(i, map.get(i) - 1);
                } else {
                    map.remove(i);
                }
            }
            groups--;
        }
        return true;
    }
}
