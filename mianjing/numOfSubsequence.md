[Find number of times a string occurs as a subsequence in given string](https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/)

```
public class MyClass {
    
    // 没有优化空间复杂度
    // public static int numSubsequence(String s, String t) {
    //     if (s == null || t == null) {
    //         return 0;
    //     }
    //     int lens = s.length();
    //     int lent = t.length();
        
    //     int[][] dp = new int[lens + 1][lent + 1];
    //     for (int i = 0; i <= lens; i++) {
    //         dp[i][0] = 1;
    //     }
    //     for (int j = 1; j <= lent; j++) {
    //         dp[0][j] = 0;
    //     }
        
    //     for (int i = 1; i <= lens; i++) {
    //         for (int j = 1; j <= lent; j++) {
    //             if (s.charAt(i - 1) != t.charAt(j - 1)) {
    //                 dp[i][j] = dp[i - 1][j];
    //             } else {
    //                 dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
    //             }
    //         }
    //     }
        
    //     return dp[lens][lent];
    // }
    
    // 优化空间复杂度 
    public static int numSubsequence(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int lens = s.length();
        int lent = t.length();
        
        int[] dp = new int[lent + 1];
        dp[0] = 1;
        for (int j = 1; j <= lent; j++) {
            dp[j] = 0;
        }
        
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lent; j++) {
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    continue;
                } else {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[lent];
    }
    
    public static void main(String args[]) {
        
        String s = "GeeksforGeeks";
        String t = "Gks";
        
        int res = numSubsequence(s, t);

        System.out.println(res);
    }
}
```
