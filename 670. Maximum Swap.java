/*
Use buckets to record the last position of digit 0 ~ 9 in this num.
为什么要找last position，因为把较小的数越往后换新的数越大，比如
39059，可以换成93059，也可以换成99053，显然3和最后那个9换了之后得到的数最大。

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
                    n[i] = n[lastIdx[j]]; // or n[i] = (char)(j + '0');
                    n[lastIdx[j]] = c;
                    return Integer.parseInt(String.valueOf(n));
                }
            }
        }
        return num;
    }
}
