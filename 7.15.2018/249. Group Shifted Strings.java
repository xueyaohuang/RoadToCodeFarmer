class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String flag = transform(str);
            if (map.containsKey(flag)) {
                map.get(flag).add(str);
            }
            else {
                map.put(flag, new ArrayList<String>());
                map.get(flag).add(str);
            }
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
