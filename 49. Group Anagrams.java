class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            char[] carr = s.toCharArray();
            Arrays.sort(carr);
            String flag = new String(carr);  
            map.putIfAbsent(flag, new ArrayList<>());
            map.get(flag).add(s);
        }
        
        return new ArrayList<>(map.values());
    }
}
