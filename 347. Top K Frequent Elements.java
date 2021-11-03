class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxFreq = 0;
        for (int i : nums) {
            int freq = map.getOrDefault(i, 0) + 1;
            map.put(i, freq);
            maxFreq = Math.max(maxFreq, freq);
        }
        List<Integer>[] freqs = new List[maxFreq + 1];
        for (int i : map.keySet()) {
            int freq = map.get(i);
            if (freqs[freq] == null) {
                freqs[freq] = new ArrayList<>();
            }
            freqs[freq].add(i);
        }
        int[] res = new int[k];
        List<Integer> list = new ArrayList<>();
        for (int i = freqs.length - 1; i >= 0 && k > 0; i--) {
            if (freqs[i] != null) {
                list.addAll(freqs[i]);
                k -= freqs[i].size();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        List<Integer>[] freqs = new List[nums.length +1];
        for (int n : map.keySet()) {
            int freq = map.get(n);
            if (freqs[freq] == null) {
                freqs[freq] = new ArrayList<>();
            }
            freqs[freq].add(n);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length; i >= 0 && res.size() < k; i--) {
            if (freqs[i] != null) {
                res.addAll(freqs[i]);
            }     
        }
        return res;
    }
}

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.add(entry);
        }
        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            res.add(entry.getKey());
        }
        return res;
    }
}

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (a, b) -> b.getValue() - a.getValue());
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).getKey());
        }
        return res;
    }
}

/*
TreeMap can be a bit handy when we only need to store unique elements in a sorted order. Java.util.TreeMap uses a red-black tree
in the background which makes sure that there are no duplicates; additionally it also maintains the elements in a sorted order.
*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        TreeMap<Integer, List<Integer>> freqs = new TreeMap<>();
        for (int n : map.keySet()) {
            int freq = map.get(n);
            if (!freqs.containsKey(freq)) {
                freqs.put(freq, new ArrayList<>());
            }
            freqs.get(freq).add(n);
        }
        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, List<Integer>> entry = freqs.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }
}

