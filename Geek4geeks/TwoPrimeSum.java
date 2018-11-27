// 给positive number N, find if N can be written as a prime + a prime. 两个质数可以相等
import java.util.*;

class TwoPrimeSum {
    // public boolean canSumTwoPrime(int n) {
    //     // Goldbach's conjecture: https://en.wikipedia.org/wiki/Goldbach%27s_conjecture
    //     if (n % 2 == 0) {
    //         return true;
    //     }
    //     return isPrime(n - 2);
    // }

    // private boolean isPrime(int n) {
    //     if (n <= 1) {
    //         return false;
    //     }
    //     if (n == 2) {
    //         return true;
    //     }
    //     if (n % 2 == 0) {
    //         return false;
    //     }
    //     for (int i = 3; i <= (int)Math.sqrt(n); i += 2) {
    //         if (n % i == 0) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    public boolean canSumTwoPrime(int n) {
        boolean[] prime = isPrimeArray(n);
        for (int i = 1; i <= n / 2; i++) {
            if (prime[i] && prime[n - i]) {
                return true;
            }
        }
        return false;
    }

    private boolean[] isPrimeArray(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            // 如果发现一个prime，把它的倍数都变成非prime
            if (prime[i]) {
                // j从i*i开始，因为之前的都被标记过了
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }
        return prime;
    }

    public static void main(String[] args) {
        TwoPrimeSum tps = new TwoPrimeSum();
        boolean res = tps.canSumTwoPrime(201);
        System.out.println(res);
    }
}
