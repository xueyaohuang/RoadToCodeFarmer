// 三种方法，实际中要根据s, k, w的大小关系决定用哪种方法

// 1. O(sk)
// s is length of S, k is length of words
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Set<String> isSubSeq = new HashSet<>();
        Set<String> isNotSubSeq = new HashSet<>();
        int res = 0;
        for (String w : words) {
            if (isSubSeq.contains(w)) {
                res++;
                continue;
            }
            if (isNotSubSeq.contains(w)) {
                continue;
            }
            if (isSubsquence(w, S)) {
                res++;
                isSubSeq.add(w);
            } else {
                isNotSubSeq.add(w);
            }
        }
        return res;
    }
    
    // O(t)
    private boolean isSubsquence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        int idx = -1;
        for (char c : s.toCharArray()) {
            idx = t.indexOf(c, idx + 1);
            if (idx == -1) {
                return false;
            }
        }
        return true;
    }
}

// 2. binary search
// see https://github.com/xueyaohuang/RoadToCodeFarmer/blob/master/392.%20Is%20Subsequence.java
// O(kwlgs) s is length of S, k is length of words, w is the average length of word.
// vs the above method O(sk)


// 3. O(s + kw)。在最外层for loop中，很多时候size是等于0的，所以不是O(skw)。
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, Queue<String>> map = new HashMap<>();
        int count = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            map.put(c, new LinkedList<>());
        }
        for (String word : words) {
            map.get(word.charAt(0)).offer(word);
        }
        for (char cs : S.toCharArray()) {
            Queue<String> queue = map.get(cs);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.length() == 1) {
                    count++;
                } else {
                    map.get(word.charAt(1)).offer(word.substring(1));
                }   
            }
        }
        return count;
    }
}
