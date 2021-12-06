class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] temp = new int[len1 + len2 - 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                temp[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = len1 + len2 - 2; i >= 0; i--) {
            temp[i] += carry;
            sb.append(temp[i] % 10);
            carry = temp[i] / 10;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
