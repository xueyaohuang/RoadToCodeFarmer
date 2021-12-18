// o(n), bucket sort, since we know k is less than arr.length+1
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int[] freq = new int[arr.length + 1];
        for (int i : map.values()) {
            freq[i]++;
        }
        int res = map.size();
        System.out.println(res);
        for (int i = 1; i < freq.length; i++) {
            if (freq[i] > 0) {
                if (k >= i * freq[i]) {
                    k -= i * freq[i];
                    res -= freq[i];
                } else {
                    return res - k / i;
                }
            }
        }
        return res;
    }
}

class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }
        int res = map.size();
        while (k > 0) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            k -= entry.getValue();
            if (k >= 0) {
                res--;
            }
        }
        return res;
    }
}
