/*
The thought process goes like this- Given a number N, we can possibly write it as a sum of 2 numbers, 3 numbers, 4 numbers and so on.
Let's assume the fist number in this series be x. Hence, we should have
x + (x+1) + (x+2)+...+ k terms = N
kx + k*(k-1)/2 = N implies
kx = N - k*(k-1)/2
So, we can calculate the RHS for every value of k and if it is a multiple of k then we can construct a sum of N using k terms starting from x.
Now, the question arises, till what value of k should we loop for? That's easy. In the worst case, RHS should be greater than 0. That is
N - k*(k-1)/2 > 0 which implies
k*(k-1) < 2N which can be approximated to
k*k < 2N ==> k < sqrt(2N)
Hence the overall complexity of the algorithm is O(sqrt(N))
*/
class Solution {
    public int consecutiveNumbersSum(int n) {
        int count = 1;
        int k = 2;
        while (k <= Math.sqrt(2 * n)) {
            if ((n - k * (k - 1) / 2) % k == 0) {
                count++;
            }
            k++;
        }
        return count;
    }
}
