class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        if (n == 2) {
            return 987;
        }
        long max = (long) Math.pow(10, n) - 1;
        long min = (long) Math.pow(10, n - 1);
        for (long i = max; i >= min; i--) {
            long palProd = makePalProd(i);
            if (isProduct(palProd, min, max)) {
                return (int) (palProd % 1337);
            }
        }
        return -1;
    }
    
    private long makePalProd(long num) {
        long res = num;
        while (num != 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }
    
    private boolean isProduct(long target, long min, long max) {
        long start = (long) Math.sqrt(target);
        long left = start;
        long right = start;
        while (left * right != target) {
            if (left * right > target) {
                left--;
                if (left < min) {
                    return false;
                }
            }
            if (left * right < target) {
                right++;
                if (right > max) {
                    return false;
                }
            }
        }
        return true;
    }
}
