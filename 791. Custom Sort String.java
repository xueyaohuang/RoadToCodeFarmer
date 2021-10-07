class Solution {
    public String customSortString(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (char c : T.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : S.toCharArray()) {
            if (map.containsKey(c)) {
                int freq = map.get(c);
                for (int i = 0; i < freq; i++) {
                    sb.append(c);
                }
            }
            map.remove(c);
        }
        for (char c : map.keySet()) {
            int freq = map.get(c);
            for (int i = 0; i < freq; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

class Solution {
    public String customSortString(String order, String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c -  'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            if (freq[c - 'a'] != 0) {
                for (int i = 0; i < freq[c - 'a']; i++) {
                    sb.append(c);
                }
            }
            freq[c - 'a'] = 0;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                for (int j = 0; j < freq[i]; j++) {
                    sb.append((char)(i + 'a'));
                }
            }
        }
        return sb.toString();
    }
}
