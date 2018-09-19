class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return "0";
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] temp = new int[len1 + len2 - 1];
        char[] ca1 = num1.toCharArray();
        char[] ca2 = num2.toCharArray();
        // 重点在于这2个for loop
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                temp[i + j] += (ca1[i] - '0') * (ca2[j] - '0'); // be careful here, temp[i + j] += ...
            }
        }
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = len1 + len2 - 2; i >= 0; i--) {
            temp[i] += carry;
            carry = temp[i] / 10;
            sb.append((char)((temp[i] % 10) + '0'));
            
        }
        if (carry > 0) {
            sb.append((char)(carry + '0'));
        }
        return sb.reverse().toString();
    }
}
