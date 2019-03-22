// O(N), max N=3000 in this problem
class RecentCounter {
    
    private Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }
    
    public int ping(int t) {
        while (!queue.isEmpty() && t - queue.peek() > 3000) {
            queue.poll();
        }
        queue.offer(t);
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
 
 // lg(N), N is the size of the arraylist. N can be quite large, larger than 3000
 class RecentCounter {
    
    private List<Integer> list;

    public RecentCounter() {
        list = new ArrayList<>();
    }
    
    public int ping(int t) {
        list.add(t);
        int idx = Collections.binarySearch(list, t - 3000);
        if (idx < 0) {
            idx = -idx - 1;
        }
        return list.size() - idx;
    }
}
