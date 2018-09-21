public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append("#").append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int idx = 0;
        while (idx < s.length()) {
            int hashTag = s.indexOf('#', idx);
            int len = Integer.valueOf(s.substring(idx, hashTag));
            res.add(s.substring(hashTag + 1, hashTag + len + 1));
            idx = hashTag + len + 1;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
