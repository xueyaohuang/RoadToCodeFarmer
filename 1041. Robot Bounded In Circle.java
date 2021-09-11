// 走完instructions后，如果停下来的方向跟最开始不一样，再走几个instructions就一定能形成circle
// 如果停下来的方向跟最开始一样，必须停在（0, 0）
class Solution {
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        int dir = 0;
        for (int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            if (c == 'G') {
                if (dir == 0) {
                    y++;
                } else if (dir == 1) {
                    x--;
                } else if (dir == 2) {
                    y--;
                } else {
                    x++;
                }
            } else if (c == 'L') {
                dir = (dir + 1) % 4;
            } else {
                // dir = (dir - 1 + 4) % 4 in order to keep dir positive
                dir = (dir + 3) % 4;
            }
        }
        if (dir == 0) {
            return x == 0 && y == 0;
        }
        return true;
    }
}
