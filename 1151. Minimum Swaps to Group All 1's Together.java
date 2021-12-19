/*
the final result we want is a window with length of n (total number of the 1s)
Check all the window with the same length n, find the maximum one which already contains the most 1s.
All we need to do is to swap the rest: n-max.

we know the window size is fixed, which is the total number of 1s
*/
class Solution {
    public int minSwaps(int[] data) {
        int totalOnes = 0;
        for (int i : data) {
            totalOnes += i;
        }
        int max = 0;
        int cur = 0;
        for (int i = 0; i < totalOnes; i++) {
            cur += data[i];
            max += data[i];
        }
        for (int i = totalOnes; i < data.length; i++) {
            cur += data[i] - data[i - totalOnes];
            max = Math.max(max, cur);
        }
        return totalOnes - max;
    }
 }
