/*
I. Given a beautiful array A, the following properties holds:
(1) A' = A*c is a Beautiful array
(2) A' = A + c is a Beautiful array
(3) if A' is an array obtained by deleting some element in A, then A' is still Beautiful.

II. Given two Beautiful array O and E, whose elements are odd and even respectively, then concatenation array A = O + E is still Beautiful.
prove: if the A[i] and A[j] are both from A or both from B, A[k] * 2 != A[i] + A[j] holds
if A[i] is from O and A[j] is from E, then A[k] * 2 is even, A[i] + A[j] is odd, so A[k] * 2 != A[i] + A[j] holds

idea: build the odd and even beautiful array and concat them
how to build the odd and even beautiful array? divide and conquer, use rules in I 
*/
class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        while (res.size() < n) {
            List<Integer> temp = new ArrayList<>();
            // construct odd array
            for (int i : res) {
                if (i * 2 - 1 <= n) {
                    temp.add(i * 2 - 1);
                }
            }
            // construct even array
            for (int i : res) {
                if (i * 2 <= n) {
                    temp.add(i * 2);
                }
            }
            res = temp;
        }
        int[] beautifulArray = new int[n];
        for (int i = 0; i < n; i++) {
            beautifulArray[i] = res.get(i);
        }
        return beautifulArray;
    }
}
