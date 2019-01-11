class Solution {
    public String reverseStr(String s, int k) {
        char[] cstr = s.toCharArray();
        for (int i = 0; i < cstr.length; i += 2 * k) {
            for (int j = Math.min(i + k - 1, cstr.length - 1), l = i; j > l; j--, l++) {
                char temp = cstr[l];
                cstr[l] = cstr[j];
                cstr[j] = temp;
            }
        }
        return String.valueOf(cstr);
    }
}
