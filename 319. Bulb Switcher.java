/*
factor of 6: 1,2,3,6
factor of 7: 1,7
factor of 9: 1,3,9

so all number have even number of factors except square number(e.g: factor of 9:1,3,9).
square number must turn on because of odd number of factors(9: turn on at 1st, off at 3rd, on at 9th)
other number must turn off(6: turn on at 1st, off at 2nd, on at 3rd, off at 6th)
so we only need to compute the number of square number less equal than n
*/
class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}


// naive way
class Solution {
    public int bulbSwitch(int n) {
        int[] bulbs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j %  i == 0) {
                    if (bulbs[j] == 0) {
                        bulbs[j] = 1;
                    } else if (bulbs[j] == 1) {
                        bulbs[j] = 0;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += bulbs[i];
        }
        return res;
    }
}
