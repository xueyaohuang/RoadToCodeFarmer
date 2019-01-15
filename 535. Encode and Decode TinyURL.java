public class Codec {
    
    // key:shortUrl, value:longUrl
    private HashMap<String, String> map = new HashMap<>();
    private String dict = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    private Random rand = new Random();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {    
        int len = dict.length();
        String shortUrl = getEncodedUrl(len);
        // It's possible that a randomly generated code has already been generated before. 
        // In that case, another random code is generated instead. 
        // Repeat until we have a code that's not already in use.
        while (map.containsKey(shortUrl)) {
            shortUrl = getEncodedUrl(len);
        }
        map.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
    
    private String getEncodedUrl(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int idx = rand.nextInt(len);
            sb.append(dict.charAt(idx));
        }
        return "http://tinyurl.com/" + sb.toString();
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
