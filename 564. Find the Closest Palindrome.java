// TLE
class Solution {
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        long r = (long)num + 1;
        long l = (long)num - 1;
        
        while (r + 1 < Long.MAX_VALUE) {
            if (isPalindrome(r)) {
                break;
            }
            r++;
        }
        
        while (l > 0) {
            if (isPalindrome(l)) {
                break;
            }
            l--;
        }
        
        if (r + l >= 2 * num) {
            return String.valueOf(l);
        }
        return String.valueOf(r);
        
    }
    
    private boolean isPalindrome(long n) {
        if (n == 0) {
            return true;
        }
        if (n < 0 || (n % 10 == 0)) {
            return false;
        }
        long reverse = 0;
        long original = n;
        while (reverse < original) {
            reverse *= 10;
            reverse += original % 10;
            original /= 10;
        }
        return reverse == original || reverse / 10 == original;
    }
}
