class Solution {
    int count;
    public int countArrangement(int n) {
        boolean[] used = new boolean[n + 1];
        backtracking(n, 1, used);
        return count;
    }
    
    private void backtracking(int n, int idx, boolean[] used) {
        if (idx > n) {
            count++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (i % idx != 0 && idx % i != 0) {
                continue;
            }
            used[i] = true;
            backtracking(n, idx + 1, used);
            used[i] = false;
        }
    }
}

/*
123456
*/
