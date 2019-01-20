class Solution {
    public String validIPAddress(String IP) {
        if (isValidIPV4Address(IP)) {
            return "IPv4";
        }
        if (isValidIPV6Address(IP)) {
            return "IPv6";
        }
        return "Neither";
    }
    
    private boolean isValidIPV4Address(String IP) {
        if (IP == null || IP.length() == 0) {
            return false;
        }
        int count = 0;
        for (char c : IP.toCharArray()) {
            if (c == '.') {
                count++;
            }
        }
        if (count != 3) {
            return false;
        }
        String[] data = IP.split("\\.");
        if (data.length != 4) {
            return false;
        }
        for (String s : data) {
            if (s == null || s.length() == 0 || s.length() > 3) {
                return false;
            }
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            int num = Integer.parseInt(s);
            // 用!String.valueOf(num).equals(s)检查有没有leading 0.
            if (!String.valueOf(num).equals(s) || num < 0 || num > 255) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidIPV6Address(String IP) {
        Set<Character> validChar = new HashSet(){{
            add('a');
            add('b');
            add('c');
            add('d');
            add('e');
            add('f');
            add('A');
            add('B');
            add('C');
            add('D');
            add('E');
            add('F');
        }};
        
        if (IP == null || IP.length() == 0) {
            return false;
        }
        int count = 0;
        for (char c : IP.toCharArray()) {
            if (c == ':') {
                count++;
            }
        }
        if (count != 7) {
            return false;
        }
        String[] data = IP.split(":");
        if (data.length != 8) {
            return false;
        }
        for (String s : data) {
            if (s == null || s.length() == 0 || s.length() > 4) {
                return false;
            }
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c) && (!validChar.contains(c))) {
                    return false;
                }
            }
        }
        return true;
    }
}
