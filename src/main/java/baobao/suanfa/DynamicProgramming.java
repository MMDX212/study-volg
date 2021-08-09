package baobao.suanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划
 *
 * 核心思想：
 *      在于拆分子问题，记住过往，减少重复计算
 *
 *
 * A ："1+1+1+1+1+1+1+1 =？"
 * A ："上面等式的值是多少"
 * B ：计算 "8"
 * A :  在上面等式的左边写上 "1+" 呢？
 * A : "此时等式的值为多少"
 * B :  很快得出答案 "9"
 * A : "你怎么这么快就知道答案了"
 * A : "只要在8的基础上加1就行了"
 * A : "所以你不用重新计算，因为你记住了第一个等式的值为8!动态规划算法也可以说是 '记住求过的解来节省时间'"
 */


public class DynamicProgramming {


    /**
     * 一个例子带你走进动态规划 -- 青蛙跳阶问题
     *
     * leetcode原题：一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 10 级的台阶总共有多少种跳法。
     *
     *
     * 要想跳到第10级台阶，要么是先跳到第9级，然后再跳1级台阶上去;要么是先跳到第8级，然后一次迈2级台阶上去。
     * 同理，要想跳到第9级台阶，要么是先跳到第8级，然后再跳1级台阶上去;要么是先跳到第7级，然后一次迈2级台阶上去。
     * 要想跳到第8级台阶，要么是先跳到第7级，然后再跳1级台阶上去;要么是先跳到第6级，然后一次迈2级台阶上去。
     *
     * 假设跳到第n级台阶的跳数我们定义为f(n)，很显然就可以得出以下公式：
     *
     * f（10） = f（9）+f(8)
     * f (9)  = f(8) + f(7)
     * f (8)  = f(7) + f(6)
     * ...
     * f(3) = f(2) + f(1)
     *
     * 即通用公式为: f(n) = f(n-1) + f(n-2)
     *
     * 那f(2) 或者 f(1) 等于多少呢？
     *
     * 当只有2级台阶时，有两种跳法，第一种是直接跳两级，第二种是先跳一级，然后再跳一级。即f(2) = 2;
     * 当只有1级台阶时，只有一种跳法，即f（1）= 1；
     * 因此可以用递归去解决这个问题：、
     *
     * 递归时间复杂度 = 解决一个子问题时间*子问题个数
     *
     * 一个子问题时间 =  f（n-1）+f（n-2），也就是一个加法的操作，所以复杂度是 O(1)；
     *
     * 问题个数 = 递归树节点的总数，递归树的总节点 = 2^n-1，所以是复杂度O(2^n)。
     *
     *
     * 因此，青蛙跳阶，递归解法的时间复杂度 = O(1) * O(2^n) =  O(2^n)，就是指数级别的，爆炸增长的，如果n比较大的话，超时很正常的了。
     *
     * 回过头来，你仔细观察这颗递归树，你会发现存在大量重复计算，比如f（8）被计算了两次，f（7）被重复计算了3次...所以这个递归算法低效的原因，就是存在大量的重复计算！
     *
     * 既然存在大量重复计算，那么我们可以先把计算好的答案存下来，即造一个备忘录，等到下次需要的话，先去备忘录查一下，如果有，就直接取就好了，备忘录没有才开始计算，那就可以省去重新重复计算的耗时啦！这就是带备忘录的解法。
     *
     *
     * 带备忘录的递归解法（自顶向下）
     * 一般使用一个数组或者一个哈希map充当这个备忘录。
     *
     * 第一步，f（10）= f(9) + f(8)，f(9) 和f（8）都需要计算出来，然后再加到备忘录中，如下：
     * 第二步，  f(9) = f（8）+ f（7），f（8）= f（7）+ f(6), 因为 f(8) 已经在备忘录中啦，所以可以省掉，f(7),f（6）都需要计算出来，加到备忘录中~
     * 第三步， f(8) = f（7）+ f(6),发现f(8)，f(7),f（6）全部都在备忘录上了，所以都可以剪掉。
     * 所以呢，用了备忘录递归算法，递归树变成光秃秃的树干咯，如下：
     * 带备忘录的递归算法，子问题个数=树节点数=n，解决一个子问题还是O(1),所以带备忘录的递归算法的时间复杂度是O(n)。
     * 接下来呢，我们用带备忘录的递归算法去撸代码，解决这个青蛙跳阶问题的超时问题咯~，代码如下：
     *
     */

    // 使用哈希Map,充当备忘录的作用
    Map<Integer,Integer> tempMap = new HashMap<>();
    public int numWays(int n){
        // n =0 也算一种
        if(n == 0){
            return  1;
        }

        if(n<=2){
            return n;
        }

        //先判断有没计算过，即看看备忘录有没有
        if(tempMap.containsKey(n)){
            //备忘录有，即计算过，直接返回
            return tempMap.get(n);
        }else {
            //备忘录没有，既没有计算过，执行递归计算，并且把结果保存到备忘录map中,对1000000007 取余（这个是leetcode题目规定的）
            tempMap.put(n,(numWays(n-1) + numWays(n-2)) % 1000000007);
            return tempMap.get(n);
        }
    }


    //给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    //输入：nums = [10,9,2,5,3,7,101,18]
    //输出：4
    //解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

    //输入：nums = [0,1,0,3,2,3]
    //输出：4

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        //初始化就是边界情况
        dp[0] = 1;
        int maxans = 1;
        //自底向上遍历
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            //从下标0到i遍历
            for (int j = 0; j < i; j++) {
                //找到前面比nums[i]小的数nums[j],即有dp[i]= dp[j]+1
                if (nums[j] < nums[i]) {
                    //因为会有多个小于nums[i]的数，也就是会存在多种组合了嘛，我们就取最大放到dp[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //求出dp[i]后，dp最大那个就是nums的最长递增子序列啦
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
