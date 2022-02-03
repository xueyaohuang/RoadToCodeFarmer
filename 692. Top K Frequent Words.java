// O(nlogk) time & O(n) space
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
        // You can't compare two Integer with a simple ==. 
        // You should use equals(). == can be used only when the Integer is between -127 ~ 128.
        // for the comparator, why is b.getKey().compareTo(a.getKey()) being returned instead of a.getKey().compareTo(b.getKey())?
        // the solution is using min-heap, which keeps heap's size to K. With min-heap, the top K elements are actually at the bottom of the heap.
        // Hence, at case where a.getValue() == b.getValue(), we want to add "abd" before "abc" into the min-heap,
        // so that when executing line result.add(0, pq.poll().getKey());, abc will go before abd in the result. 
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }
}


// 最差O(nlgn), 因为有可能每个word出现一次，sort一个bucket就是O(nlgn)
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String>[] buckets = new List[words.length + 1];
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            String word = entry.getKey();
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(word);
        }

        List<String> res = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && k > 0; i--) {
            if (buckets[i] != null) {
                Collections.sort(buckets[i]);
                for (String word : buckets[i]) {
                    res.add(word);
                    k--;
                    if (k == 0) {
                        break;
                    }
                }
            }
        }
        return res;
    }
}
