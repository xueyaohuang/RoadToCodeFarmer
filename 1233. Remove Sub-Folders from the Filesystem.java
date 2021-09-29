// In ASCII, '/' is before 'a': e.g., '/a', '/a/b', '/aa'
// After sorting the folder array, we only need to consider if the current folder is a subfolder of the previous one or not.
// O(n * m * logn), n is length of folder, m is average length of folder[i]
// the sort is based on merge sort for Object and time complexity of merge sort is O(n * logn). That means n * logn times comparing happened.
// For this question, it just makes the comparing time be O(m). 
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> res = new ArrayList<>();
        res.add(folder[0]);
        for (int i = 1; i < folder.length; i++) {
            String lastFolder = res.get(res.size() - 1);
            // 注意要加上"/"
            if (!folder[i].startsWith(lastFolder + "/")) {
                res.add(folder[i]);
            }      
        }
        return res;
    }
}

// Trie Time & space: O(n * m), where n = folder.length, m = average size of the strings in folder.
class Solution {
    class TrieNode {
        String word;
        Map<String, TrieNode> map;
        public TrieNode() {
            map = new HashMap<>();
        }
    }
    TrieNode root = new TrieNode();
    List<String> res = new ArrayList<>();
    public List<String> removeSubfolders(String[] folder) {
        buildTrie(folder);
        addFolders(root);
        return res;
    }
    
    private void addFolders(TrieNode root) {
        for (Map.Entry<String, TrieNode> entry : root.map.entrySet()) {
            if (entry.getValue().word != null) {
                // System.out.println(entry.getKey());
                res.add(entry.getValue().word);
            } else {
                addFolders(entry.getValue());
            }
        }
    }
    
    private void buildTrie(String[] folder) {
        outer:
        for (String f : folder) {
            TrieNode cur = root;
            String[] dirs = f.substring(1).split("/");
            for (String dir : dirs) {
                if (!cur.map.containsKey(dir)) {
                    cur.map.put(dir, new TrieNode());
                }
                cur = cur.map.get(dir);
                if (cur.word != null) {
                    continue outer;
                }
            }
            cur.word = f;
        }
    }
}

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> res = new ArrayList<>();
        Set<String> folderSet = new HashSet<>(Arrays.asList(folder));
        for (int i = 0; i < folder.length; i++) {
            String f = folder[i];
            if (!folderSet.contains(f)) {
                continue;
            }
            res.add(f);
            for (int j = i + 1; j < folder.length; j++) {
                if (folder[j].startsWith(f + "/")) {
                    folderSet.remove(folder[j]);
                }
            }
        }
        return res;
    }
}
