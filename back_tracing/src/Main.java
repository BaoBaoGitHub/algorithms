import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private List<List<Integer>> intRes = new ArrayList<>();

    private LinkedList<Integer> intPath = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) {
            return intRes;
        }
        combineBackTracing(n, k, 1);

        return intRes;
    }

    private void combineBackTracing(int n, int k, int startIndex) {
        if (intPath.size() == k) {
            intRes.add(new ArrayList<>(intPath));
            return;
        }

        for (int i = startIndex; i <= n - k; i++) {
            intPath.addLast(i);
            combineBackTracing(n, k, i + 1);
            intPath.removeLast();
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k <= 0 || n <= 0) {
            return intRes;
        }
        combinationSum3BackTracing(k, n, 0, 1);

        return intRes;
    }

    private void combinationSum3BackTracing(int k, int n, int curSum, int startNum) {
        if (intPath.size() == k) {
            if (curSum == n) {
                intRes.add(new ArrayList<>(intPath));
            }
            return;
        }

        for (int i = startNum; i <= 9; i++) {
            intPath.addLast(i);
            combinationSum3BackTracing(k, n, curSum + i, i + 1);
            intPath.removeLast();
        }
    }

    private List<String> stringRes = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return stringRes;
        }
        List<char[]> maps = new ArrayList<>();
        maps.add(new char[]{});
        maps.add(new char[]{});
        maps.add(new char[]{'a', 'b', 'c'});
        maps.add(new char[]{'d', 'e', 'f'});
        maps.add(new char[]{'g', 'h', 'i'});
        maps.add(new char[]{'j', 'k', 'l'});
        maps.add(new char[]{'m', 'n', 'o'});
        maps.add(new char[]{'p', 'q', 'r', 's'});
        maps.add(new char[]{'t', 'u', 'v'});
        maps.add(new char[]{'w', 'x', 'y', 'z'});

        char[] charArray = digits.toCharArray();

        letterCombinationsBackTracing(maps, charArray, 0);

        return stringRes;
    }

    private void letterCombinationsBackTracing(List<char[]> maps, char[] digits, int depth) {
        if (depth == digits.length) {
            stringRes.add(sb.toString());
            return;
        }

        char[] curChars = maps.get(digits[depth] - '0');
        for (char c : curChars) {
            sb.append(c);
            letterCombinationsBackTracing(maps, digits, depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumBackTracing(candidates, target, 0);
        return intRes;
    }

    private void combinationSumBackTracing(int[] candidates, int target, int startIndex) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            intRes.add(new ArrayList<>(intPath));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            int curCandidate = candidates[i];
            intPath.addLast(curCandidate);
            combinationSumBackTracing(candidates, target - curCandidate, i);
            intPath.removeLast();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSumBackTracing2(candidates, target, 0);
        return intRes;
    }

    private void combinationSumBackTracing2(int[] candidates, int target, int startIndex) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            intRes.add(new ArrayList<>(intPath));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            int curCandidate = candidates[i];
            // 防止重复结果
            if (i > startIndex && curCandidate == candidates[i - 1]) {
                continue;
            }

            intPath.addLast(curCandidate);
            combinationSumBackTracing(candidates, target - curCandidate, i + 1);
            intPath.removeLast();
        }
    }

    private List<List<String>> stringListList = new ArrayList<>();

    private LinkedList<String> stringList = new LinkedList<>();

    public List<List<String>> partition(String s) {
        partitionBackTracing(s, 0);

        return stringListList;
    }

    private void partitionBackTracing(String s, int startIndex) {
        if (startIndex >= s.length()) {
            stringListList.add(new ArrayList<>(stringList));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            String substring = s.substring(startIndex, i + 1);
            if (!huiwen(substring)) {
                continue;
            }

            stringList.addLast(substring);
            partitionBackTracing(s, i + 1);
            stringList.removeLast();
        }
    }

    private boolean huiwen(String substring) {
        if (substring == null || substring.length() == 0) {
            return false;
        }

        int left = 0;
        int right = substring.length() - 1;
        while (left < right) {
            if (substring.charAt(left) != substring.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 1) {
            return intRes;
        }
        findSubsequencesBackTracing(nums, 0);
        return intRes;
    }

    private void findSubsequencesBackTracing(int[] nums, int startIndex) {
        if (startIndex == nums.length) {
            return;
        }

        // 每一层要去重
        Set<Integer> layerNode = new HashSet<>();

        for (int i = startIndex; i < nums.length; i++) {
            // intPath中只要有数据，要求单调递增
            if (intPath != null && intPath.size() >= 1 && nums[i] < intPath.getLast()) {
                continue;
            }

            //回溯树中同一层不能有重复值
            if (layerNode.contains(nums[i])) {
                continue;
            }

            layerNode.add(nums[i]);
            intPath.addLast(nums[i]);
            if (intPath != null && intPath.size() >= 2) {
                intRes.add(new ArrayList<>(intPath));
            }
            findSubsequencesBackTracing(nums, i + 1);
            intPath.removeLast();
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return intRes;
        }
        boolean[] used = new boolean[nums.length];

        permuteBackTracing(nums, used);

        return intRes;
    }

    private void permuteBackTracing(int[] nums, boolean[] used) {
        if (intPath.size() == nums.length) {
            intRes.add(new ArrayList<>(intPath));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            intPath.addLast(nums[i]);
            permuteBackTracing(nums, used);
            intPath.removeLast();
            used[i] = false;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return intRes;
        }

        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUniqueBackTracing(nums, used);
        return intRes;
    }


    //    todo 这里注意一下去重逻辑
    private void permuteUniqueBackTracing(int[] nums, boolean[] used) {
        if (intPath.size() == nums.length) {
            intRes.add(new ArrayList<>(intPath));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) {
                continue;
            }

            // quchong
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == true) {
                continue;
            }

            intPath.addLast(nums[i]);
            used[i] = true;
            permuteUniqueBackTracing(nums, used);
            used[i] = false;
            intPath.removeLast();
        }
    }

    private char queen = 'Q';
    private char blank = '.';

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return stringListList;
        }
        // 用一个 n 行 n 列的数组表示棋盘
        // 需要一个将数组变为 list<string> 的方法
        char[][] chessboard = new char[n][n];
        for (char[] line : chessboard) {
            Arrays.fill(line, blank);
        }

        solveNQueensBackTracing(n, 0, chessboard);
        return stringListList;
    }

    private void solveNQueensBackTracing(int n, int row, char[][] chessboard) {
        if (row == n) {
            stringListList.add(constructStringList(chessboard));
            return;
        }

        for (int j = 0; j < chessboard[0].length; j++) {
            // 把 chessboard[row][j]标记为皇后
            chessboard[row][j] = queen;
            if (!isValid(chessboard, row, j)) {
                // 标记回空白
                chessboard[row][j] = blank;
                continue;
            }
            solveNQueensBackTracing(n, row + 1, chessboard);
            // 标记回空白
            chessboard[row][j] = blank;
        }

    }

    private boolean isValid(char[][] chessboard, int i, int j) {
        // 检查行
        for (int row = i - 1; row >= 0; row--) {
            if (chessboard[row][j] == queen) {
                return false;
            }
        }
        // 检查列
        for (int column = j - 1; column >= 0; column--) {
            if (chessboard[i][column] == queen) {
                return false;
            }
        }

        // 检查左上角
        for (int column = j - 1, row = i - 1; row >= 0 && column >= 0; row--, column--) {
            if (chessboard[row][column] == queen) {
                return false;
            }
        }

        // 检查右上角
        for (int row = i - 1, column = j + 1; row >= 0 && column < chessboard[0].length; row--, column++) {
            if (chessboard[row][column] == queen) {
                return false;
            }
        }

        return true;
    }

    private List<String> constructStringList(char[][] chessboard) {
        if (chessboard == null || chessboard.length == 0) {
            return null;
        }

        List<String> res = new ArrayList<>();
        for (char[] line : chessboard) {
            StringBuilder sb = new StringBuilder();
            for (char c : line) {
                sb.append(c);
            }
            res.add(sb.toString());
        }

        return res;
    }

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        solveSudokuBackTracing(board, 0, 0);
    }

    private void solveSudokuBackTracing(char[][] board, int i, int j) {
        // 递归终止条件
        if (i >= board.length) {
            return;
        }

        // 如果当前位置不空，直接处理下一个格子
        if (board[i][j] != '.') {
            if (j == board[0].length - 1) {
                solveSudokuBackTracing(board, i + 1, 0);
            } else {
                solveSudokuBackTracing(board, i, j + 1);
            }
            return;
        }

        // 如果当前位置为空，遍历 1 到 9，看看有没有合适的
        for (char k = '1'; k <= '9'; k++) {
            // 放数字，如果冲突，取消数字并试探下一个数字
            board[i][j] = k;

            if (!isValidSudo(board, i, j, k)) {
                board[i][j] = '.';
                continue;
            }

            // 如果不冲突,继续递归下一个格子
            if (j == board[0].length - 1) {
                solveSudokuBackTracing(board, i + 1, 0);
            } else {
                solveSudokuBackTracing(board, i, j + 1);
            }

            // 如果从下一个格子回溯回来，整个数独盘都满了，说明已经找到解了，直接 return 完事
            if (fullBoard(board)) {
                return;
            }

            // 否则说明当前放的 k 找不到解，把当前位置置空，处理下一个节点
            board[i][j] = '.';
        }
    }

    private boolean fullBoard(char[][] board) {
        if (board == null || board.length == 0) {
            return true;
        }

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = board[0].length - 1; j >= 0; j--) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidSudo(char[][] board, int row, int column, char k) {
        // 当前行
        for (int j = 0; j < board[0].length; j++) {
            if (j == column) {
                continue;
            }
            if (board[row][j] == k) {
                return false;
            }
        }
        // 当前列
        for (int i = 0; i < board.length; i++) {
            if (i == row) {
                continue;
            }
            if (board[i][column] == k) {
                return false;
            }
        }
        // 当前九宫格
        int beginRow = row / 3 * 3;
        int beginColumn = column / 3 * 3;
        for (int i = beginRow; i < beginRow + 3; i++) {
            for (int j = beginColumn; j < beginColumn + 3; j++) {
                if (i == row && j == column) {
                    continue;
                }
                if (board[i][j] == k) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solveSudoku(new char[][]{
                {'5','3','.'},
                {'6','.','.'},
                {'.','9','8'}
        });
    }
}
