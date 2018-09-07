class Solution {
    public int lengthLongestPath(String input) {
        String[] strs = input.split("\n");
        Deque<Integer> stack = new ArrayDeque<>();
        int maxLen = 0;
        stack.push(0); // "dummy" length
        for (String s : strs) {
            int numTab = s.lastIndexOf("\t") + 1; // number of "\t"
            while (numTab + 1 < stack.size()) {  // find parent
                stack.pop();  // 把与当前文件（夹）平级的文件（夹）的长度pop出去
            }
            int curLen = stack.peek() + s.length() - numTab + 1;  // remove "/t", add"/", "/"是最终结果里面要加的，不是s里面的
            stack.push(curLen);
            if (s.contains(".")) {
                maxLen = Math.max(maxLen, curLen - 1); // if s contains ".", it has no "/", so curLen - 1.
            }
        }
        return maxLen;
    }
}
// "dir \tsubdir1 \tsubdir2 \t\tfile.ext"
    
// dir
//     subdir1
//     subdir2
//         file.ext
