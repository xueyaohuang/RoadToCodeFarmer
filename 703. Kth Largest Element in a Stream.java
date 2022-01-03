class KthLargest {
    
    PriorityQueue<Integer> pq;
    int size;

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>(k);
        size = k;
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }
    
    public int add(int val) {
        pq.offer(val);
        if (pq.size() > size) {
            pq.poll();
        }
        return pq.peek();
    }
}

class KthLargest {
    
        PriorityQueue<Integer> pq;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<>(k);
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int n) {
            if (pq.size() < k) {
                pq.offer(n);
            } else if (pq.peek() < n) {
                pq.poll();
                pq.offer(n);
            }
            return pq.peek();
        }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
