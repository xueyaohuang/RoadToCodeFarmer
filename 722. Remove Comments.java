// 1. If we see '//' we stop reading the current line, and add whatever characters we have seen to the result.
// 2. If we see '/*' then we start the multiline comment mode and we keep on ignoring characters until we see '*/'.
// 3. If the current character is neither of the above two and the multiline comment mode is off, then we add that character to the current line.
class Solution {
    public List<String> removeComments(String[] source) {
        if (source == null || source.length == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean blockComment = false;
        for (String line : source) {
            for (int i = 0; i < line.length(); i++) {
                if (blockComment) {
                    if (line.charAt(i) == '*' && i < line.length() - 1 && line.charAt(i + 1) == '/') {
                        blockComment = false;
                        i++;  //skip '/' on next iteration of i
                    }
                } else {
                    if (line.charAt(i) == '/' && i < line.length() - 1 && line.charAt(i + 1) == '/') {
                        break;
                    } else if (line.charAt(i) == '/' && i < line.length() - 1 && line.charAt(i + 1) == '*') {
                        blockComment = true;
                        i++;  //skip '*' on next iteration of i
                    } else {
                        sb.append(line.charAt(i));
                    }
                }
            }
            
            // 如果没有!blockComment，
            // ["a/*comment", "line", "more_comment*/b"]的结果是
            // ["a","b"]而不是["ab"]
            if (!blockComment && sb.length() > 0) {
                res.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return res;
    }
}
