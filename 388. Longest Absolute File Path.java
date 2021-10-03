// 用stack存上一层文件夹的长度
class Solution {
    public int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxLen = 0;
        String[] strs = input.split("\n");
        for (String str : strs) {
            int numOfTab = str.lastIndexOf("\t") + 1; // number of "\t"
            while (stack.size() > numOfTab) { // find parent
                stack.pop(); // 把与当前文件（夹）平级的文件（夹）的长度pop出去
            }
            int previousPathLen = stack.isEmpty() ? 0 : stack.peek();
            int curLen = previousPathLen + str.length() - numOfTab; // remove length of "/t"
            if (str.contains(".")) {
                maxLen = Math.max(maxLen, curLen); // if it's a file, don't add to stack
            } else {
                stack.push(curLen + 1); // if it's a dir, add to stack, also add length of "/"
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
