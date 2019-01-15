// 这个方法不scale：if the number of hits per second could be very large。
// queue的size会很大，hit和getHits的复杂度是O(m)m是每秒的hit个数
class HitCounter {
    
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public HitCounter() {
        queue = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            queue.poll();
        }
        queue.offer(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            queue.poll();
        }
        return queue.size();
    }
}

class HitCounter {
    
    int[] hit;
    int[] time;

    /** Initialize your data structure here. */
    public HitCounter() {
        hit = new int[300];
        time = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int idx = timestamp % 300;
        if (time[idx] == timestamp) {
            hit[idx]++;
        } else {
            time[idx] = timestamp;
            hit[idx] = 1;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int count = 0;
        int idx = timestamp % 300;
        for (int i = 0; i < 300; i++) {
            if (timestamp - time[i] < 300) {
                count += hit[i];
            }
        }
        return count;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
