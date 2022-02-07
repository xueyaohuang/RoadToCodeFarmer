class Solution {
    public int longestPalindrome(String s) {
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hs.contains(s.charAt(i))) {
                hs.remove(s.charAt(i));
                count++;
            } else {
                hs.add(s.charAt(i));
            }
        }
        return hs.isEmpty() ? 2 * count : 2 * count + 1;
    }
}

class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int res = 0;
        boolean hasUnpairedLetter = false;
        for (char c : map.keySet()) {
            if (map.get(c) % 2 == 0) {
                res += map.get(c);
            } else if (map.get(c) > 2) {
                if (!hasUnpairedLetter) {
                    res += map.get(c);
                    hasUnpairedLetter = true;
                } else {
                    res += map.get(c) - 1;
                }
            } else if (!hasUnpairedLetter) {
                res++;
                hasUnpairedLetter = true;
            }
        }
        return res;
    }
}
