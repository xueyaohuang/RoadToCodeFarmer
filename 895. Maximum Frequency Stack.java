// push O(1), pop O(1)
class FreqStack {
    
    Map<Integer, Integer> freq;
    List<Deque<Integer>> list;
    int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        list = new ArrayList<>();
        maxFreq = 0;
    }
    
    public void push(int val) {
        int curFreq = freq.getOrDefault(val, 0) + 1;
        freq.put(val, curFreq);
        maxFreq = Math.max(curFreq, maxFreq);
        if (list.size() < maxFreq) {
            list.add(new ArrayDeque<>());
        }
        list.get(curFreq - 1).push(val);
    }
    
    public int pop() {
        int res = list.get(maxFreq - 1).pop();
        if (list.get(maxFreq - 1).isEmpty()) {
            list.remove(maxFreq - 1);
            maxFreq--;
        }
        freq.put(res, freq.get(res) - 1);
        if (freq.get(res) == 0) {
            freq.remove(res);
        }
        return res;
    }
}

// push O(lgn), pop O(lgn)
class FreqStack {
    
    Map<Integer, Integer> freq;
    PriorityQueue<int[]> pq;
    // 当前val进入FreqStack时是第几个
    int idx;

    public FreqStack() {
        freq = new HashMap<>();
        pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[2] - a[2] : b[1] - a[1]);
        idx = 0;
    }
    
    public void push(int val) {
        freq.put(val, freq.getOrDefault(val, 0) + 1);
        pq.offer(new int[]{val, freq.get(val), idx++});
    }
    
    public int pop() {
        int res = pq.poll()[0];
        freq.put(res, freq.get(res) - 1);
        if (freq.get(res) == 0) {
            freq.remove(res);
        }
        return res;
    }
}
