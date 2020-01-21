// The main idea is based on greedy. Let's say the range of the current jump is
// [curBegin, curEnd], curFarthest is the farthest point that all points in 
// [curBegin, curEnd] can reach. Once the current point reaches curEnd, then 
// trigger another jump, and set the new curEnd with curFarthest, then keep the
// above steps, as the following:
// This is an implicit bfs solution. i == curEnd means you visited all the items 
// on the current level. Incrementing jumps++ is like incrementing the level you
// are on. And curEnd = curFarthest is like getting the queue size (level size) 
/  for the next level you are traversing.
class Solution {
    public int jump(int[] nums) {
        int jump = 0;
        int end = 0;
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == end) {
                jump++;
                end = max;
                if (max >= nums.length - 1) {
                    return jump;
                }
            }
        }
        return jump;
    }
}
