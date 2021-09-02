// 主要因为 .. 是返回上一级，所以需要用到stack的pop，所以要用stack。
class Solution {
    public String simplifyPath(String path) {
        String[] dirs = path.split("/+");
        Deque<String> stack = new ArrayDeque<>();
        for (String dir : dirs) {
            if (dir.equals(".") || dir.equals("")) {
                continue;
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.pollLast());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}

class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() < 2) {
            return path;
        }
        int len = path.length();
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (path.charAt(i) != '/') {
                int end = i;
                while (end < len && path.charAt(end) != '/') {
                    end++;
                }
                String cur = path.substring(i, end);
                i = end;
                if (cur.equals(".")) {
                    continue;
                } else if (cur.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }   
                } else {
                    stack.push(cur);
                }
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.pollLast());
        }
        return sb.toString();
    }
}
