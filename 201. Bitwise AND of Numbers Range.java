/*
The trick here is that :
Bitwise-AND of any two numbers will always produce a number less than or equal to the smaller number.

Consider the following example:

									12 ---- 1100
									11 ---- 1011
									10 ---- 1010
									9  ---- 1001
									8  ---- 1000
									7  ---- 0111
									6  ---- 0110
									5  ---- 0101

Desired Range: [5,12]

Starting from 12, the loop will first do
12 & 11 = 8

Next iteration, the loop will do
8 & 7 = 0

why did we skip anding of 10,9? Because even if we did so, the result would eventually be anded with 8 whose value would be lesser than equal to 8.

Hence, you start from the range end and keep working your way down the range till you reach the start.
*/
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            n &= (n - 1);
        }
        return m & n;
    }
}

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n) {
            return m;
        }
        int res = 0;
        int bits = getBits(m);
        for (int i = 1; i <= bits - 1; i++) {
            int cur = (int)Math.pow(2, i);
            int j = m;
            while (j <= n) {
                if ((j & cur) == 0) {
                    break;
                }
                j++;
                
            }
            if (j == n + 1) {
                res += cur;
            }
        }
        return res;
    }
    
    private int getBits(int n) {
        int res = 0;
        while (n != 0) {
            n /= 2;
            res++;
        }
        return res;
    }
}
