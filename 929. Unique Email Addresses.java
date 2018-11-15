class Solution {
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }
        
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String[] localDomain = email.split("@");
            String local = localDomain[0];
            String domain = localDomain[1];
            StringBuilder sb = new StringBuilder();
            for (char c : local.toCharArray()) {
                if (c == '.') {
                    continue;
                } else if (c == '+') {
                    break;
                } else {
                    sb.append(c);
                }
            }
            sb.append('@').append(domain);
            String newEmail = sb.toString();
            set.add(newEmail);
        }
        return set.size();
    }
}
