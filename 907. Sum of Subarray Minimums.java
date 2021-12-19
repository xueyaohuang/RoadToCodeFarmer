// naive O(n^2)
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int sum = 0;
        int mod = (int)Math.pow(10, 9) + 7;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                sum += min;
                sum %= mod;
            }
        }
        return sum;
    }
}

/*
O(n) using monotonous increase stack

Intuition:

res = sum(A[i] * f(i))
where f(i) is the number of subarrays in which A[i] is the minimum.

To get f(i), we need to find out:
prev[i], prev[i] = j means arr[j] is the previous less or equal element of arr[i].
next[i], next[i] = j means arr[j] is the next less element of arr[i].

Then,
i - prev[i] equals to the number of subarray ending with A[i], and A[i] is single minimum.
next[i] - i equals to the number of subarray starting with A[i], and A[i] is the first minimum.

Finally f(i) = (i - prev[i]) * (next[i] - i) (排列组合一下)

For [3,1,2,4] as example:
i - prev[i] = [1,2,1,1]
next[i] - i = [1,3,2,1]
f = [1,6,2,1]
res = 3 * 1 + 1 * 6 + 2 * 2 + 4 * 1 = 17

The last thing that needs to be mentioned for handling duplicate elements:
Method: Set strict less and non-strict less(less than or equal to) for finding NLE(next less element) and PLE (previous less element) respectively.
The order doesn't matter.

For example, the below code for finding NLE is strict less, while PLE is actually non-strict less.
*/
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int len = arr.length;
        int mod = (int)Math.pow(10, 9) + 7;
        long res = 0;
        // prev[i] = j means arr[j] is the previous less or equal element of arr[i].
        int[] prev = new int[len];
        // next[i] = j means arr[j] is the next less element of arr[i].
        int[] next = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.fill(prev, -1);
        Arrays.fill(next, len);
        // 从后往前loop，把求next less element 变成求previous less element
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                prev[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                next[stack.pop()] = i;
            }
            stack.push(i);
        }
        for (int i = 0; i < len; i++) {
            res += ((long)arr[i] * (i - prev[i]) * (next[i] - i) % mod) % mod;
            res %= mod;
        }
        return (int)res;
    }
}
