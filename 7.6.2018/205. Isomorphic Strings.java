class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.equals(t)) {
            return true;
        }
        char[] map = new char[256];
        boolean[] used = new boolean[256];
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (map[cs[i]] == 0) {
                if (used[ct[i]]) {
                    return false;
                }
                map[cs[i]] = ct[i];
                used[ct[i]] = true;
            }
            else {
                if (map[cs[i]] != ct[i]) {
                    return false;
                }    
            }
        }
        return true;
    }
}

class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (map.containsKey(cs)) {
                if (map.get(cs) != ct) {
                    return false;
                }
            }
            else if (! map.containsValue(ct)) {
                map.put(cs, ct);
            }
            else {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> maps = new HashMap<>();
        Map<Character, Integer> mapt = new HashMap<>();
        for (Integer i = 0; i < s.length(); i++) {
            if (maps.put(s.charAt(i), i) != mapt.put(t.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }
}

/*for (int i = 0; i < s.length(); i++)会报错，因为if (maps.put(s.charAt(i), i) != mapt.put(t.charAt(i), i))
是比较reference（address）,而不是content comparison。
如果用int i，由于Map<Character, Integer> 放进去value的类型是Integer，用int construct Integer时，maps和mapt会生成
2个不同的Integer object。但是如果同Integer i, maps和mapt在put时，放入的是同一个integer object。*/
