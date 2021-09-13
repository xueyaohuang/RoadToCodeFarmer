class Visit {
    String username;
    String website;
    int timestamp;
    public Visit(String username, String website, int timestamp) {
        this.username = username;
        this.website = website;
        this.timestamp = timestamp;
    }
}      

class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Visit> visits = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {
            visits.add(new Visit(username[i], website[i], timestamp[i]));
        }
        Map<String, List<Visit>> userVisits = new HashMap<>();
        for (Visit v : visits) {
            userVisits.putIfAbsent(v.username, new ArrayList<>());
            userVisits.get(v.username).add(v);
        }
        Map<String, Integer> freqMap = new HashMap<>();
        for (String user : userVisits.keySet()) {
            List<Visit> vs = userVisits.get(user);
            if (vs.size() < 3) {
                continue;
            }
            Collections.sort(vs, (a, b) -> a.timestamp - b.timestamp);
            Set<String> patterns = getPatterns(vs);
            for (String pattern : patterns) {
                freqMap.put(pattern, freqMap.getOrDefault(pattern, 0) + 1);
            }
        }
        int maxFreq = 0;
        String mvp = "";
        for (String pattern : freqMap.keySet()) {
            int freq = freqMap.get(pattern);
            if (freq > maxFreq) {
                maxFreq = freq;
                mvp = pattern;
            } else if (freq == maxFreq) {
                if (pattern.compareTo(mvp) < 0) {
                    mvp = pattern;
                }
            }
        }
        return Arrays.asList(mvp.split(" "));
    }
    
    // 注意
    // 1. 每个pattern对一个user只算一次
    // 2. website不需要是三个连续的，所以这里是三重for loop
    private Set<String> getPatterns(List<Visit> vs) {
        if (vs.size() < 3) {
            return new HashSet<>();
        }
        Set<String> res = new HashSet<>();
        for (int i = 0; i < vs.size() - 2; i++) {
            for (int j = i + 1; j < vs.size() - 1; j++) {
                for (int k = j + 1; k < vs.size(); k++) {
                    String pattern = vs.get(i).website + " " + vs.get(j).website + " " + vs.get(k).website;
                    res.add(pattern);
                }
            }
        }
        return res;
    }
}
