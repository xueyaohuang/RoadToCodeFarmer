class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int reverse = 0;
        int original = x;
        while (original != 0) {
            reverse *= 10;
            reverse += original % 10;
            original /= 10;
        }
        return reverse == x;
    }
}

class Solution {
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || (x % 10 == 0)) {
            return false;
        }
        int reverse = 0;
        int original = x;
        while (reverse < original) {
            reverse *= 10;
            reverse += original % 10;
            original /= 10;
        }
        return reverse == original || reverse / 10 == original;
    }
}
