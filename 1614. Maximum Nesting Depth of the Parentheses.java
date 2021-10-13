// 数open，close括号之差的最大值
class Solution {
    public int maxDepth(String s) {
        int max = 0;
        int diff = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                diff++;
                max = Math.max(max, diff);
            } else if (c == ')') {
                diff--;
            }
        }
        return max;
    }
}
