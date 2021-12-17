/*
In each loop, We caculate cur[i], which represent the sum of Uniq() for all substrings whose last char is S.charAt(i).

For example,
S = 'ABCBD'
When i = 2, cur[2] = Uniq('ABC') + Uniq('BC') + Uniq('C')
When i = 3, cur[3] = Uniq('ABCB') + Uniq('BCB') + Uniq('CB') + Uniq('B')

Notice, we append char 'B' into each previous substrings. Only in substrings 'CB' and 'B', the char 'B' can be identified as uniq.
The contribution of 'B' from cur[2] to cur[3] is i + 1 - lastPosition['B']. (i + 1 means the number of substrings that end at i)
At the same time, in substrings 'ABCB', 'BCB', the char 'B' can‘t be identified as uniq any more, the previous contribution of 'B' should be removed.

So we have'cur[i] = cur[i - 1] - contribution[S.charAt(i)] + (i - lastPosition[S.charAt(i)])
Then the new contribution[S.charAt(i)] = i - lastPosition[S.charAt(i)]

The final result is the sum of all cur[i].
*/
class Solution {
    public int uniqueLetterString(String s) {
        // char最后一次出现的位置
        int[] lastPosition = new int[26];
        // 当前loop，每个char贡献的次数
        int[] count = new int[26];
        int res = 0;
        // 到i为止，所有substring的Unique Characters个数
        int countEndHere = 0;
        for (int i = 0; i < s.length(); i++) {
            int curChar = s.charAt(i) - 'A';
            countEndHere -= count[curChar];
            count[curChar] = i + 1 - lastPosition[curChar];
            countEndHere += count[curChar];
            lastPosition[curChar] = i + 1;
            res += countEndHere;
        }
        return res;
    }
}
