package baobao.suanfa;

/**
 * 算法：最大公共子串
 */
public class MaxPublicChildStr {

    public static String lcsUseDynamicProgram(String str1,String str2){
        // 方法入口惯例校验入参
        if(str1 == null || "".equals(str1) || str2 == null || "".equals(str2)){
            return "-1";
        }

        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int size1 = charArray1.length;
        int size2 = charArray2.length;

        // 先将第一列和第二列设置为0
        int[][] dp = new int[size1+1][size2+1];
        for (int i = 0; i < size1; i++) {
            dp[i][0] = 0;
            dp[0][i] = 0;
        }
        // maxLength 保存数组中出现过的最大子串长度，maxIndexINChar1 标识
        // 最大子串的末尾字符在str1 中的index

        int maxLength = 0;
        int maxIndexInChar1 = 0;
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j <size2 ; j++) {
                //dp[i+1][j+1] 如果charArray1[i] 与charArray2[j] 不同则为0，相同则为dp[i][j]+1
                if(charArray1[i] != charArray2[j]){
                    dp[i+1][j+1] = 0;
                }else {
                    dp[i+1][j+1] =dp[i][j] +1;
                    if(dp[i+1][j+1] >maxLength){
                        maxLength = dp[i+1][j+1];
                        maxIndexInChar1 = i;
                    }
                }
            }
        }
        if(maxLength == 0){
            return "-1";
        }else {
            //根据长度和字符串str 1 中下表截取最大公共子串
            return str1.substring(maxIndexInChar1+1 -maxLength,maxIndexInChar1+1);
        }
    }

    public static void main(String[] args) {
        /**
         * 这种解法采取了动态规划的思想，解决的问题多数有重叠子问题的特点，为了减少重复计算，对每一个子问题
         * 只解一次，将其不同阶段的不同状态保存在一个二维数组中。
         * 这样的话，复杂度就是O(m*n),空间复杂度O(M*N)
         * 通过空间换时间，使得字符串1与字符串2 每个只比较了一次
         */

        String s = lcsUseDynamicProgram("ab123cd", "a123456c");
        System.out.println(s);
    }
}
