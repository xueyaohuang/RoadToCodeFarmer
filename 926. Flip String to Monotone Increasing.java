/*
we assume the string after flip is s = '0'*i + '1'*j, the index of first '1' is i

we just need to find the i in the initial string. We make all 1 before index i flip to 0, and make all 0 after index i flip to 1. Then, we get the right answer.

for instance, s = 010110.
if we choose the first 1(index=1) as i, we need to make all 1 before i flip to 0(all 0), make all 0 after i flip to 1(all 2),so the answer is 2.
if we choose the second 1(index=3) as i, the answer is 1 + 1 = 2
if we choose the third 1(index=4) as i, the answer is 2 + 1 = 3
and so on.

I use cnt0 to record the number of 0 after i, cnt1 to record th number of 1 before i
*/
class Solution {
    public int minFlipsMonoIncr(String S) {
        if (S == null || S.length() < 2) {
            return 0;
        }
        int count0 = 0;
        int count1 = 0;
        for (char c : S.toCharArray()) {
            if (c == '0') {
                count0++;
            }
        }
        int minFlip = Math.min(count0, S.length() - count0);
        for (char c : S.toCharArray()) {
            if (c == '0') {
                count0--;
            } else {
                minFlip = Math.min(minFlip, count0 + count1);
                count1++;
            }
        }
        return minFlip;
    }
}
