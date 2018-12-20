class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0;
        int len1 = v1.length, len2 = v2.length;
        
        while (i < len1 && i < len2) {
            int num1 = Integer.parseInt(v1[i]);
            int num2 = Integer.parseInt(v2[i]);
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            } else {
                i++;
            }
        }
        if (len1 == len2) {
            return 0;
        } else if (len1 > len2) {
            while (i < len1) {
                if (Integer.parseInt(v1[i]) != 0) {
                    return 1;
                }
                i++;
            }
            return 0;
        } else {
            while (i < len2) {
                if (Integer.parseInt(v2[i]) != 0) {
                    return -1;
                }
                i++;
            }
            return 0;
        }
    }
}
