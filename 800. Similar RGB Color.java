class Solution {
    
    int[] candidates = {0, 17, 34, 51, 68, 85, 102, 119, 136, 153, 170, 187, 204, 221, 238, 255};
    
    public String similarRGB(String color) {
        StringBuilder sb = new StringBuilder("#");
        for (int i = 1; i < 6; i += 2) {
            String cur = color.substring(i, i + 2);
            if (cur.charAt(0) == cur.charAt(1)) {
                sb.append(cur);
            } else {
                sb.append(getNearestColor(cur));
            }
        }
        return sb.toString();
    }
    
    private String getNearestColor(String color) {
        int val = Integer.parseInt(color, 16);
        int i = 1;
        for (; i < candidates.length; i++) {
            if (candidates[i] >= val) {
                break;
            }
        }
        int res = val - candidates[i - 1] > candidates[i] - val ? candidates[i] : candidates[i - 1];
        return res == 0 ? "00" : Integer.toHexString(res);
    }
}
