class Solution {
    
    class Account {
        String name;
        List<String> emails;
        Account parent;
        int rank;
        public Account(String name) {
            this.name = name;
            this.emails = new ArrayList<String>();
            this.parent = this;
            this.rank = 0;
        }
    }
    
    public Account findRoot(Account acc) {
        if (acc.parent != acc) {
            acc.parent = findRoot(acc.parent);
        }
        return acc.parent;
    }
    public void connect(Account a, Account b) {
        Account rootA = findRoot(a);
        Account rootB = findRoot(b);
        if (rootA.rank > rootB.rank) {
            rootB.parent = rootA;
        } else if (rootA.rank < rootB.rank) {
            rootA.parent = rootB;
        } else {
            rootB.parent = rootA;
            rootA.rank++;
        }    
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Account> map = new HashMap<>();
        List<Account> list = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            Account newAcc = new Account(name);
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                if (map.containsKey(email)) {
                    connect(newAcc, map.get(email));
                } else {
                    newAcc.emails.add(email);
                    map.put(email, newAcc);
                }
            }
            list.add(newAcc);
        }
        for (Account acc : list) {
            if (acc.parent != acc) {
                Account root = findRoot(acc);
                root.emails.addAll(acc.emails);
            }
        }
        for (Account acc : list) {
            if (acc.parent == acc) {
                List<String> temp = new ArrayList<>();
                temp.add(acc.name);
                Collections.sort(acc.emails, (s1, s2) -> s1.compareTo(s2));
                temp.addAll(acc.emails);
                res.add(temp);
            }
        }
        return res;
    }
}


class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailUser = new HashMap<>(); // k: email, v: name
        Map<String, Set<String>> emailGraph = new HashMap<>(); // k: email, v: emails of the same username. Build the email graph.
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
