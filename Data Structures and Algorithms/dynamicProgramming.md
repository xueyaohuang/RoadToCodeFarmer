* 优化问题（max, min），计数问题(多少种xxx。。。)。
* 定义子问题，大问题化小问题。大问题由哪些维度构成？怎么把问题变小
* 寻找recurrence
* 优化避免重复计算  
  bottom up  nested for loop
  top down (with memoization)  recursion + memoization
  
## 分类
1. 矩阵坐标型
2. 序列型（单序列，双序列）
   如LIS, LCS
3. knapsack变种。牢记0-1背包和非0-1背包。   
   * 0-1背包问题本质：给一堆东西，从中选出若干组合在一起，达到某各指标最大化，同时满足一定的限定条件。  
   问题大小的维度：a.物品数，b.限制条件大小。  
   写递归时通常要分第i个用或者不用（0-1背包）。  
   474 Ones and Zeroes  
   * 非0-1背包，每个物品可以用无限次。  
   问题大小的维度：a.物品可以无限用，所以物品数的限制消除，b.限制条件大小。问题大小维度变成1维。  
   coin change I II
   * sunset sum
4. 区间型，如矩阵乘法，戳气球
 
## loop顺序问题，for loop逆序，每个元素用1次（0-1背包），正序每个元素用多次（完全背包）。  
for loop, 有时候从小到大，有时候从大到小，原因只有1个。

dp 都因该是从小问题到大问题，这一点不会变，为什么有时候for loop从大到小，给人一种从大问题到小问题的错觉？

因为并不是真正的从大到小，只是在优化空间复杂度的时候，去掉了一个维度，在更新第i轮的dp值的时候，必须用第i-1轮的值，所以看到有从大往小loop。
具体是从小到大还是从大到小，要看从小到大能否根据第i-1轮的dp值更新第i轮的dp值。

举个栗子：

0-1背包，假设n个物品，背包能装W的重量，那么for loop如下
```
for (int i = 1; i <= n; i++) {
    for (int j = 0; j <= W; j++) {
        dp[i][j] = dp[i - 1][j]; // not rob i
        if (j > w[i]) {
            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]); // rob i
        }
    }
}
```
可以看到dp[i][j]的更新只依赖于dp[i - 1][j], dp[i - 1][j - w[i]], 只依赖于i-1轮的状态，和i-2或更早之前的没关系。  
所以可以优化空间复杂度，用滚动数组。  
事实上，这要求在每次主循环中我们以j从大到小，j = W..0的顺序推dp，这样才能保证推dp[j]时dp[j-v[i]]保存的是状态dp[i-1][j-v[i]]的值。 
如果j的循环顺序是顺序的话，那么则成了f[i][j]由f[i][j-v[i]]推知，显然错误。优化代码如下：
（换句话说，这正是为了保证每件物品只选一次，保证在考虑“选入第i件物品”这件策略时，依据的是一个绝无已经选入第i件物品的子结果f[i-1][j-w[i]]。)
```
// 0-1背包
for (int i = 1; i <= n; i++) {
    for (int j = W; j >= w[i]; j--) {  // j >= w[i]就不用check if (j > w[i]).
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
    }
}
```
反之，完全背包的特点恰好是每种物品可选无限件，所以在考虑“加选一件第i种物品”这种策略时，却正需要一个可能已选入第i种物品的子结果f[i][j-w[i]],
把0-1背包内层的for loop顺序就是完全背包。
```
// 完全背包，这两个for loop可以换位置
for (int i = 1; i <= n; i++) {
    for (int j = w[i]; j <= W; j++) {
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
    }
}
```

比如474 Ones and Zeroes这个题，与0-1背包非常类似，只是重量维度由一维变成2维，所以在优化空间复杂度时，把第i个string这个维度取消掉，
后面的m和n都要从大到小loop，这样才能保证用的是第i-1轮的数据更新。  

有时优化空间复杂度后，不需要改变loop的顺序，比如lc 62 Unique Paths  
优化前是：
```
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

```
class Solution {
    public int uniquePaths(int m, int n) {
        int[] ways = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    ways[j] = 1;
                } else {
                    ways[j] += ways[j - 1];
                }
            }
        }
        return ways[n - 1];
    }
}
```
对比这两个recurrence，需要逆序loop的，更新依赖于i-1的dp，而不需要逆序loop的，更新依赖于
```
Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i])

dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
```

比如 coin change 2,  
The difference is that if you update dp while:
increasing i then the previous partial result dp[i - coin] is the result that has considered coin already
decreasing i then the previous partial result dp[i - coin] is the result that has not considered coin yet
```
/** 
 * @return number of ways to make sum s using repeated coins
 */
public static int coinrep(int[] coins, int s) {
    int[] dp = new int[s + 1]; 
    dp[0] = 1;          
    for (int coin : coins)      
        for (int i = coin; i <= s; i++)         
            dp[i] += dp[i - coin];                                  
    return dp[s];
}                                       
                                            
/**
 * @return number of ways to make sum s using non-repeated coins
 */
public static int coinnonrep(int[] coins, int s) {
    int[] dp = new int[s + 1];
    dp[0] = 1;  
    for (int coin : coins)
        for (int i = s; i >= coin; i--)
            dp[i] += dp[i - coin];              
    return dp[s];                                                   
} 
```
