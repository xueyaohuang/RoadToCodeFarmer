/*
DP.

Sub-question of DP is:
Suppose that you have a string s, counter_flip flips are required for the string, and there were counter_one '1's in the original string s.

Let's see the next step of DP.

Now a new character is appended to the original string. The question is that, how should counter_flip be updated?
When '1' comes, no more flip should be applied, since '1' is appended to the tail of the original string.
When '0' comes, there are two options for us: flip the newly appended '0' to '1', after counter_flip flips for the original string; 
or flip counter_one '1' in the original string to '0'. 
Hence, the result of the next step of DP, in the '0' case, is min(counter_flip + 1, counter_one);.

Based on these analysis, the solution comes.
*/
class Solution {
    public int minFlipsMonoIncr(String s) {
        int res = 0;
        int countOne = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                // When '1' comes, no more flip should be applied
                countOne++;
            } else {
                // When '0' comes, either flip 0 to 1, which is res + 1, or flip all 1s tto 0, which is countOne
                res = Math.min(res + 1, countOne);
            }
        }
        return res;
    }
}
