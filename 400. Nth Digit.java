class Solution {
    public int findNthDigit(int n) {
        int k = 1;
        while (n - 9 * (long)Math.pow(10, k - 1) * k > 0) {
            n -= 9 * (long)Math.pow(10, k - 1) * k;
            k++;
        }
        long i = (long)Math.pow(10, k - 1) + n / k - 1;
        n %= k;
        if (n == 0) {
            return getNthDigitOfNum(i, k - 1);
        }
        i++;
        return getNthDigitOfNum(i, n - 1);
    }
    
    private int getNthDigitOfNum(long num, int i) {
        String numStr = String.valueOf(num);
        char c = numStr.charAt(i);
        return c - '0';
    }
}

// 1 9     1
// 2 90    2
// 3 900   3
// 4 9000  4
    
//     85693 - 9 - 180 - 2700 - 36000 = 46804
//      9999
    
//     10^k
    
//   k  0 1 2 3 4
//   n  1

class Solution {
    public int findNthDigit(int n) {
        int start = 1;
        int len = 1;
        long num = 9;
        while (n > len * num) {
            n -= len * num;
            len += 1;
            num *= 10;
            start *= 10;
        }
        start += (n - 1) / len;
        String s = Integer.toString(start);
        return s.charAt((n - 1) % len) - '0';
    }
}
