// String concatenation ("str1 + str2") vs string builder.
// Outside loop, java compiler automatically convert string concatenation to string builder, however inside loop the compiler does not
// do this. So we can use string concatenation outside loop but not inside a loop.

class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
