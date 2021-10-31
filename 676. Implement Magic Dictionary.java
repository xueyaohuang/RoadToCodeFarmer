// use * for string matching
// 问题在于如果dictionary里有searchWord怎么处理, 比如
// 在["hello", "leetcode"]里搜 "hello"是false，但是在["hello", "hallo", "leetcode"]里搜 "hello"是true
// 如果dictionary里有searchWord，需要searchWord的Wildcard在wordDict中出现2次
class MagicDictionary {
    Set<String> origin;
    Map<String, Integer> wordDict;
    Set<Integer> lengths;
    public MagicDictionary() {
        wordDict = new HashMap<>();
        lengths = new HashSet<>();
        origin = new HashSet<>();
    }
    
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            origin.add(word);
            lengths.add(word.length());
            char[] wordArr = word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {
                char c = wordArr[i];
                wordArr[i] = '*';
                String cur = String.valueOf(wordArr);
                wordDict.put(cur, wordDict.getOrDefault(cur, 0) + 1);
                wordArr[i] = c;
            }
        }
    }
    
    public boolean search(String searchWord) {
        if (!lengths.contains(searchWord.length())) {
            return false;
        }
        char[] wordArr = searchWord.toCharArray();
        for (int i = 0; i < wordArr.length; i++) {
            char c = wordArr[i];
            wordArr[i] = '*';
            String cur = String.valueOf(wordArr);
            if (wordDict.containsKey(cur)) {
                if (wordDict.get(cur) > 1) {
                    return true;
                }
                if (!origin.contains(searchWord)) {
                    return true;
                }
            }
            wordArr[i] = c;
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
