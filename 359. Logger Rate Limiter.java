class Logger {

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

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
