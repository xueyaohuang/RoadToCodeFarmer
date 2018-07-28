class Solution {
    class Account {
        String name;
        List<String> emails;
        Account parent;
        public Account(String name) {
            this.name = name;
            this.emails = new ArrayList<String>();
            this.parent = this;
        }
    }
    public Account findRoot(Account acc) {
        while (acc.parent != acc) {
            acc = acc.parent;
        }
        return acc;
    }
    public void connect(Account a, Account b) {
        Account rootA = findRoot(a);
        Account rootB = findRoot(b);
        rootA.parent = rootB;
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
                Collections.sort(acc.emails);
                temp.addAll(acc.emails);
                res.add(temp);
            }
        }
        return res;
    }
}
