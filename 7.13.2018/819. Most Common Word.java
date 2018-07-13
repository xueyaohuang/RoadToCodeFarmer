class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.replaceAll("[!?',;.]", "").toLowerCase().split("\\s+");
        Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            if (!bannedWords.contains(w)) {
                map.put(w, map.getOrDefault(w, 0) + 1);
            }
        }
        int count = 0;
        String res = ""; // must initialize a string
        for (String w : map.keySet()) {
            if (map.get(w) > count) {
                res = w;
                count = map.get(w);
            }
        }
        return res;
    }
}


class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        String res = ""; // must initialize a string
        paragraph += ".";
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            }
            else if (sb.length() > 0) {
                String w = sb.toString().toLowerCase();
                if (!bannedWords.contains(w)) {
                    map.put(w, map.getOrDefault(w, 0) + 1);
                    if (map.get(w) > count) {
                        res = w;
                        count = map.get(w);
                    }
                }  
                sb = new StringBuilder();
            }
        }
        return res;
    }
}
