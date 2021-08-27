class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0; // it actuall means how many CLOSE parentheses we should add
        int close = 0; // it actuall means how many OPEN parentheses we should add
        for (char c : s.toCharArray()) {
            if (c == '(') {
               open++;
            } else if (open > 0) {
                // if open > 0 we find a valid pair
                open--;
            } else {
                // if open < 0, we need to add one open
                close++;
            }
        }
        return open + close;
    }
}
