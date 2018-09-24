class Solution {
    public boolean judgeSquareSum(int c) {
        int i = 0;
        int j = (int) Math.sqrt(c);
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum == c) {
                return true;
            }
            else if (sum > c) {
                j--;
            }
            else {
                i++;
            }
        }
        return false;
    }
}

class Solution {
    public boolean judgeSquareSum(int c) {
        for (int i = 0; i * i <= c / 2; i++) {
            int rest = c - i * i;
            int root = (int)Math.sqrt(rest);
            if (root * root == rest) {
                return true;
            }
        }
        return false;
    }
}

