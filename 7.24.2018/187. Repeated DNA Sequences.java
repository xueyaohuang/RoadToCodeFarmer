class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String substr = s.substring(i, i + 10);
            if (map.containsKey(substr)) {
                if (map.get(substr) == 1) {
                    list.add(substr);
                    map.put(substr, 2);
                }
            }
            else {
                map.put(substr, 1);
            }
        }
        return list;
    }
}

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() < 10) {
            return res;
        }
        
        char[] map = new char[255];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;
        
        int mask = 0xfffff;//20bit,10个字母，每个字母占2bit
        int val = 0;
        char[] schar = s.toCharArray();
        for (int i = 0; i < 9; i++) {
            val = (val << 2) | (map[schar[i]] & 3);
        }
        
        byte[] record = new byte[1 << 20];
        for (int i = 9; i < schar.length; i++) {
            val = ((val << 2) & mask) | (map[schar[i]] & 3);
            
            if (record[val] == 1) {
                res.add(s.substring(i-9, i+1));
            }
            if (record[val] < 2) {
                record[val] ++;
            }
        }
        return res;
    }
}
