class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (String s : strings) {
            String ShiftedStr = makeShiftedString(s);
            map.putIfAbsent(ShiftedStr, new ArrayList<>());
            map.get(ShiftedStr).add(s);
        }
        for (List<String> l : map.values()) {
            res.add(l);
        }
        return res;
    }
    
    private String makeShiftedString(String s) {
        int diff = s.charAt(0) - 'a';
        // if starts with 'a', doesn't need to shift
        if (diff == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            char shiftedChar = (char)(c - diff);
            if (shiftedChar < 'a') {
                shiftedChar = (char)(shiftedChar + 26);
            }
            sb.append(shiftedChar);
        }
        return sb.toString();
    }
}

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String flag = transform(str);
            map.putIfAbsent(flag, new ArrayList<>());
            map.get(flag).add(str);
        }
        // map.values().forEach(temp -> res.add(temp)); However, it is very slow.
        for (String str : map.keySet()) {
            res.add(map.get(str));
        }
//         // also fast. map.values() returns a Collections view of the values.
//         for (List<String> list : map.values()) {
//             res.add(list);
//         }
        return res;
    }
    private String transform(String s) {
        char[] ca = s.toCharArray();
        int charValue = ca[0] - 'a';
        for (int i = 0; i < ca.length; i++) {
            ca[i] -= charValue;
            if (ca[i] < 'a') {
                ca[i] += 26;
            }
        }
        return String.valueOf(ca);
    }
}
