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

class Solution {
    public String nearestPalindromic(String n) {
        int len = n.length() / 2;
        long num = Long.parseLong(n);
        long dividend = (long)Math.pow(10, len);
        List<Long> list = new ArrayList<>();
        
        long num1 = makePalindrome(num / dividend * dividend);
        long num2 = makePalindrome((num / dividend + 1) * dividend);
        long num3 = makePalindrome((num / dividend - 1) * dividend);
        
        if (num1 != num) {
            list.add(num1);
        }
        if (num2 != num) {
            list.add(num2);
        }
        if (num3 != num) {
            list.add(num3);
        }
        list.add((long)Math.pow(10, n.length() - 1) - 1); // 999, when n is 1000
        list.add((long)Math.pow(10, n.length()) + 1); // 1001, when n is 999
        
        Collections.sort(list, (a, b) -> Long.compare(Math.abs(a - num), Math.abs(b - num)) == 0 ? Long.compare(a, b) : Long.compare(Math.abs(a - num), Math.abs(b - num)));
        
        return String.valueOf(list.get(0));
    }
    
    private long makePalindrome(long n) {
        String s = String.valueOf(n);
        char[] sc = s.toCharArray();
        int i = 0;
        int j = sc.length - 1;
        while (i < j) {
            sc[j] = sc[i];
            i++;
            j--;
        }
        return Long.parseLong(new String(sc));
    }
}
