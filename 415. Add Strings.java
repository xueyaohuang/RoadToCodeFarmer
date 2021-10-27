class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        int idx1 = num1.length() - 1;
        int idx2 = num2.length() - 1;
        while (idx1 >= 0 || idx2 >= 0) {
            if (idx1 >= 0) {
                sum += num1.charAt(idx1) - '0';
                idx1--;
            }
            if (idx2 >= 0) {
                sum += num2.charAt(idx2) - '0';
                idx2--;
            }
            sb.append(sum % 10);
            sum /= 10;
        }
        if (sum > 0) {
            sb.append(sum);
        }
        return sb.reverse().toString();
    }
}

class Solution {
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 < len2) {
            return addStrings(num2, num1);
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0, carry = 0;
        int i = len1 - 1, j = len2 - 1;
        while (j >= 0) {
            int n1 = num1.charAt(i) - '0';
            int n2 = num2.charAt(j) - '0';
            sum = n1 + n2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }
        while (i >= 0) {
            int n1 = num1.charAt(i) - '0';
            sum = n1 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            i--;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
