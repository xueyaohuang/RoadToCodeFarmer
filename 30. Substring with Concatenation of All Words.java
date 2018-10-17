class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), m = words.length;
        List<Integer> result = new ArrayList<>();
        if (m == 0) return result;
        int l = words[0].length();
        
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        
        for (int i = 0; i < l; i++) {
            for (int j = i; j + m * l <= n; j = j + l) {
                String ss = s.substring(j, j + m * l);
                Map<String, Integer> temp = new HashMap<>();
                for (int k = m - 1; k >= 0; k--) {
                    String w = ss.substring(k * l, (k + 1) * l);
                    int count = temp.getOrDefault(w, 0) + 1;
                    
                    if (count > map.getOrDefault(w, 0)) {
                        j = j + k * l;
                        break;
                    } else if (k == 0) {
                        result.add(j);
                    } else {
                        temp.put(w, count);
                    }
                }
            }
        }
        return result;        
    }
}


class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), m = words.length;
        List<Integer> result = new ArrayList<>();
        if (m == 0) return result;
        int l = words[0].length();
        
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        
        for (int i = 0; i + m * l <= n; i++) {
            String ss = s.substring(i, i + m * l);
            Map<String, Integer> temp = new HashMap<>();
            for (int k = m - 1; k >= 0; k--) {
                String w = ss.substring(k * l, (k + 1) * l);
                int count = temp.getOrDefault(w, 0) + 1;

                if (count > map.getOrDefault(w, 0)) {
                    break;
                } else if (k == 0) {
                    result.add(i);
                } else {
                    temp.put(w, count);
                }
            }
            
        }
        return result;        
    }
}
