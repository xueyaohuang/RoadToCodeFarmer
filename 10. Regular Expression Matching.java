// 注意，*当删除用的时候，只能删除紧挨着*前面的一个char
// 注意 a*，如果a用一次，结果是a，如果a用2次，结果才是aa（不是用一次就是aa）
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        int lens = s.length();
        int lenp = p.length();
        // canMatch[i][j]表示s[:i - 1]与p[:j - 1]是否match
        boolean[][] canMatch = new boolean[lens + 1][lenp + 1];
        canMatch[0][0] = true; // s和p都为空，自然就是match
        
        // 考虑*删除前面的内容的情况，比如：
        // s=aab，p=x*aab。x*可以被删除，所以需要canMatch[0][2]为true。
        for (int i = 0; i < lenp; i++) {
            if (p.charAt(i) == '*' && canMatch[0][i - 1]) { // 删除*前面的一个char
                canMatch[0][i + 1] = true;
            }
        }
        
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lenp; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    canMatch[i][j] = canMatch[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    // 只能删除*前面的char
                    if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        canMatch[i][j] = canMatch[i][j - 2];
                    } else {
                        
                        canMatch[i][j] = canMatch[i][j - 2] || canMatch[i][j - 1] || canMatch[i - 1][j];
                        
                        // 删除*前面的char
                        // canMatch[i][j] |= canMatch[i][j - 2];
                        // *前面的char只用一次。比如a和a*要匹配，a*的意思是只把a用一次。这样相当于a*一共2个char变成只有a一个char，所以j-1.
                        // canMatch[i][j] |= canMatch[i][j - 1];
                        // *前面的char用多次。比如aa和a*要匹配，*前面的a需要用2次。p的第j位用第j-1位的char与s的第i位对上了，
                        // 所以s的第i位不用考虑了，但是p的第j位还是要考虑，因为即使*前面的char只用一次，都需要第j位的*。
                        // canMatch[i][j] |= canMatch[i - 1][j];
                    }
                }
            }
        }
        return canMatch[lens][lenp];
    }
}
