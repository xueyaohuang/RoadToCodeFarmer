/*
Use buckets to record the last position of digit 0 ~ 9 in this num.

Loop through the num array from left to right.
For each position, we check whether there exists a larger digit in this num (start from 9 to current digit).
We also need to make sure the position of this larger digit is behind the current one.
If we find it, simply swap these two digits and return the result.

*/
class Solution {
    public int maximumSwap(int num) {
        char[] n = String.valueOf(num).toCharArray();
        int[] lastIdx = new int[10];
        for (int i = 0; i < n.length; i++) {
            lastIdx[n[i] - '0'] = i;
        }
        for (int i = 0; i < n.length; i++) {
            for (int j = 9; j > n[i] - '0'; j--) {
                if (lastIdx[j] > i) { // 不能只check j是否存在（lastIdx[j] ！= 0）， 否则input是9974会return9794
                    char c = n[i];
                    n[i] = n[lastIdx[j]];
                    n[lastIdx[j]] = c;
                    return Integer.parseInt(String.valueOf(n));
                }
            }
        }
        return num;
    }
}
