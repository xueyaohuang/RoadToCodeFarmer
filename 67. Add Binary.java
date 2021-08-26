class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        StringBuilder asb = (new StringBuilder(a)).reverse();
        StringBuilder bsb = (new StringBuilder(b)).reverse();
        int lena = a.length();
        int lenb = b.length();
        int i = 0;
        while (i < lena || i < lenb) {
            int x = 0;
            int y = 0;
            if (i < lena) {
                x = asb.charAt(i) - '0';
            }
            if (i < lenb) {
                y = bsb.charAt(i) - '0';
            }
            sb.append(x  ^ y ^ carry);
            carry = (x + y + carry) >= 2 ? 1: 0;
            i++;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}

class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        int lena = a.length();
        int lenb = b.length();
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = 0;
        for (; i < Math.min(lena, lenb); i++) {
            int numa = a.charAt(lena - 1 - i) - '0';
            int numb = b.charAt(lenb - 1 - i) - '0';
            sb.append(numa ^ numb ^ carry);
            carry = numa + numb + carry >= 2 ? 1 : 0;
        }
        while (i < lena) {
            int numa = a.charAt(lena - 1 - i) - '0';
            sb.append(numa ^ carry);
            carry = numa + carry >= 2 ? 1 : 0;
            i++;
        }
        while (i < lenb) {
            int numb = b.charAt(lenb - 1 - i) - '0';
            sb.append(numb ^ carry);
            carry = numb + carry >= 2 ? 1 : 0;
            i++;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
