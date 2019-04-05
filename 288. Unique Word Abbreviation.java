class ValidWordAbbr {
    
    private Map<String, Integer> map;
    private Set<String> set;

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        set = new HashSet<>();
        for (String s : dictionary) {
            if (!set.add(s)) {
                continue;
            }
            String abbr = getAbbr(s);
            map.put(abbr, map.getOrDefault(abbr, 0) + 1);
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (!map.containsKey(abbr)) {
            return true;
        } else {
            if (map.get(abbr) > 1) {
                return false;
            } else {
                return set.contains(word);
            }
        }
    }
    
    private String getAbbr(String s) {
        if (s.length() <= 2) {
            return s;
        }
        return s.substring(0, 1) + (s.length() - 2) + s.substring(s.length() - 1); 
    }
}

class ValidWordAbbr {
    
    private Map<String, String> map;
    
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String word : dictionary) {
            String abbr = getAbbr(word);
            String value = map.get(abbr);
            if (value == null) {
                map.put(abbr, word);
            } else if (!value.equals(word)) {
                map.put(abbr,"");
            }
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        String temp = map.get(abbr);
        return temp == null || temp.equals(word);
    }
    
    public String getAbbr(String word) {
        int n = word.length();
        if (n <= 2) {
            return word;
        }
        return word.charAt(0) + String.valueOf(n - 2) + word.charAt(n - 1);
    }
}
