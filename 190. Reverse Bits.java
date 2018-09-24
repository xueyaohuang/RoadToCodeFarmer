public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        // 注意不能写成 while (n != 0)，这样的话会在最前面补0
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }
}

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res=n;
        res=res<<16|res>>>16;
        res=(res&0x00ff00ff)<<8|(res&0xff00ff00)>>>8;
        res=(res&0x0f0f0f0f)<<4|(res&0xf0f0f0f0)>>>4;
         res=(res&0x33333333)<<2|(res&0xcccccccc)>>>2;
         res=(res&0x55555555)<<1|(res&0xaaaaaaaa)>>>1;
        return res;
    }
}

int reverseBits(int n) {
  return Integer.reverse(n);
}

//How to optimize if this function is called multiple times? 
//We can divide an int into 4 bytes, and reverse each byte then combine into an int. 
//For each byte, we can use cache to improve performance.
public class Solution {
    // you need treat n as an unsigned value
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 4; i++){
            int tmp = n & 0xFF;
            if(map.containsKey(tmp)){
                res = (res << 8) + map.get(tmp); 
            } else{
                res = (res << 8) + reverse8Bits(tmp);
            }
            n >>= 8;
        }
        return res;
    }
    
    private int reverse8Bits(int n){
        int res = 0;
        int tmp = n;
        for(int i = 0; i < 8; i++){
            res = (res << 1) + (tmp & 1);
            tmp >>= 1;
        }
        map.put(n, res);
        return res;
    }
}
