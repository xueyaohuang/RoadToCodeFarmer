class Solution {
    
    // cache，避免计算相同的input
    private Map<String, List<Integer>> map = new HashMap<>();
    
    public List<Integer> diffWaysToCompute(String input) {
        // 检查这个input是否被计算过
        if (map.containsKey(input)) {
            return map.get(input);
        }
        
        int len = input.length();
        List<Integer> res = new ArrayList<>();
        
        // 如果input是纯数字，直接加进去
        if (isDigit(input)) {
            res.add(Integer.parseInt(input));
            map.put(input, res);
            return res;
        }
        
        // divide and conquer，把input按运算符分成左右两边分别计算。
        // 需要在每一个运算符都做分治。
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftRes = diffWaysToCompute(left);
                List<Integer> rightRes = diffWaysToCompute(right);
                for (int a : leftRes) {
                    for (int b : rightRes) {
                        if (c == '+') {
                            res.add(a + b);
                        } else if (c == '-') {
                            res.add(a - b);
                        } else {
                            res.add(a * b);
                        }
                    }
                }
            }
        }
        // cache result
        map.put(input, res);
        return res;
    }
    
    // 判断input是否为纯digit
    private boolean isDigit(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
