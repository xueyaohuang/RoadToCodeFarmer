// no endSet
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordDict = new HashSet<>(wordList);
        if (!wordDict.contains(endWord)) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        Set<String> used = new HashSet<>();
        int count = 1;
        while (!set.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for (String s : set) {
                if (s.equals(endWord)) {
                    return count;
                }
                char[] cur = s.toCharArray();
                for (int i = 0; i < cur.length; i++) {
                    char ch = cur[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        cur[i] = c;
                        String target = String.valueOf(cur);
                        if (!used.contains(target) && wordDict.contains(target)) {
                            temp.add(target);
                            used.add(target);
                        }
                    }
                    cur[i] = ch;
                }
            }
            count++;
            set = temp;
        }
        return 0;
    }
}

/*
1. Why shortest transformation?
Using BFS, each time increase len by 1, if there is any transformation, it will return.
2. Why swap beginSet and endSet when beginSet.size() > endSet.size()?
It doesn't matter how we transform, from beginset to endset or the other way around.
But in the while loop, I will loop through the beginSet, so it would be faster if I loop through a set with smaller size.
3. What is set 'used' used for?
Keep track of the mid-state word. If I don't have set 'used', the algorithm may fall into a dead loop.
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> used = new HashSet<>();
        Set<String> wordSet = new HashSet<>(wordList);
        beginSet.add(beginWord);
        endSet.add(endWord);
        int step = 1;
        
        while (!beginSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            Set<String> temp = new HashSet<>();
            for (String str : beginSet) {
                char[] chs = str.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    char original = chs[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chs[i] = c;
                        String target = String.valueOf(chs);
                        if (endSet.contains(target)) {
                            return step + 1;
                        }
                        if (!used.contains(target) && wordSet.contains(target)) {
                            temp.add(target);
                            used.add(target);
                        }
                    }
                    chs[i] = original;
                }
            }
            beginSet = temp;
            step++;
        }
        return 0;
    }
}
