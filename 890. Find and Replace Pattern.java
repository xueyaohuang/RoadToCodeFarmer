class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        int len = pattern.length();
        for (String word : words) {
            if (word.length() != len) {
                continue;
            }
            Map<Character, Character> mapw = new HashMap<>();
            Map<Character, Character> mapp = new HashMap<>();
            int i = 0;
            for (; i < len; i++) {
                char cw = word.charAt(i);
                char cp = pattern.charAt(i);
                if (!mapw.containsKey(cw)) {
                    mapw.put(cw, cp);
                }
                if (!mapp.containsKey(cp)) {
                    mapp.put(cp, cw);
                }
                if (mapw.get(cw) != cp || mapp.get(cp) != cw) {
                    break;
                }
            }
            if (i == len) {
                res.add(word);
            }
        }
        return res;
    }
}

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] norm = normalize(pattern);
        List<String> res = new ArrayList<String>();
        for (String word : words) {
            if (Arrays.equals(normalize(word), norm)) {
                res.add(word);
            }
        }
        return res;
    }

    public int[] normalize(String word) {
        HashMap<Character, Integer> map = new HashMap<>();
        int len = word.length();
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            map.putIfAbsent(c, map.size());
            res[i] = map.get(c);
        }
        return res;
    }
}
