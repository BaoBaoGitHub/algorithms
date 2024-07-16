import javax.jws.Oneway;
import java.util.*;

public class Main {
    public boolean isValid(String s) {
        char[] right = {')', ']', '}'};
        char[] left = {};
        char[] sCharArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : sCharArray) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (c == ']' && stack.peek() == '[') {
                    stack.pop();
                } else if (c == '}' && stack.peek() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty() ? true : false;
    }

    public String removeDuplicates(String s) {
        Deque<Character> queue = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!queue.isEmpty() && queue.peekLast().equals(c)) {
                queue.pollLast();
            } else {
                queue.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.pollFirst());
        }
        return sb.toString();
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        // 对于每一个token，如果是运算符，则从栈中拿出两个数字运算
        for (String token : tokens) {
            int num1 = 0;
            int num2 = 0;
            if ("+".equals(token)) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 + num2);
            } else if ("-".equals(token)) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 - num2);
            } else if ("*".equals(token)) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 * num2);
            } else if ("/".equals(token)) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 / num2);
            } else {
                // 如果是数字入栈即可
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.pop();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MyQuque myQuque = new MyQuque();
        if (nums.length == 1) {
            return nums;
        }
        int left = 0;
        int right = 0;
        // 前 k-1 个数字构造 MyQue
        for (; right < k; right++) {
            myQuque.push(nums[right]);
        }
        int[] res = new int[nums.length - k + 1];
        res[left] = myQuque.front();
        // 窗口滑动到结尾
        for (; right < nums.length; right++) {
            myQuque.pop(nums[left++]);
            myQuque.push(nums[right]);
            res[left] = myQuque.front();
        }
        return res;
    }

    static class MyQuque {
        private Deque<Integer> deque = new ArrayDeque<>();

        public void push(Integer x) {
            // 当前数字大于 deque 的尾的时候，一直pop
            while (!deque.isEmpty() && deque.peekLast() < x) {
                deque.pollLast();
            }
            deque.offerLast(x);
        }

        public void pop(Integer x) {
            if (!deque.isEmpty() && deque.peekFirst().equals(x)) {
                deque.pollFirst();
            }
        }

        public Integer front() {
            return deque.peekFirst();
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (pair1, pair2) -> {
            return pair1[1] - pair2[1];
        });

        int cnt = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] curPair = new int[]{entry.getKey(), entry.getValue()};
            if (cnt < k) {
                queue.offer(curPair);
                cnt++;
            } else {
                if (curPair[1] > queue.peek()[1]) {
                    queue.poll();
                    queue.offer(curPair);
                }
            }
        }

        int[] res = queue.stream().mapToInt((pair) -> {
            return pair[0];
        }).toArray();

        return res;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.maxSlidingWindow(new int[]{1}, 1);
    }
}
