class FileSystem {
    
    Map<String, Integer> paths;

    public FileSystem() {
        paths = new HashMap<>();
        paths.put("", Integer.MIN_VALUE);
    }
    
    public boolean createPath(String path, int value) {
        if (paths.containsKey(path)) {
            return false;
        }
        int lastSlashIdx = path.lastIndexOf('/');
        // if (lastSlashIdx == 0) {
        //     paths.put(path, value);
        //     return true;
        // }
        String parent = path.substring(0, lastSlashIdx);
        if (!paths.containsKey(parent)) {
            return false;
        }
        paths.put(path, value);
        return true;
    }
    
    public int get(String path) {
        
        return paths.getOrDefault(path, -1);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
