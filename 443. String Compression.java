// 1. 记录当前的char
// 2. 数当前的char有几个
// 3. set当前的char
// 4. set当前char的个数
// 1和2用idx，3和4用idxInPlace
class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int idxInPlace = 0;
        int idx = 0;
        while (idx < chars.length) {
            char curChar = chars[idx];
            int count = 0;
            while (idx < chars.length && chars[idx] == curChar) {
                idx++;
                count++;
            }
            chars[idxInPlace++] = curChar;
            if (count > 1) {       
                for (char digit : Integer.toString(count).toCharArray()) {
                    chars[idxInPlace++] = digit;
                }
            }
        }
        return idxInPlace;
    }
}
