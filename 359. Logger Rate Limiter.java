class Logger {

    // 这个方法的问题是map会越来越大，包含很多已经超时的message信息
    Map<String, Integer> map;
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<String, Integer>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message)) {
            map.put(message, timestamp);
            return true;
        }
        else {
            int lastPrint = map.get(message);
            if (timestamp - lastPrint < 10) {
                return false;
            }
            else {
                map.put(message, timestamp);
                return true;
            }
        }
        // another logtic
//         if (map.containsKey(message)) {
//             int last = map.get(message);
//             if (timestamp - last < 10) 
//                 return false;
//         }
//         map.put(message, timestamp);
//         return true;
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
