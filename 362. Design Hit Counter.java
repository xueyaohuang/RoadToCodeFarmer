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
        // hit时也可以不清理queue中过时的hit
        // 好处是hit是复杂度是O(1）
        // 坏处是queue的size会更大
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

// scale method, 循环数组
// 组要是要把相同时间的hit聚在一起
class HitCounter {
    
    int[] time;
    int[] hits;
    final int DURATION = 300;

    public HitCounter() {
        time = new int[300];
        hits = new int[300];
    }
    
    public void hit(int timestamp) {
        int idx = timestamp % DURATION;
        if (timestamp == time[idx]) {
            hits[idx]++;
        } else {
            time[idx] = timestamp;
            hits[idx] = 1;
        }
    }
    
    public int getHits(int timestamp) {
        int count = 0;
        for (int i = 0; i < DURATION; i++) {
            if (timestamp - time[i] < DURATION) {
                count += hits[i];
            }
        }
        return count;
    }
}

// scale method
class HitCounter {
    // 涉及到在两端操作，要用deque
    Deque<Hit> dq;
    int hits;
    final int DURATION = 300;

    public HitCounter() {
        dq = new ArrayDeque<>();
        hits = 0;
    }
    
    public void hit(int timestamp) {
        while (!dq.isEmpty() && dq.peekFirst().time <= timestamp - DURATION) {
            hits -= dq.pollFirst().hits;
        }
        if (!dq.isEmpty() && dq.peekLast().time == timestamp) {
            dq.peekLast().hits++;
        } else {
            dq.offerLast(new Hit(timestamp));
        }
        hits++;
    }
    
    public int getHits(int timestamp) {
        while (!dq.isEmpty() && dq.peekFirst().time <= timestamp - DURATION) {
            hits -= dq.pollFirst().hits;
        }
        return hits;
    }
}

class Hit {
    int time;
    int hits;
    public Hit(int timestamp) {
        this.time = timestamp;
        hits = 1;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
