// best
class Solution {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0)
                words = helper(num % 1000) +THOUSANDS[i] + " " + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private String helper(int num) {
        if (num == 0)
            return "";
        else if (num < 20)
            return LESS_THAN_20[num] + " ";
        else if (num < 100)
            return TENS[num / 10] + " " + helper(num % 10);
        else
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
}

// my own stupid sol
class Solution {
    
    String[] lessThanTwenty = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    String[] tens = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] scale = new String[]{"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        String s = String.valueOf(num);
        int len = s.length();
        int segment = len / 3;
        int rest = len - segment * 3;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < segment; i++) {
            String mul = scale[i];
            String cur = s.substring(len - Math.min(3, len) - i * 3, len - i * 3);
            String hundred = parseHundreds(cur);
            if (!hundred.equals("")) {
                sb.insert(0, " " + mul).insert(0, " " + hundred);
                while (sb.charAt(sb.length() - 1) == ' ') {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        if (rest > 0 ) {
            String cur = "";
            if (rest == 1) {
                cur = "00" + s.substring(0, 1);
            }
            if (rest == 2) {
                cur = "0" + s.substring(0, 2);
            }
            
            String mul = scale[segment];
            String hundred = parseHundreds(cur);
            sb.insert(0, " " + mul).insert(0, " " + hundred);
            while (sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
    
    private String parseHundreds(String num) {
        int i = 0;
        while (i < 3 && num.charAt(i) == '0') {
            i++;
        }
        if (i == 3) {
            return "";
        }
        if (i == 2) {
            return lessThanTwenty[Integer.parseInt(num.substring(i))];
        }
        StringBuilder sb = new StringBuilder();
        if (i == 1) {
            if (Integer.parseInt(num.substring(i)) <= 20) {
                return lessThanTwenty[Integer.parseInt(num.substring(i))];
            } else {
                sb.append(tens[num.charAt(1) - '0'] + " " + lessThanTwenty[num.charAt(2) - '0']);
            }
        }
        if (i == 0) {
            sb.append(lessThanTwenty[num.charAt(0) - '0'] + " ").append("Hundred" + " ");
            if (Integer.parseInt(num.substring(1, 3)) < 20) {
                sb.append(lessThanTwenty[Integer.parseInt(num.substring(1, 3))]);
            } else {
                sb.append(tens[num.charAt(1) - '0'] + " ").append(lessThanTwenty[num.charAt(2) - '0']);
            }
        } 
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
