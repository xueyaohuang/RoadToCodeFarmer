// O(nlogn) time where n == hand.length, because in the worst case all n elements are distinct so
// inserting n elements into a TreeMap costs O(nlogn) time, and it costs O(nlogn) time to remove
// all elements again while creating the groups.
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        int groups = n / groupSize;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        while (groups > 0) {
            Integer smallest = map.firstKey();
            for (int i = smallest; i < smallest + groupSize; i++) {
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
