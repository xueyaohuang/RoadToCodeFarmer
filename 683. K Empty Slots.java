/*
The idea is to use an array days[] to record each position's flower's blooming day.
That means days[i] is the blooming day of the flower in position i+1.
We just need to find a subarray days[left, left+1,..., left+k-1, right] which satisfies:
for any i = left+1,..., left+k-1, we can have days[left] < days[i] && days[right] < days[i].
Then, the result is max(days[left], days[right]).
*/

class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        if (flowers == null || flowers.length < k + 2) {
            return -1;
        }
        
        int len = flowers.length;
        int[] date = new int[len];
        for (int i = 0; i < len; i++) {
            date[flowers[i] - 1] = i + 1;
        }
        
        int res = Integer.MAX_VALUE;
        int left = 0, right = k + 1;
        
        // O(n), 因为i最多把每个数都走一遍，外层while中的right每次跳的很多
        outer: while (right < len) {
            for (int i = left + 1; i < right; i++) {
                if (date[i] < date[left] || date[i] < date[right]) {
                    left = i;
                    right = left + k + 1;
                    continue outer;
                }
            }
            res = Math.min(res, Math.max(date[left], date[right]));
            left = right;
            right = left + k + 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
