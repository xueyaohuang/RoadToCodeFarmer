
class  Solution  {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result =   new ArrayList<>();
        if (strings == null || strings.length == 0) {
            return result;
        }
        Map<String, List<String>> mapping = new HashMap<>();
        for (String s : strings) {
            String flg = getFlag(s);
            if (!mapping.containsKey(flg)) {
                mapping.put(flg, new ArrayList<String>());
            }
            mapping.get(flg).add(s);
        }
        for (String key : mapping.keySet()) {
            result.add(mapping.get(key));
        }
        return result;
    }
    
    private String getFlag(String s) {
        char[] chars = s.toCharArray();
        int dist = (int)(chars[0] - 'a');
        for (int i = 0; i < chars.length; i++) {
            chars[i] -= dist;
            if (chars[i] < 'a') {
                chars[i] += 26;
            }
        }
        return new String(chars);
    }
}
