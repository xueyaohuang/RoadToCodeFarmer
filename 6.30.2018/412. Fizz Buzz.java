// follow up question: https://leetcode.com/problems/fizz-buzz/discuss/89936/Java-Fuzz-Buzz-Follow-up(no-if-else-and-extendable)


class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                res.add("FizzBuzz");
            }
            else if (i % 3 == 0) {
                res.add("Fizz");
            }
            else if (i % 5 == 0){
                res.add("Buzz");
            }
            else {
                // res.add(i + "");
                res.add(String.valueOf(i));
            }
        }
        return res;
    }
}

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new LinkedList();
        int cnt3 = 3;
        int cnt5 = 5;
        for(int i = 1; i <= n; i++){
            if (i == cnt3 && i == cnt5) {
                res.add("FizzBuzz");
                cnt3 += 3;
                cnt5 += 5;
            }
            else if (i == cnt3) {
                cnt3 += 3;
                res.add("Fizz");
            }
            else if (i == cnt5) {
                cnt5 += 5;
                res.add("Buzz");
            }
            else{
                res.add(Integer.toString(i));
            }
        }
        return res;
    }
}
