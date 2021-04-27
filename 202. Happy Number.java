class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (set.add(n)) {
            n = getSquareSum(n);
        }
        return n == 1;
    }
    
    private int getSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int cur = n % 10;
            sum += cur * cur;
            n /= 10;
        }
        return sum;
    }
}

class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (set.add(n)) {
            if (sumSquNum(n) == 1) {
                return true;
            }
            n = sumSquNum(n);           
        }
        return false;
    }
    private int sumSquNum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}

class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1) {
            n = getSquare(n);
            if (!set.add(n)) {
                return false;
            }
        }
        return true;
    }
    private int getSquare(int n) {
        int res = 0;
        while (n != 0) {
            res += Math.pow(n % 10, 2);
            n /= 10;
        }
        return res;
    }
}
