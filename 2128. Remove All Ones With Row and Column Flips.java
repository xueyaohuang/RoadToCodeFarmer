/*
Basically the "pattern" of each row should be the same, by pattern, I mean following:

    001100 and 001100 are the same pattern
    001100 and 110011 (the invert of original) are the same pattern

Only in above situation, one matrix can be converted to all zero
*/
class Solution {
    public boolean removeOnes(int[][] grid) {
        int[] pattern1 = grid[0];
        int[] pattern2 = getRevertPatterrn(pattern1);
        for (int i = 1; i < grid.length; i++) {
            if (!arrayEqual(grid[i], pattern1) && !arrayEqual(grid[i], pattern2)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean arrayEqual(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
    
    private int[] getRevertPatterrn(int[] p) {
        int[] res = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            if (p[i] == 0) {
                res[i] = 1;
            }
        }
        return res;
    }
}
