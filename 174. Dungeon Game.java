class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) {
            return 0;
        }
        
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] health = new int[row][col];
        
        health[row - 1][col - 1] = Math.max(1, 1 - dungeon[row - 1][col - 1]);
        for (int i = row - 2; i >= 0; i--) {
            health[i][col - 1] = Math.max(1, health[i + 1][col - 1] - dungeon[i][col - 1]);
        }
        for (int i = col - 2; i >= 0; i--) {
            health[row - 1][i] = Math.max(1, health[row - 1][i + 1] - dungeon[row - 1][i]);
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                health[i][j] = Math.max(1, Math.min(health[i + 1][j] - dungeon[i][j], health[i][j + 1] - dungeon[i][j]));
            }
        }
        return health[0][0];
    }
}
