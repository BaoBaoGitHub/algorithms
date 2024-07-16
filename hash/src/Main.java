import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public boolean isAnagram(String s, String t) {
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : sCharArray) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : tCharArray) {
            map.put(c, map.getOrDefault(c, 0) - 1);
        }

        Set<Character> keys = map.keySet();
        for (Character key : keys) {
            if (!map.get(key).equals(0)) {
                return false;
            }
        }

        return true;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            if (set1.contains(num)) {
                resSet.add(num);
            }
        }

        int[] res = resSet.stream().mapToInt(x -> x).toArray();
        return res;
    }

    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }

        Set<Integer> set = new HashSet<>();

        int nextNum = n;
        while (true) {
            nextNum = computeNextNum(nextNum);
            if (nextNum == 1) {
                return true;
            }
            if (set.contains(nextNum)) {
                return false;
            } else {
                set.add(nextNum);
            }
        }
    }

    private int computeNextNum(int n) {
        int sum = 0;

        while (n != 0) {
            int mod = n % 10;
            sum += mod * mod;
            n /= 10;
        }

        return sum;
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }

        return null;
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map1 = new HashMap<>(nums1.length);

        int res = 0;

        for (int num1 : nums1) {
            for (int num2 : nums2) {
                map1.put(num1 + num2, map1.getOrDefault(num1 + num2, 0) + 1);
            }
        }

        for (int num3 : nums3) {
            for (int num4 : nums4) {
                res += map1.getOrDefault(-num3 - num4, 0);
            }
        }
        return res;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        char[] magazineCharArray = magazine.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazineCharArray) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if(map.get(c)<0){
                return false;
            }
        }

        return true;
    }
}