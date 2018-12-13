class Solution {
    public String solveEquation(String equation) {
        if (equation == null || equation.length() == 0) {
            return "No solution";
        }
        String[] parts = equation.split("=");
        int[] left = evaluateExpression(parts[0]);
        int[] right = evaluateExpression(parts[1]);
        int coef = left[0] - right[0];
        int cst = right[1] - left[1];
        if (coef == 0 && cst != 0) {
            return "No solution";
        }
        if (coef == 0 && cst == 0) {
            return "Infinite solutions";
        }
        return "x=" + cst / coef;
    }
    
    private int[] evaluateExpression(String expression) {
        // x(?=y)	Matches 'x' only if 'x' is followed by 'y'. Also known as a lookahead.
        // [abc]	Matches any of the enclosed characters. Also known as a character set. 
        // You can create range of characters using the hyphen character such as A-Z (A to Z). 
        // Note that in character sets, special characters (., *, +) do not have any special meaning.
        String[] tokens = expression.split("(?=[-+])");
        int[] coefficient = new int[2];
        for (String token : tokens) {
            if (token.equals("x") || token.equals("+x")) {
                coefficient[0]++;
            } else if (token.equals("-x")) {
                coefficient[0]--;
            } else if (token.contains("x")) {
                coefficient[0] += Integer.parseInt(token.substring(0, token.length() - 1));
            } else {
                coefficient[1] += Integer.parseInt(token);
            }
        }
        return coefficient;
    }
}
