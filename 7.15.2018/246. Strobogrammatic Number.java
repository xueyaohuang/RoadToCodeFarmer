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
