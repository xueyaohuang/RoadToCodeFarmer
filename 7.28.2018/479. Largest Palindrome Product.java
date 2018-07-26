class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        long max = (long)Math.pow(10, n);
        long min = max/ 10;
        long temp = 0;
        max--;
        
        for (long num = max - 1; num >= min; num--){
            temp = makePal(num);
            if (isFactor(temp, min, max)){
                return (int)(temp % 1337);
            }
        }
        return -1;
    }
    
    public long makePal(long num){
        long res = num;
        while(num > 0){
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }
    
    public boolean isFactor(long product, long min, long max){
        long mid = (long) Math.sqrt(product);
        if (mid > max || mid < min) return false;
        long left = mid;
        long right = mid;
        long temp = left * right;
        while (temp != product){
            if (temp < product){
                right++;
                if (right > max) return false;
            }else{
                left--;
                if (left < min) return false;
            }
            temp = left * right;
        }
        return true;
    }
}
