import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        // 定义 dp i 为 F（i）的值
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[2] = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = dp[2];
        }

        return dp[2];
    }

    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        // dp[i]代表有 i 个台阶的爬法
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        int[] dp = new int[cost.length + 1];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[dp.length - 1];
    }

    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 第一行
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }
        // 第一列
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 当前格子是障碍物
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                // 上面没有障碍物
                if (obstacleGrid[i - 1][j] == 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                // 左边没有障碍物
                if (obstacleGrid[i][j - 1] == 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public int integerBreak(int n) {
        if (n < 2) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // 将 i 拆分成两个数的乘积
                int chengji1 = j * (i - j);
                // 将 i 拆分成三个或以上数的乘积
                int chengji2 = dp[j] * (i - j);
                dp[i] = Math.max(dp[i], Math.max(chengji1, chengji2));
            }
        }

        return dp[n];
    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        return dp[n];

    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;

        // 从 nums 数字中选择数字，随便选择数量，装容量为 sum 的背包，最大能装多少
        return sum == canPartitionBag(nums, sum) ? true : false;
    }

    private int canPartitionBag(int[] nums, int sum) {
        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[sum];
    }

    public int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }
        int half = sum / 2;

        int res = sum - 2 * canPartitionBag(stones, half);
        // 从 nums 数字中选择数字，随便选择数量，装容量为 sum 的背包，最大能装多少
        return Math.abs(res);
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }

        int len = strs.length;
        int[] a = new int[len + 1]; // a[i+1] 的值为 strs[i]中 0 的个数
        int[] b = new int[len + 1]; // a[i+1] 的值为 strs[i]中 1 的个数
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            int cnt0 = 0;
            int cnt1 = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '0') {
                    a[i + 1]++;
                } else if (s.charAt(j) == '1') {
                    b[i + 1]++;
                }
            }
        }
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        // 对于 1 到 len 个物品
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= m; j++) { // m个 0
                for (int k = 0; k <= n; k++) { // n 个 1
                    if (j < a[i] || k < b[i]) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - a[i]][k - b[i]] + 1);
                    }
                }
            }
        }

        return dp[len][m][n];
    }

    public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return 0;
        }

        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;

        // 先考虑刚好能装满 amout 的情况
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        // 装不满 amount 的情况
        return dp[coins.length][amount];
    }

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums.length + 1][target + 1];
        dp[0][0] = 1;

        for (int j = 0; j <= target; j++) {
            for (int i = 1; i <= nums.length; i++) {
                int num = nums[i - 1];

                if (num > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 当 i 的时候，对应的是 nums[i-1]其实就是两种选择
                    // 1. 选择 nums[i-1]作为最后一个元素
                    // 2. 不选择 nums[i-1]作为最后一个元素
                    dp[i][j] = dp[i - 1][j] + dp[nums.length][j - num];
                }
            }
        }

        return dp[nums.length][target];
    }

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        // 定义 dp[i][j]为用前 [0,1,...,i-1] 个硬币来凑成 amount 需要的最少硬币个数
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 0;
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= coins.length; i++) {
            int coin = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                // coin 的值撑爆背包容量，一定不会选
                if (j < coin) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 没有一种包含硬币 coin 的选法使得能让硬币凑出 j-coin，所以当前硬币也选不了
                    if (dp[i][j - coin] == Integer.MAX_VALUE) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coin] + 1);
                    }
                }
            }
        }

        return dp[coins.length][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length][amount];
    }

    public int numSquares(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 1;
        }

        // 定义 dp[i][j]为用[0,1,2,...,i]的平方这些数字组成 n 最少需要多少个
        int line = 0;
        while (line * line < n) {
            line++;
        }

        int[][] dp = new int[line + 1][n + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int i = 1; i <= line; i++) {
            for (int j = 0; j <= n; j++) {
                int cnt = i * i;
                if (cnt > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i][j - cnt] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[i][j - cnt] + 1, dp[i - 1][j]);
                }
            }
        }

        return dp[line][n];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        Set<String> set = wordDict.stream().collect(Collectors.toSet());

        // 定义 dp[i][j]为用wordDict中的[0,1,...,i]排列能否组成 s[0,j)
        boolean[][] dp = new boolean[set.size() + 1][s.length() + 1];
        dp[0][0] = true;

        for (int j = 0; j <= s.length(); j++) {
            for (int i = 1; i <= wordDict.size(); i++) {
                String word = wordDict.get(i - 1);

                if (word.length() > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = (dp[wordDict.size()][j - word.length()] && set.contains(s.substring(j - word.length(), j))) || dp[i - 1][j];
                }
            }
        }

        return dp[wordDict.size()][s.length()];
    }

    // 打家劫舍
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    // 打家劫舍 2
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // 这个工具类没有想的那么简单。。。,用了会出错。。。
//        int[] nums1 = Arrays.copyOfRange(nums, 0, nums.length-1);
//        int[] nums2 = Arrays.copyOfRange(nums, 1, nums1.length + 1);
        int[] nums1 = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            nums1[i] = nums[i];
        }
        int[] nums2 = new int[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            nums2[i - 1] = nums[i];
        }
        return Math.max(rob1(nums1), rob1(nums2));
    }

    private Map<TreeNode, Integer> map = new HashMap<>();

    public int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return rob3PostOrder(root);
    }

    public int rob3PostOrder(TreeNode root) {
        // 终止条件
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            map.put(root, root.val);
            return root.val;
        }

        // 有保存当前节点的状态
        if (map.containsKey(root)) {
            return map.get(root);
        }

        // 单层递归
        // 偷当前节点
        int touLeft = 0;
        if (root.left != null) {
            touLeft = rob3PostOrder(root.left.left) + rob3PostOrder(root.left.right);
        }
        int touRight = 0;
        if (root.right != null) {
            touRight = rob3PostOrder(root.right.left) + rob3PostOrder(root.right.right);
        }
        int touCur = touLeft + touRight + root.val;

        // 不偷当前节点
        int butouCur = rob3PostOrder(root.left) + rob3PostOrder(root.right);
        int res = Math.max(touCur, butouCur);
        map.put(root, res);

        return res;
    }

    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 定义 dp[i][0]和 dp[i][1] 为第[i]天持有/不持有股票的利润
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 定义 dp[i][0]和 dp[i][1] 为第[i]天持有/不持有股票的利润
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];
    }

    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // 定义 dp[i][0] 和 dp[i][1] 为第[i]天第一次持有/不持有股票的利润
        // 定义 dp[i][2] 和 dp[i][3] 为第[i]天第二次持有/不持有股票的利润
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }

        return dp[prices.length - 1][3];
    }

    // 要是真碰到这个题，建议跳过
    // 脑子都快烧了。。。磨了快一小时才
    public int maxProfit4(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) {
            return 0;
        }

        int[][][] dp = new int[prices.length + 1][k + 1][2];
        for (int i = 1; i <= k; i++) {
            dp[0][i][0] = -prices[0];
        }

        for (int i = 1; i <= prices.length; i++) {
            int price = prices[i - 1];
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] - price);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] + price);
            }
        }

        return dp[prices.length][k][1];
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.rob2(new int[]{2, 3, 2});
    }
}
