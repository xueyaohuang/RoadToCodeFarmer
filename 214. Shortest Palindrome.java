// https://leetcode.com/problems/shortest-palindrome/solution/

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
// Time complexity: O(n^2)
// Each iteration of shortestPalindrome is linear in size of substring and the maximum number of recursive calls can be n/2 times as shown in the Intuition section.
// Let the time complexity of the algorithm be T(n). Since, at the each step for the worst case, the string can be divide into 2 parts and we require only one part for further computation. 
// Hence, the time complexity for the worst case can be represented as : T(n)=T(n-2)+O(n). So, T(n) = O(n) + O(n-2) + O(n-4) + ... + O(1), which is O(n^2)
// https://leetcode.com/problems/shortest-palindrome/solution/
// sol 2
class Solution {
    public String shortestPalindrome(String s) {
        int i = 0;
        int len = s.length();
        // 从头开始，找到包含从头开始最长palindrome的substring
        // for loop结束后，s.substring(0, i)不一定是palindrome，但是一定包含从头开始的最长palindrome
        // for loop结束后，s.substring(i)肯定不是palindrome的一部分，需要把他reverse后接到s的最开始
        // 之后递归调用
        for (int j = len - 1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
        }
        if (i == len) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.substring(i));
        return sb.reverse().toString() + shortestPalindrome(s.substring(0, i)) + s.substring(i);
    }
}


// kmp
// 思路还是  find the longest palindrome substring starts from 0
// build a string: s + "#" + reverse(s), and run kmp on it
// the last element of kmp PrefixArray will be our solution
// We add "#" here to force the match in reverse(s) starts from its first index, 否则s和reverse s连起来可能构成palindrome，最后一个元素的值可能大于s的长度
// ex. catacb # bcatac
// kmp table:
// c a t a c b # b c a t a c
// 0 0 0 0 1 0 0 0 1 2 3 4 5
// In the last cell, we got a value 5. It means in s we have a substring of length 5 that is palindrome.
class Solution {
    public String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        String str = s + "|" + sb.reverse().toString();
        int[] table = kmpTable(str);
        int len = table.length;
        StringBuilder addToFront = new StringBuilder(s.substring(table[len - 1]));
        return addToFront.reverse().append(s).toString();
        
    }
    
    private int[] kmpTable(String s) {
        int len = s.length();
        int[] table = new int[len];
        for (int i = 1, j = 0; i < len; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = table[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            table[i] = j;
        }
        return table;
    }
}
