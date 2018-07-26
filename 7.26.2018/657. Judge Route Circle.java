class Solution {
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() % 2 == 1) {
            return false;
        }
        int x = 0;
        int y = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'U') {
                y++;
            } else if (c == 'D') {
                y--;
            } else if (c == 'R') {
                x++;
            } else {
                x--;
            } 
        }
        return x == 0 && y == 0;
    }
}
