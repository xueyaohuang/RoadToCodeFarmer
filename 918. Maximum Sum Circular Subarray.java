/*
Two cases:
1. The first is that the subarray take only a middle part, and we know how to find the max subarray sum.
2. The second is that the subarray take a part of head array and a part of tail array. 
   We can transfer this case to the first one.
   The maximum result equals to the total sum minus the minimum subarray sum.
   
Corner case

Just one to pay attention:
If all numbers are negative, maxSum = max(A) and minSum = sum(A).
In this case, max(maxSum, total - minSum) = 0, which means the sum of an empty subarray.
According to the deacription, We need to return the max(A), instead of sum of am empty subarray.
So we return the maxSum to handle this corner case.

Complexity

One pass, time O(N)
No extra space, space O(1)
*/

class Solution {
    public int maxSubarraySumCircular(int[] A) {
        int curMax = A[0];
        int curMin = A[0];
        int max = A[0];
        int min = A[0];
        int sum = A[0];
        for (int i = 1; i < A.length; i++) {
            curMax = curMax > 0 ? curMax + A[i] : A[i];
            max = Math.max(max, curMax);
            curMin = curMin < 0 ? curMin + A[i] : A[i];
            min = Math.min(min, curMin);
            sum += A[i];
        }
        return min == sum ? max : Math.max(max, sum - min);
    }
}
