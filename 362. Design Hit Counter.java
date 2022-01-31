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
        for (int i = 0; i < 300; i++) {
            if (timestamp - time[i] < 300) {
                count += hit[i];
            }
        }
        return count;
    }
}

// scale method
class HitCounter {
    
    Deque<Time> queue;
    int hit;

    /** Initialize your data structure here. */
    public HitCounter() {
        queue = new ArrayDeque<>();
        hit = 0;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek().time >= 300) {
            hit -= queue.poll().count;
        }
        if (!queue.isEmpty() && queue.peekLast().time == timestamp) {
            queue.peekLast().count++;
            hit++;
        } else {
            queue.offer(new Time(timestamp));
            hit++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek().time >= 300) {
            hit -= queue.poll().count;
        }
        return hit;
    }
}

class Time {
    int time;
    int count;
    public Time(int time) {
        this.time = time;
        this.count = 1;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
