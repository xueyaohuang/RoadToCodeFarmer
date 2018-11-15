// 左边的最大值需要小于右边任意一个数
// 遍历A时，几下左边最大值，一旦遇到一个数比这个最大值小，就要更新左半边最右元素的index
class Solution {
    public int partitionDisjoint(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        // curIdx是目前左半部分的末尾index
        int curIdx = 0;
        // curMax是截止到index i时，前面所有元素最大值
        // leftMax是截止curIdx 时，前面所有元素最大值
        // i大于等于curIdx
        int curMax = A[0], leftMax = A[0];
        int len = A.length;
        
        for (int i = 1; i < len; i++) {
            if (A[i] < leftMax) {
                curIdx = i;
                leftMax = curMax;
            } else {
                curMax = Math.max(curMax, A[i]);
            }
        }
        return curIdx + 1;
    }
}
