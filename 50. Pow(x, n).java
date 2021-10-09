class Solution {
    public double myPow(double x, int n) {
        if (n == 0 || x == 1) {
            return 1;
        }
        // n%2==0在n < 0之前检查可以避免n是-2147483648的时候-n溢出
        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        }
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        return x * myPow(x * x, n / 2);
    }
}

class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            x = 1 / x;
            return n % 2 == 0 ? myPow(x * x, -(n / 2)) : x * myPow(x * x, -(n / 2));
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}

// divide and conquer
// recursive
