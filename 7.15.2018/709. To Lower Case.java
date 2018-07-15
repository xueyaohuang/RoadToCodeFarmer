// ASCII Table
// A: 65, Z: 90
// a: 97, z: 122

class Solution {
    public String toLowerCase(String str) {
        char[] ca = str.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (Character.isLetter(ca[i]) && ca[i] - 'a' < 0) {
                ca[i] = (char)(ca[i] + 32);
            }
        }
        return String.valueOf(ca);
    }
}
