// brute force.
// 1. 找到从index 0 开始的s的一个最长的palindrome的substring
// 2. 把s中剩下部分，reverse后，拼到s最开头
class Solution {
    public String shortestPalindrome(String s) {
        int len = s.length();
        String rvs = reverse(s);
        for (int i = s.length(); i > 0; i--) {
            if (s.substring(0, i).equals(rvs.substring(len - i))) 
                return new StringBuilder(s.substring(i)).reverse().append(s).toString();
        }
        return s;
    }
    
    public String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = len - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

// recursion


// kmp

