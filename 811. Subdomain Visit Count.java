class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        if (cpdomains == null || cpdomains.length == 0) {
            return new ArrayList<>();
        }
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String cpdomain : cpdomains) {
            String[] cp = cpdomain.split(" ");
            int count = Integer.parseInt(cp[0]);
            String domain = cp[1];
            map.put(domain, map.getOrDefault(domain, 0) + count);
            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    String subdomain = domain.substring(i + 1);
                    map.put(subdomain, map.getOrDefault(subdomain, 0) + count);
                }   
            }
        }
        // for (Map.Entry<String, Integer> entry : map.entrySet()) {
        //     res.add(entry.getValue() + " " + entry.getKey());
        // }
        for (String domain : map.keySet()) {
            res.add(map.get(domain) + " " + domain);
        }
        return res;
    }
}
