class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        if (!constructPath(beginSet, endWord, wordSet, map)) {
            return res;
        }
        List<String> curList = new ArrayList<>();
        curList.add(beginWord);
        generateList(beginWord, endWord, res, curList, map);
        return res;
    }
    
    private boolean constructPath(Set<String> beginSet, String endWord, Set<String> wordSet, Map<String, List<String>> map) {
        if (beginSet.contains(endWord)) {
            return true;
        }
        if (beginSet.isEmpty()) {
            return false;
        }
        wordSet.removeAll(beginSet);
        Set<String> nextBegin = new HashSet<>();
        for (String s : beginSet) {
            map.put(s, new ArrayList<>());
            char[] sc = s.toCharArray();
            for (int i = 0; i < sc.length; i++) {
                char origin = sc[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == origin) {
                        continue;
                    }
                    sc[i] = c;
                    String newStr = new String(sc);
                    if (!wordSet.contains(newStr)) {
                        continue;
                    }
                    nextBegin.add(newStr);
                    map.get(s).add(newStr);
                }
                sc[i] = origin;
            }
        }
        return constructPath(nextBegin, endWord, wordSet, map);
    }
    
    private void generateList(String beginWord, String endWord, List<List<String>> res, List<String> curList, Map<String, List<String>> map) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(curList));
            return;
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String s : map.get(beginWord)) {
            curList.add(s);
            generateList(s, endWord, res, curList, map);
            curList.remove(curList.size() - 1);
        }
    }
}

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return new ArrayList<List<String>>();
        }
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        if (!constructPath(beginSet, endSet, wordSet, map, true)) {
            return res;
        }
        List<String> curList = new ArrayList<>();
        curList.add(beginWord);
        generateList(beginWord, endWord, res, curList, map);
        return res;
    }
    
    private boolean constructPath(Set<String> beginSet, Set<String> endSet, Set<String> wordSet, Map<String, List<String>> map, boolean isForward) {
        if (beginSet.isEmpty() || endSet.isEmpty()) {
            return false;
        }
        wordSet.removeAll(beginSet);
        boolean found = false;
        Set<String> nextBegin = new HashSet<>();
        for (String s : beginSet) {
            char[] sc = s.toCharArray();
            for (int i = 0; i < sc.length; i++) {
                char origin = sc[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == origin) {
                        continue;
                    }
                    sc[i] = c;
                    String newStr = new String(sc);
                    if (!wordSet.contains(newStr)) {
                        continue;
                    }
                    nextBegin.add(newStr);
                    String key = isForward ? s : newStr;
                    String value = isForward ? newStr : s;
                    if (!map.containsKey(key)) {
                        map.put(key, new ArrayList<>());
                    }
                    map.get(key).add(value);
                    if (endSet.contains(newStr)) {
                        found = true;
                    }
                }
                sc[i] = origin;
            }
        }
        if (found) {
            return true;
        }
        if (nextBegin.size() > endSet.size()) {
            return constructPath(endSet, nextBegin, wordSet, map, !isForward);
        }
        return constructPath(nextBegin, endSet, wordSet, map, isForward);
    }
    
    private void generateList(String beginWord, String endWord, List<List<String>> res, List<String> curList, Map<String, List<String>> map) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(curList));
            return;
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String s : map.get(beginWord)) {
            curList.add(s);
            generateList(s, endWord, res, curList, map);
            curList.remove(curList.size() - 1);
        }
    }
}
