public class Array {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int begin = 0;
        int end = nums.length - 1;
        while (begin <= end) {
            int index = (begin + end) / 2;
            if (nums[index] < target) {
                begin = index + 1;
            } else if (nums[index] > target) {
                end = index - 1;
            } else {
                return index;
            }
        }

        return -1;
    }

    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            } else {
                fast++;
            }
        }

        return nums.length - fast + slow;
    }

    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] squares = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;

        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                squares[index] = leftSquare;
                left++;
            } else {
                squares[index] = rightSquare;
                right--;
            }
            index--;
        }

        return squares;
    }

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;

        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                res = res < (right - left) + 1 ? res : (right - left) + 1;
                sum -= nums[left];
                left++;
            }
            right++;
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int[][] generateMatrix(int n) {
        if (n < 1) {
            return new int[0][0];
        }

        int[][] res = new int[n][n];

        int num = 1;
        int tar = n * n;

        int up = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;

        while (num <= tar) {
            // 向右填充
            for (int j = left; j <= right; j++) {
                res[up][j] = num++;
            }
            up++;
            // 向下填充
            for (int i = up; i <= down; i++) {
                res[i][right] = num++;
            }
            right--;
            // 向左填充
            for (int j = right; j >= left; j--) {
                res[down][j] = num++;
            }
            down--;
            // 向上填充
            for (int i = down; i >= up; i--) {
                res[i][left] = num++;
            }
            left++;
        }

        return res;
    }

    public static void main(String[] args) {
        Array array = new Array();
        System.out.println(array.search(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, -3));
    }
}
