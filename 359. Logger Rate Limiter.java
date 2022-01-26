class Logger {

    // 这个方法的问题是map会越来越大，包含很多已经超时的message信息
    Map<String, Integer> map;
    final int INTERVAL = 10;
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<String, Integer>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (map.containsKey(message)) {
            int last = map.get(message);
            if (timestamp - last < INTERVAL) 
                return false;
        }
        map.put(message, timestamp);
        return true;
    }
}


// map/set with experiation，把map中超时的都删掉
class Log {
    int time;
    String msg;
    public Log(int time, String msg) {
        this.time = time;
        this.msg = msg;
    }
}

// amortized time complexity should be O(1) -- since words are only going in and out of the queue once.
// could have a situation where you spend a lot of time in a single function call --
// which gives us the worst case scenario of O(n), n := # unique words in last 10 seconds. 
// it gets big if there are a lot of unique words in the last 10 seconds -- no solution can get around this. 
class Logger {

    Queue<Log> queue;
    Set<String> set;
    /** Initialize your data structure here. */
    public Logger() {
        queue = new LinkedList<>();
        set = new HashSet<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!queue.isEmpty() && timestamp - 10 >= queue.peek().time) {
            Log timeExceedLog = queue.poll();
            set.remove(timeExceedLog.msg);
        }
        if (set.contains(message)) {
            return false;
        } else {
            queue.offer(new Log(timestamp, message));
            set.add(message);
            return true;
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

// https://leetcode.com/problems/logger-rate-limiter/discuss/391558/Review-of-four-different-solutions%3A-HashMap-Two-Sets-Queue-with-Set-Radix-buckets-(Java-centric)
