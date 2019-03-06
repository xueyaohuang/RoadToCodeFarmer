// O(nlgn)
class Solution {
    public int rotatedDigits(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isGoodNumber(i)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isGoodNumber(int n) {
        int rotateToSelf = 0;
        int bits = 0;
        while (n != 0) {
            int x = n % 10;
            if (x == 0 || x == 1 || x == 8) {
                rotateToSelf++;
            }
            if (x == 3 || x == 4 || x == 7) {
                return false;
            }
            n /= 10;
            bits++;
        }
        return rotateToSelf != bits;
    }
}

// O(n)
/*
Using a int[] for dp.
dp[i] = 0, invalid number
dp[i] = 1, valid and same number
dp[i] = 2, valid and different number
*/
class Solution {
    public int rotatedDigits(int N) {
        int count = 0;
        int[] dp = new int[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            if (i == 1 || i == 8) {
                dp[i] = 1;
            } else if (i == 2 || i == 5 || i == 6 || i == 9) {
                dp[i] = 2;
                count++;
            } else {
                int a = dp[i / 10], b = dp[i % 10];
                if (a == 1 && b == 1) {
                    dp[i] = 1;
                } else if (a >= 1 && b >= 1) {
                    dp[i] = 2;
                    count++;
                }
            }
        }
        return count;
    }
}

// O(lgn)
http://www.frankmadrid.com/ALudicFallacy/2018/02/28/rotated-digits-leet-code-788/
