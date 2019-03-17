/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
    
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    
    public void cleanRoom(Robot robot) {
        if (robot == null) {
            return;
        }
        Set<String> set = new HashSet<>();
        dfsTraverse(0, 0, 0, set, robot);
    }
    
    // 由于move只能向上，所以需要dir，否则会出现方向向上，实际上却往下移动的情况
    private void dfsTraverse(int x, int y, int curDir, Set<String> set, Robot robot) {
        robot.clean();
        set.add(x + " " + y);
        for (int i= 0; i < 4; i++) {
            int nextDir = (curDir + i) % 4;
            int nextX = x + dirs[nextDir][0];
            int nextY = y + dirs[nextDir][1];
            if (!set.contains(nextX + " " + nextY) && robot.move()) {
                dfsTraverse(nextX, nextY, nextDir, set, robot);
                // back track, 回到之前的那个点，不同于一般的dfs，因为这个robot是在真实的grid上移动
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            robot.turnLeft();
        }
    }
}
