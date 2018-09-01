// 实际上这样对idx1和idx2的更新考虑到了所有可能的组合，但是因为word1可能和word2一样，所以在一个idx处，两个idx可能变成一样。所以在一个idx处，要更新distance2次。
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int idx1 = -1;
        int idx2 = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                idx1 = i;
            }
            if (idx1 != idx2 && idx1 != -1 && idx2 != -1) {
                distance = Math.min(distance, Math.abs(idx1 - idx2));
            }
            if (word2.equals(words[i])) {
                idx2 = i;
            }
            if (idx1 != idx2 && idx1 != -1 && idx2 != -1) {
                distance = Math.min(distance, Math.abs(idx1 - idx2));
            }
        }
        return distance;
    }
}
