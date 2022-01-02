/*
Explanation

The order in initial arrays doesn't matter,
my first intuition is to sort the array.

For each number A[i]:

    There are i smaller numbers,
    so there are 2 ^ i sequences in which A[i] is maximum.
    we should do res += A[i] * 2^i

    There are n - i - 1 bigger numbers,
    so there are 2 ^ (n - i - 1) sequences in which A[i] is minimum.
    we should do res -= A[i] * 2^(n - i - 1)

Done.

Time Complexity:

Time O(NlogN)
Space O(1)

FAQ

Q. why do we plus mod before return?
A In Cpp and Java, mod on negative number will still get a negative number.
*/

class Solution {
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long res = 0, mod = (long)Math.pow(10,  9) + 7;
        // 分成2个for loop是为了避免overflow
        long max = 1;
        for (int i = 0; i < n; i++) {
            res += nums[i] * max % mod;
            res %= mod;
            max = max * 2 % mod;
        }
        long min = 1;
        for (int i = n - 1; i >= 0; i--) {
            res -= nums[i] * min % mod;
            res %= mod;
            min = min * 2 % mod;
        }
        return (int)((res + mod) % mod);
    }
}
