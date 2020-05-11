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

class Solution {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
