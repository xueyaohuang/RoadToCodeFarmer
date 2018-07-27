class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) {
            return false;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String[] strs : pairs) {
            map.putIfAbsent(strs[0], new ArrayList<String>());
            map.get(strs[0]).add(strs[1]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            } 
            if (!(map.containsKey(words1[i]) && map.get(words1[i]).contains(words2[i])) && !(map.containsKey(words2[i]) && map.get(words2[i]).contains(words1[i]))) {
                return false;
            } 
        }
        return true;
    }
}

class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) {
            return false;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String[] strs : pairs) {
            map.putIfAbsent(strs[0], new ArrayList<String>());
            map.putIfAbsent(strs[1], new ArrayList<String>());
            map.get(strs[0]).add(strs[1]);
            map.get(strs[1]).add(strs[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            } 
            if (!(map.containsKey(words1[i]) && map.get(words1[i]).contains(words2[i]))) {
                return false;
            } 
        }
        return true;
    }
}
