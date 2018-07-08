public class Codec {
    
    private HashMap<String, String> map = new HashMap<>();
    String dict = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {      
        Random random = new Random();
        String url = "";
        String shortUrl = "http://tinyurl.com/";
        // It's possible that a randomly generated code has already been generated before. In that case, another random code is generated instead. Repeat until we have a code that's not already in use.
        while (!map.containsValue(longUrl)) {
            for (int i = 0; i < 6; i++) {
                int idx = random.nextInt(dict.length());
                url += dict.charAt(idx);
            }
            shortUrl = "http://tinyurl.com/" + url;
            if (!map.containsKey(shortUrl)) {
                map.put(shortUrl, longUrl);
            }      
        }
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
