class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        int lens = s.length();
        int lent = t.length();
        int count = 0;
        
        if (lens == 0) {
            return true;
        }
        
        for (int i = 0; i < lent; i++) {
            if (t.charAt(i) == s.charAt(count)) {
                count++;
            }
            if (count == lens) {
                return true;
            }
        }
        return false;
    }
}

// follow up question
class Solution {
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new ArrayList<>());
            }
            map.get(c).add(i);
        }
        int cur = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            cur = searchIndex(cur, map.get(c));
            if (cur == -1) {
                return false;
            }
        }
        return true;
    }
    
    private int searchIndex(int cur, List<Integer> list) {
        int start = 0, end = list.size() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) <= cur) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return list.get(start) > cur ? list.get(start) : -1;
    }
}
