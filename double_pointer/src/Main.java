import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public List<List<Integer>> threeSum(int[] nums) {
        int left = 0;

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        while (left < nums.length - 2) {
            int mid = left + 1;
            int right = nums.length - 1;
            while (mid < right) {
                int sum = nums[left] + nums[mid] + nums[right];
                if (sum < 0) {
                    mid++;
                } else if (sum > 0) {
                    right--;
                } else if (sum == 0) {
                    res.add(Arrays.asList(nums[left], nums[mid], nums[right]));
                    // mid向右移动到第一个不重复的
                    mid++;
                    while (nums[mid] == nums[mid - 1] && mid < right) {
                        mid++;
                    }
                    // right 向左移动到第一个不重复的
                    right--;
                    while (nums[right] == nums[right + 1] && mid < right) {
                        right--;
                    }
                }
            }
            left++;
            while (nums[left] == nums[left - 1] && left < nums.length - 2) {
                left++;
            }
        }

        return res;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int left1 = 0;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        while (left1 < nums.length - 3) {
            if (nums[left1] > target && (nums[left1] > 0) || (target > 0)) {
                break;
            }
            int left2 = left1 + 1;
            while (left2 < nums.length - 2) {
                int mid = left2 + 1;
                int right = nums.length - 1;

                while (mid < right) {
                    long sum = nums[left1] + nums[left2] + nums[mid] + nums[right];

                    if (sum < target) {
                        mid++;
                    } else if (sum > target) {
                        right--;
                    } else if (sum == target) {
                        // 记录结果
                        res.add(Arrays.asList(nums[left1], nums[left2], nums[mid], nums[right]));
                        // 移动 mid 直至无重复
                        mid++;
                        while (nums[mid] == nums[mid - 1] && mid < right) {
                            mid++;
                        }
                        // 移动 right 直至无重复
                        right--;
                        while (nums[right] == nums[right + 1] && mid < right) {
                            right--;
                        }
                    }
                }
                left2++;
                while (nums[left2] == nums[left2 - 1] && left2 < nums.length - 2) {
                    left2++;
                }
            }
            left1++;
            while (nums[left1] == nums[left1 - 1] && left1 < nums.length - 3) {
                left1++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Main main = new Main();
        List<List<Integer>> list = main.fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296);
    }
}
