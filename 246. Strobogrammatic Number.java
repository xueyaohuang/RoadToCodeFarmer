public class Solution {
    /**
     * @param num: a string
     * @return: true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        // write your code here
        int i = 0;
        int j = num.length() - 1;
        while (i <= j) {
            int ni = num.charAt(i) - '0';
            int nj = num.charAt(j) - '0';
            if (ni == 0 || ni == 1 || ni == 8) {
                if (ni != nj) {
                    return false;
                }
            } else if (ni == 9) {
                if (nj != 6) {
                    return false;
                }
            } else if (ni == 6) {
                if (nj != 9) {
                    return false;
                }
            } else {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        if (num.contains("2") || num.contains("3") || num.contains("4") || num.contains("5") || num.contains("7")) {
            return false;
        }
        int len = num.length();
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);
            if (c == '6') {
                if (num.charAt(len - 1 - i) != '9') {
                    return false;
                }
            }
            if (c == '9') {
                if (num.charAt(len - 1 - i) != '6') {
                    return false;
                }
            }
            if (c == '0') {
                if (num.charAt(len - 1 - i) != '0') {
                    return false;
                }
            } 
            if (c == '1') {
                if (num.charAt(len - 1 - i) != '1') {
                    return false;
                }
            }
            if (c == '8') {
                if (num.charAt(len - 1 - i) != '8') {
                    return false;
                }
            }
        }
        return true;
    }
}
