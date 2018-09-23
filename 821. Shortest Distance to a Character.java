//First forward pass to find shortest distant to character on left.
//Second backward pass to find shortest distant to character on right.
class Solution {
    public int[] shortestToChar(String S, char C) {

        int len = S.length();
        int[] res = new int[len];
        int pos = -1;
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) == C) {
                pos = i;
            }
            res[i] = pos == -1 ? len - 1 : i - pos;
        }
        pos = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (S.charAt(i) == C) {
                pos = i;
            }
            res[i] = Math.min(res[i], pos == -1 ? res[i] : pos - i);
        }
        return res;
    }
}

class Solution {
    public int[] shortestToChar(String S, char C) {
        
        List<Integer> list = new ArrayList<>();
        List<Integer> itv = new ArrayList<>();
        int len = S.length();
        int[] res = new int[len];
        
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) == C) {
                list.add(i);
            }
        }
        
        itv.add(-1); // 先加入-1，因为最下面的loop，j从itv.get(i) + 1开始，如果要取到0，需要最开始时-1.
        int listSize = list.size();
        for (int i = 0; i < listSize - 1; i++) {
            itv.add((list.get(i) + list.get(i + 1)) / 2);
        }
        itv.add(len - 1);
        
        int itvSize = itv.size();
        for (int i = 0; i < itvSize - 1; i++) {
            // 前面不取等号，后面取等号。
            // 因为求中点时，如果和是奇数，中点时偏小一点的（比如17/2=8），所以这个中点一定要在区间的右边取到。
            // 如果在区间左边取到，结果会大1.
            for (int j = itv.get(i) + 1; j <= itv.get(i + 1); j++) { 
                res[j] = Math.abs(list.get(i) - j);
            }
        }
        return res;
    }
}
