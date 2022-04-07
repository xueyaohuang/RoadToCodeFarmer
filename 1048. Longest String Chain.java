class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> map = new HashMap<>();
        int res = 1;
        for (String w : words) {
            map.put(w, 1);
            for (int i = 0; i < w.length(); i++) {
                StringBuilder sb = new StringBuilder(w);
                sb.deleteCharAt(i);
                String s = sb.toString();
                if (map.containsKey(s) && map.get(s) + 1 > map.get(w)) {
                    map.put(w, map.get(s) + 1);
                }
            }
            res = Math.max(res, map.get(w));
        }
        return res;
    }
}
