class Solution {
    public int lengthLongestPath(String input) {
        String[] strs = input.split("\n");
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        stack.push(0); // "dummy" length
        for (String s : strs) {
            int numTab = s.lastIndexOf("\t") + 1; // number of "\t"
            while (numTab + 1 < stack.size()) {  // find parent
                stack.pop();  
            }
            int curLen = stack.peek() + s.length() - numTab + 1;  // remove "/t", add"/"
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
