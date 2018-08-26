class WordDistance {
    
    Map<String, List<Integer>> map = new HashMap<>();

    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            map.putIfAbsent(w, new ArrayList<Integer>());
            map.get(w).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int i = 0;
        int j = 0;
        int distance = Integer.MAX_VALUE;
        while (i < l1.size() && j < l2.size()) {
            int idx1 = l1.get(i);
            int idx2 = l2.get(j);
            distance = Math.min(distance, Math.abs(idx1 - idx2));
            if (idx1 < idx2) {
                i++;
            }
            else {
                j++;
            }
        }
        return distance;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
