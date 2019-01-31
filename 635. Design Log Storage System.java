class LogSystem {
    Map<String, Integer> log;
    List<String> list;
    int[] poses;
     
    public LogSystem() {
        log = new TreeMap<>(); 
        list = Arrays.asList(new String[]{"Year", "Month", "Day", "Hour", "Minute", "Second"});
        poses = new int[]{4, 7, 10, 13, 16, 19}; 
    }
    
    public void put(int id, String timestamp) {
        log.put(timestamp, id);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new LinkedList<>();
        int pos = poses[list.indexOf(gra)];
        for (String str : log.keySet()) {
            if (str.substring(0, pos).compareTo(s.substring(0, pos)) < 0) {
                continue;
            }
            if (str.substring(0, pos).compareTo(e.substring(0, pos)) > 0) {
                break;
            }
            res.add(log.get(str));
        }
        return res; 
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
