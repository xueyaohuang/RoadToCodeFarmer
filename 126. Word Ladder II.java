class Solution {
    public List<List<String>> findLadders(String start, String end, List<String> wordList){
        List<List<String>> res = new ArrayList<>();

        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(end))
            return res;

        Map<String, List<String>> map = new HashMap<>(wordList.size());

        Set<String> startSet = new HashSet<>();
        startSet.add(start);

        Set<String> endSet = new HashSet<>();
        endSet.add(end);

        if(!helper(wordSet, startSet, endSet, map, true))
            return res;

        List<String> curList = new ArrayList<>();
        curList.add(start);

        generateStr(start, end, map, curList, res);

        return res;
    }

    private boolean helper(Set<String> wordSet, Set<String> startSet, Set<String> endSet, Map<String, List<String>> map, boolean isForward){
        if(startSet.isEmpty() || endSet.isEmpty())
            return false;
        boolean found = false;
        wordSet.removeAll(startSet);
        Set<String> nextStart = new HashSet<>();
        for(String cur : startSet){
            char[] curChs = cur.toCharArray();
            for(int i = 0; i < curChs.length; i++){
                char tmpC = curChs[i];
                for(char c = 'a'; c <= 'z'; c++){
                    if(curChs[i] == c)
                        continue;
                    curChs[i] = c;
                    String tmp = new String(curChs);
                    if(!wordSet.contains(tmp))
                        continue;
                    nextStart.add(tmp);
                    String key = isForward ? cur : tmp;
                    String value = isForward ? tmp : cur;
                    if(!map.containsKey(key))
                        map.put(key, new ArrayList<>());
                    map.get(key).add(value);
                    if(endSet.contains(tmp))
                        found = true;
                }
                curChs[i] = tmpC;
            }
        }
        if(found)
            return true;
        if(nextStart.size() > endSet.size())
            return helper(wordSet, endSet, nextStart, map, !isForward);
        return helper(wordSet, nextStart, endSet, map, isForward);
    }

    private void generateStr(String beginWord, String endWord, Map<String, List<String>> map, List<String> curList, List<List<String>> res){
        if(beginWord.equals(endWord)){
            res.add(new ArrayList<>(curList));
            return;
        }
        if(!map.containsKey(beginWord))
            return;
        for(String cur : map.get(beginWord)){
            curList.add(cur);
            generateStr(cur, endWord, map, curList, res);
            curList.remove(curList.size() - 1);
        }
    }
}
