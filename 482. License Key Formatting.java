class Solution {
    public String licenseKeyFormatting(String S, int K) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        int len = S.length();
        for (int i = len - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c != '-') {
                if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                    count++;
                } else {
                    sb.append(c);
                    count++;
                }
            }
            if (count == K) {
                sb.append('-');
                count = 0;
            }
        }
        if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString();
    }
}
