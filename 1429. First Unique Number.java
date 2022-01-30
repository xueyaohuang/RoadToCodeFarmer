class FirstUnique {
    
    Map<Integer, Integer> map;
    // queue里是目前所有的Unique Number，queue的FIFO特性可以用来获得First Unique Number
    Queue<Integer> queue;

    public FirstUnique(int[] nums) {
        map = new HashMap<>();
        queue = new LinkedList<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                queue.offer(n);
            }
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
    }
    
    public int showFirstUnique() {
        while (!queue.isEmpty() && map.getOrDefault(queue.peek(), 0) > 1) {
            queue.poll();
        }
        return queue.isEmpty() ? -1 : queue.peek();
    }
    
    public void add(int value) {
        if (!map.containsKey(value)) {
                queue.offer(value);
        }
        map.put(value, map.getOrDefault(value, 0) + 1);
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
