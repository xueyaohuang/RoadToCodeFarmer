/*
In fact, "R" can move to the right until it is blocked by "L" while "L" can move to the left
until it is blocked by "R". So one solution is to remove all "X" in the two strings and check if they are identical.
*/

class Solution {
    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) {
            return false;
        }
        int i = 0, j = 0;
        int len = start.length();
        while (i < len && j < len) {
            while (i < len && start.charAt(i) == 'X') {
                i++;
            }
            while (j < len && end.charAt(j) == 'X') {
                j++;
            }
            char c1 = i < len ? start.charAt(i) : '0';
            char c2 = j < len ? end.charAt(j) : '0';
            
            if (c1 != c2) {
                return false;
            }
            if (c1 == 'L' && i < j) {
                return false;
            }
            if (c1 == 'R' && i > j) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
}
