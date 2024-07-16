import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> que = new LinkedList();
        que.offer(root);
        Node pre = null;
        while (!que.isEmpty()) {
            pre = null;
            int size = que.size();

            for (int i = 0; i < size; i++) {
                Node curNode = que.poll();
                if (pre != null) {
                    pre.next = curNode;
                }
                pre = curNode;

                if (curNode.left != null) {
                    que.offer(curNode.left);
                }
                if (curNode.right != null) {
                    que.offer(curNode.right);
                }
            }
        }
        return root;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }


    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    private boolean compareList(List<Integer> leftMidRight, List<Integer> rightMidRight) {
        if (leftMidRight.size() != rightMidRight.size()) {
            return false;
        }

        for (int i = 0; i < leftMidRight.size(); i++) {
            Integer i1 = leftMidRight.get(i);
            Integer i2 = rightMidRight.get(i);
            if (!i1.equals(i2)) {
                return false;
            }
        }
        return true;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 判断 root 是否满二叉树，若是直接返回节点数
        TreeNode curNode = root;
        int left = 0;
        while (curNode != null) {
            left++;
            curNode = curNode.left;
        }

        curNode = root;
        int right = 0;
        while (curNode != null) {
            right++;
            curNode = curNode.right;
        }

        if (left == right) {
//            int res = 1;
//            for (int i = 0; i < left; i++) {
//                res *= 2;
//            }
//            res--;
            return (int) Math.pow(2, left) - 1;
        }

        // 若不是，递归计算左右子树
        // 返回左右子树节点数+1
        return countNodes(root.left) + countNodes(root.right) + 1;

    }

    public boolean isBalanced(TreeNode root) {
        // 左子树是平衡二叉树
        // 右子树也是平衡二叉树
        // 并且左右子树高度差小于等于 1
        return height(root) == -1 ? false : true;
    }

    /**
     * 如果 root 是平衡二叉树，计算 root 的高度；否则返回 -1
     *
     * @param root
     * @return
     */
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        int diff = Math.abs(leftHeight - rightHeight);
        return diff <= 1 ? Math.max(leftHeight, rightHeight) + 1 : -1;
    }


    public List<String> binaryTreePaths(TreeNode root) {
        List<String> resPath = new ArrayList<>();
        LinkedList<Integer> curPath = new LinkedList<>();

        traval(root, resPath, curPath);

        return resPath;
    }

    private void traval(TreeNode root, List<String> resPath, LinkedList<Integer> curPath) {
        if (root == null) {
            return;
        }

        // 叶子节点，添加一个结果
        curPath.addLast(root.val);
        if (root.left == null && root.right == null) {
            resPath.add(curPath.stream().map(String::valueOf).collect(Collectors.joining("->")));
            return;
        }

        // 处理左右孩子节点
        if (root.left != null) {
            traval(root.left, resPath, curPath);
            curPath.removeLast();
        }
        if (root.right != null) {
            traval(root.right, resPath, curPath);
            curPath.removeLast();
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return mysumOfLeftLeaves(root, false);
    }

    private int mysumOfLeftLeaves(TreeNode root, boolean left) {
        if (root == null) {
            return 0;
        }

        // 左叶子节点
        int curVal = left == true ? root.val : 0;
        if (root.left == null && root.right == null) {
            return curVal;
        }

        return mysumOfLeftLeaves(root.left, true) + mysumOfLeftLeaves(root.right, false);
    }

    public int findBottomLeftValue(TreeNode root) {
        return layerTravel(root);
    }

    private int layerTravel(TreeNode root) {
        int res = 0;

        LinkedList<TreeNode> layerNode = new LinkedList<>();
        layerNode.add(root);
        while (!layerNode.isEmpty()) {
            // 每次循环开始，看到的都是当前层的第一个节点
            int size = layerNode.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = layerNode.pollFirst();
                if (i == 0) {
                    res = curNode.val;
                }

                if (curNode.left != null) {
                    layerNode.offerLast(curNode.left);
                }
                if (curNode.right != null) {
                    layerNode.offerLast(curNode.right);
                }
            }
        }

        return res;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                return true;
            } else {
                return false;
            }
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder, int beginIn, int endIn, int beginPost, int endPost) {
        // 递归终止条件
        if (beginIn > endIn || beginPost > endPost) {
            return null;
        }
        if (beginIn == endIn && beginPost == endPost) {
            return new TreeNode(inorder[beginIn], null, null);
        }

        // 下一层递归
        int splitNum = postorder[endPost];
        int index = beginIn;
        while (index <= endIn) {
            if (inorder[index] == splitNum) {
                break;
            }
            index++;
        }
        int diff = index - beginIn;
        TreeNode node = new TreeNode(splitNum, null, null);
        node.left = buildTree(inorder, postorder, beginIn, index - 1, beginPost, beginPost + diff - 1);
        node.right = buildTree(inorder, postorder, index + 1, endIn, beginPost + diff, endPost - 1);
        return node;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }

        int index = findMax(nums, begin, end);
        TreeNode node = new TreeNode(nums[index], null, null);
        node.left = constructMaximumBinaryTree(nums, begin, index - 1);
        node.right = constructMaximumBinaryTree(nums, index + 1, end);
        return node;
    }

    private int findMax(int[] nums, int begin, int end) {
        int max = nums[begin];
        int index = begin;

        for (int i = begin; i <= end; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 递归终止条件
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode root = new TreeNode();
        root.val = root1.val + root2.val;
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }
//
//    private void mergeTrees( TreeNode root1, TreeNode root2) {
//        if (root1 == null && root2 == null) {
//            root = null;
//        } else if (root1 != null && root2 == null) {
//            root.val = root1.val;
//        } else if (root1 == null && root2 != null) {
//            root.val = root2.val;
//        } else {
//            root.val = root1.val + root2.val;
//        }
//
//        if (root1 != null && root2 != null) {
//            mergeTrees()
//        }
//    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }

        TreeNode leftSearch = searchBST(root.left, val);
        if (leftSearch != null) {
            return leftSearch;
        }
        TreeNode rightSearch = searchBST(root.right, val);
        if (rightSearch != null) {
            return rightSearch;
        }

        return null;
    }

    public boolean isValidBST(TreeNode root) {
        ArrayList<Integer> nodes = new ArrayList<>();
        leftMidRight(root, nodes);
        if (nodes.size() == 0 || nodes.size() == 1) {
            return true;
        }
        int before = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            Integer cur = nodes.get(i);
            if (cur <= before) {
                return false;
            }
            before = cur;
        }
        return true;
    }

    private void leftMidRight(TreeNode root, ArrayList<Integer> nodes) {
        if (root == null) {
            return;
        }

        leftMidRight(root.left, nodes);
        nodes.add(root.val);
        leftMidRight(root.right, nodes);
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        ArrayList<Integer> integers = new ArrayList<>();
        leftMidRight(root, integers);

        return minDiff(integers);

//        int leftDiff = getMinimumDifference(root.left);
//        int leftCurDiff = root.left != null ? Math.abs(root.val - root.left.val) : Integer.MAX_VALUE;
//        int rightDiff = getMinimumDifference(root.right);
//        int rightCurDiff = root.right != null ? Math.abs(root.val - root.right.val) : Integer.MAX_VALUE;

    }

    private int minDiff(ArrayList<Integer> integers) {
        int res = Integer.MAX_VALUE;

        int pre = integers.get(0);
        for (int i = 1; i < integers.size(); i++) {
            int cur = integers.get(i);
            if (Math.abs(cur - pre) < res) {
                res = Math.abs(cur - pre);
            }
            pre = cur;
        }

        return res;
    }

    private TreeNode pre = null;
    private int maxCnt = 0;
    private int cnt = 0;
    private List<Integer> res = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        pre = null;
        if (root == null) {
            return new int[]{};
        }

        realFindMode(root);
        int[] array = res.stream().mapToInt((val) -> {
            return val;
        }).toArray();
        return array;
    }

    private void realFindMode(TreeNode root) {
        if (root == null) {
            return;
        }

        realFindMode(root.left);

        if (pre == null || pre.val != root.val) {
            cnt = 1;
        } else {
            cnt++;
        }

        if (cnt == maxCnt) {
            res.add(root.val);
        } else if (cnt > maxCnt) {
            maxCnt = cnt;
            res = new ArrayList<>();
            res.add(root.val);
        }
        pre = root;

        realFindMode(root.right);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode leftRes = lowestCommonAncestor(root.left, p, q);
        TreeNode rightRes = lowestCommonAncestor(root.right, p, q);
        if (leftRes != null && rightRes != null) {
            return root;
        }
        if (leftRes != null) {
            return leftRes;
        }
        if (rightRes != null) {
            return rightRes;
        }
        return null;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    private TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val == key) {
            // 当前叶子节点直接删除
            if (root.left == null && root.right == null) {
                root = null;
            }
            // 当前节点是单边
            else if (root.left != null && root.right == null) {
                root = root.left;
            } else if (root.right != null && root.left == null) {
                root = root.right;
            }
            // 不是单边,再处理
            else {
                // 拿到左孩子
                TreeNode left = root.left;
                // 拿到右孩子，向左遍历到叶子节点
                TreeNode right = root.right;
                while (right.left != null) {
                    right = right.left;
                }
                // 将左孩子挂在叶子节点的左下方
                right.left = left;

                // root 被更新为原 root 的右孩子
                root = root.right;
            }
        }

        return root;
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 当前节点比 low 小
        // 当前节点在[low,high]
        // 当前节点比 high 大
        if (root == null) {
            return null;
        }

        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (nums == null || left < 0 || right < 0 || left >= nums.length || right >= nums.length || left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, left, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, right);

        return node;
    }

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        // 首先构造一棵形状相同的树
        TreeNode tarRoot = constructSameShapeTree(root);
        // 然后同时右根左遍历两棵树，记录 sum，跟新目标树的val
        rightMidLeft(root, tarRoot);

        return tarRoot;
    }

    private void rightMidLeft(TreeNode srcRoot, TreeNode tarRoot) {
        if (srcRoot == null || tarRoot == null) {
            return;
        }

        rightMidLeft(srcRoot.right, tarRoot.right);
        sum += srcRoot.val;
        tarRoot.val = sum;
        rightMidLeft(srcRoot.left, tarRoot.left);
    }

    private TreeNode constructSameShapeTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode node = new TreeNode();
        node.left = constructSameShapeTree(root.left);
        node.right = constructSameShapeTree(root.right);

        return node;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(543);
        root.left = new TreeNode(384);
        root.left.right = new TreeNode(445);
        root.right = new TreeNode(652);
        root.right.right = new TreeNode(699);

        Main main = new Main();
        System.out.println(main.getMinimumDifference(root));
    }
}
