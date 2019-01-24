//首先要做的就是确定每一行能放下的单词数，这个不难，就是比较n个单词的长度和加上n - 1个空格的长度跟给定的长度L来比较即可
//找到了一行能放下的单词个数，然后计算出这一行存在的空格的个数，是用给定的长度L减去这一行所有单词的长度和。
//得到了空格的个数之后，就要在每个单词后面插入这些空格，这里有两种情况，比如某一行有两个单词"to" 和 "a"，给定长度L为6
//如果这行不是最后一行，那么应该输出"to   a"，如果是最后一行，则应该输出 "to a  "，所以这里需要分情况讨论，最后一行的处理方法和其他行之间略有不同。
//最后一个难点就是，如果一行有三个单词，这时候中间有两个空，如果空格数不是2的倍数，那么左边的空间里要比右边的空间里多加入一个空格，那么我们只需要用总的空格数除以空间个数
//能除尽最好，说明能平均分配，除不尽的话就多加个空格放在左边的空间里"
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int idx = 0;
        int len = words.length;
        while (idx < len) {
            //count：该行所有单词累计总长度
            int count = words[idx].length();
            //last:该行最后一个词的index
            int lastIdx = idx + 1;
            while (lastIdx < len) {
                //out of bound
                if (count + words[lastIdx].length() + 1 > maxWidth) {
                    break;
                }
                //plus one for the space, if its a perfect fit it will fit
                count += words[lastIdx].length() + 1;
                lastIdx++;
            }
            StringBuilder sb = new StringBuilder();
            //append该行第一个单词
            sb.append(words[idx]);
            //这一行除去第一个已经append的单词，共剩下几个词语：从index到last-1
            int numLeft = lastIdx - 1 - idx;
            // if last line or number of words in the line is 1, left-justified
            // 最后一行：每个单词中间一个空格， 剩余补上空白
            if (lastIdx == len || numLeft == 0) {
                for (int i = idx + 1; i < lastIdx; i++) {
                    sb.append(" ").append(words[i]);
                }
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                // 不是最后一行：fully justified
                // 这一行总space的个数：（长度-累计单词总长度）
                // 每个单词后面space的个数：（长度-累计单词总长度）/单词个数
                int numEvenSpace = (maxWidth - count) / numLeft;
                int numOneMoreSpace = (maxWidth - count) % numLeft;
                for (int i = idx + 1; i < lastIdx; i++) {
                    for (int j = 0; j < numEvenSpace; j++) {
                        sb.append(" ");
                    }
                    if (numOneMoreSpace > 0) {
                        sb.append(" ");
                        numOneMoreSpace--;
                    }
                    sb.append(" ").append(words[i]);
                }
            }
            res.add(sb.toString());
            idx = lastIdx;
        }
        return res;
    }
}
