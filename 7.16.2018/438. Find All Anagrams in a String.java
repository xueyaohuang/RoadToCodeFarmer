class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int count = map.size();
        while (right < s.length()) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    count--;
                }
            }
            right++;
            while (count == 0) {
                char c1 = s.charAt(left);
                if (map.containsKey(c1)) {
                    map.put(c1, map.get(c1) + 1);
                    if (map.get(c1) > 0) {
                        count++;
                    }
                }
                if (right - left == p.length()) {
                    res.add(left);
                }
                left++;
            }
        }
        return res;
    }
}

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] map = new int[256];
        for (char c : p.toCharArray()) {
            map[c]++;
        }
        int left = 0;
        int count =0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map[c]-- > 0) {
                count++;
            }
            while (count == p.length()) {
                char c1 = s.charAt(left);
                if (count == right - left + 1) {
                    res.add(left);
                }
                if (map[c1]++ >= 0) {
                    count--;
                }
                left++;
            }
        }
        return res;
    }
}