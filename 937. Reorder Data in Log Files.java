class LetterLog {
    String id;
    String content;
    public LetterLog(String id, String content) {
        this.id = id;
        this.content = content;
    }
}

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<LetterLog> letLogs = new ArrayList<>();
        List<String> digLogs = new ArrayList<>();
        for (String log : logs) {
            String[] parts = log.split(" ", 2);
            if (Character.isLetter(parts[1].charAt(0))) {
                letLogs.add(new LetterLog(parts[0], parts[1]));
            } else {
                digLogs.add(log);
            }
        }
        Collections.sort(letLogs, (a, b) -> a.content.equals(b.content) ? a.id.compareTo(b.id) : a.content.compareTo(b.content));
        String[] res = new String[logs.length];
        for (int i = 0; i < letLogs.size(); i++) {
            res[i] = letLogs.get(i).id + " " + letLogs.get(i).content;
        }
        for (int i = letLogs.size(); i < res.length; i++) {
            res[i] = digLogs.get(i - letLogs.size());
        }
        return res;
    }
}
