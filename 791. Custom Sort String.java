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
