class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] notPrime = new boolean[n];
        int count = 1;
        for (int i = 3; i < n; i += 2) {
            if (!notPrime[i]) {
                count++;
                // 这个for j的循环放到if里面比外面快，因为放到外面会有重复的i * j被再次设置成true
                for (int j = 3; i * j < n; j += 2) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }
    
    // private boolean isPrime(int n) {
    //     if (n <= 1) {
    //         return false;
    //     }
    //     if (n % 2 == 0) {
    //         return false;
    //     }
    //     for (int i = 3; i * i <= n; i += 2) {
    //         if (n % i == 0) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }
}
