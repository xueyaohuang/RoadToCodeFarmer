class Solution {
    
    class Account {
        
        String name;
        List<String> emails;
        int rank;
        Account parent;
        
        public Account(String name) {
            this.name = name;
            this.emails = new ArrayList<>();
            this.rank = 0;
            this.parent = this;
        }
        
    }
    
    public Account find(Account p) {  // path compress, get a flat tree
        if (p != p.parent) {
            p.parent = find(p.parent);
        }
        return p.parent;
    }
    
    public void union(Account p, Account q) {  // use rank to accelerate
        Account rootp = find(p);
        Account rootq = find(q);
        if (rootp == rootq) {
            return;
        }
        if (rootp.rank > rootq.rank) {
            rootq.parent = rootp;
        } else if (rootp.rank < rootq.rank) {
            rootp.parent = rootq;
        } else {
            rootq.parent = rootp;
            rootp.rank++;
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        // k: email, v: account
        Map<String, Account> accountMap = new HashMap<>();
        List<Account> accountList = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        
        for (List<String> account : accounts) {
            String name = account.get(0);
            Account acc = new Account(name);
            int size = account.size();
            
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (accountMap.containsKey(email)) {  // 必须检查
                    union(acc, accountMap.get(email));
                } else {
                    acc.emails.add(email);
                    accountMap.put(email, acc);
                }
            }
            accountList.add(acc);
        }
        
        for (Account acc : accountList) {
            if (acc.parent != acc) {
                Account accRoot = find(acc);
                accRoot.emails.addAll(acc.emails);
            }
        }
        
        for (Account acc : accountList) {
            if (acc.parent == acc) {
                List<String> curAccount = new ArrayList<>();
                curAccount.add(acc.name);
                Collections.sort(acc.emails);
//                 Collections.sort(acc.emails, (e1, e2) -> e1.compareTo(e2));
                curAccount.addAll(acc.emails);
                res.add(curAccount);
            }
        }
        
        return res;
    }
}


class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        Map<String, String> emailUser = new HashMap<>(); // k: email, v: name
        Map<String, Set<String>> emailGraph = new HashMap<>(); // k: email, v: emails of the same user. Build the email graph.
        Set<String> visited = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                if (!emailGraph.containsKey(email)) {
                    emailGraph.put(email, new HashSet<String>());
                }
                if (i != 1) {
                    emailGraph.get(email).add(acc.get(i - 1));
                    emailGraph.get(acc.get(i - 1)).add(email);
                }
                emailUser.put(email, name);
            }
        }
        for (String email : emailUser.keySet()) {
            List<String> acc = new LinkedList<>();
            if (visited.add(email)) {  
                dfs(emailGraph, acc, visited, email);
                Collections.sort(acc);
                acc.add(0, emailUser.get(email));
                res.add(acc);
            }
        }
        return res;
    }
    private void dfs(Map<String, Set<String>> emailGraph, List<String> acc, Set<String> visited, String email) {
        acc.add(email);
        for (String eml : emailGraph.get(email)) {
            if (visited.add(eml)) {
                dfs(emailGraph, acc, visited, eml);
            }
        }
    }
}
