/*
This problem has a lot of edge cases to be considered:

1. overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
2. 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
3. a little trick is that we should save the value that is to be multiplied in the next recursion.
*/

class Solution {
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtracking(num, target, 0, 0, 0, sb, res);
        return res;
    }
    // prev: 之前的累计result
    // mul只在乘法的时候有用
    private void backtracking(String num, int target, long prev, int start, long mul, StringBuilder sb, List<String> res) {
        if (start == num.length()) {
            if (prev == target) {
                res.add(sb.toString());
                return;
            }
        }
        for (int i = start; i < num.length(); i++) {
            // 跳过0023这种有leading zero的情况, 所以是num.charAt(start) == '0'而不是num.charAt(i) == '0'
            if (num.charAt(start) == '0' && i != start) {
                break;
            }
            long cur = Long.parseLong(num.substring(start, i + 1));
            int len = sb.length();
            if (start == 0) {
                // 第一个数前面没有符号
                backtracking(num, target, cur, i + 1, cur, sb.append(cur), res);
                sb.setLength(len);
            } else {
                backtracking(num, target, prev + cur, i + 1, cur, sb.append("+").append(cur), res);
                sb.setLength(len);
            
                backtracking(num, target, prev - cur, i + 1, -cur, sb.append("-").append(cur), res);
                sb.setLength(len);
                
                // mul是之前的
                backtracking(num, target, prev - mul + mul * cur, i + 1, mul * cur, sb.append("*").append(cur), res);
                sb.setLength(len);
            } 
        }
    }
}
