// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space
// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/478708/RZ-Summary-of-all-the-solutions-I-have-learned-from-Discuss-in-Python
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i : arr) {
            while (!stack.isEmpty() && i >= stack.peek()) {
                int mid = stack.pop();
                if (stack.isEmpty()) {
                    res += mid * i;
                } else {
                    res += mid * Math.min(i, stack.peek());
                }
            }
            stack.push(i);
        }
        while (stack.size() > 1) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}
