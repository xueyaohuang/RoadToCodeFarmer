class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        return 1162261467 % n == 0;
    }
}

class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}

class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 1) {
            return true;
        }
        long cur = 1;
        while (cur < n) {
            cur *= 3;
            if (cur == n) {
                return true;
            }
        }
        return false;
    }
}
