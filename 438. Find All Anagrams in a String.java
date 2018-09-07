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
        if (s == null || p == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] map = new int[26];
        for (char c : p.toCharArray()) { // p的map，不是s的map
            map[c - 'a']++;
        }
        int left = 0;
        int right = 0;
        int count = 0;
        int lenS = s.length();
        int lenP = p.length();
        for (; right < lenS; right++) {
            char cright = s.charAt(right);
            // 先找到一个window，这个window内包含了p中所有的letter
            // map[cright - 'a'] > 0 说明了，在s中又找到一个p中也有的letter
            // 如果cright在p中，那么map[cright - 'a']在找全p中letter之前一直是大于0的。
            if (map[cright - 'a'] > 0) {
                count++;
            }
            map[cright - 'a']--;
            // count == lenP说明p中的letter在当前left到right的window中找全了，但是不一定连续构成Anagrams，还要检查window的长度
            while (count == lenP) {
                if (right - left + 1 == lenP) {
                    res.add(left);
                }
                char cleft = s.charAt(left);
                // 由于left要向前移动，需要从count中减去移动走的p中的letter
                if (map[cleft - 'a'] >= 0) {
                    count--;
                }
                map[cleft - 'a']++;
                left++;
            }
        }
        return res;
    }
}
