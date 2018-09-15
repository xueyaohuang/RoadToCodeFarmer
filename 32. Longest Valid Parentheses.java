// 把valid括号序列赋值1，其他为0，然后数最长连续1序列的长度。
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        int[] data = new int[len];
        int cur = 0, max = 0;
        
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    data[stack.pop()] = 1;
                    data[i] = 1;
                }
            }
        }
        
        for (int i : data) {
            if (i == 1) {
                cur++;
            } else {
                max = Math.max(max, cur);
                cur = 0;
            }
        }
        
        return Math.max(max, cur);
    }
}

//DP solution 4ms
The idea is go through the string and use DP to store the longest valid parentheses at that point.
take example "()(())"
i : [0,1,2,3,4,5]
s : [( ,) ,( ,( ,) ,) ]
DP:[0,2,0,0,2,6]

1, We count all the ‘(‘.
2, If we find a ‘)’ and ‘(‘ counter is not 0, we have at least a valid result size of 2. “()"
3, Check the the one before (i - 1). If DP[i - 1] is not 0 means we have something like this “(())” . This will have DP “0024"
4, We might have something before "(())”. Take "()(())” example, Check the i = 1 because this might be a consecutive valid string.

public class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int result = 0;
        int leftCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (leftCount > 0){
                dp[i] = dp[i - 1] + 2;
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                result = Math.max(result, dp[i]);
                leftCount--;
            }
        }
        return result;
    }
}
