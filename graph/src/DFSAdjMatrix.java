import java.util.*;

public class DFSAdjMatrix {
    private static List<List<Integer>> res = new ArrayList<>();
    private static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] graph = new int[n + 1][n + 1];

        // 初始化邻接矩阵
        for (int i = 0; i < m; i++) {
            int s = in.nextInt();
            int t = in.nextInt();
            graph[s][t] = 1;
        }

        path.addLast(1);
        dfsAdjMatrix(graph, 1, n);

        if (res.size() == 0) {
            System.out.println(-1);
        }

        for (List<Integer> integerList : res) {
            for (int i = 0; i < integerList.size() - 1; i++) {
                System.out.print(integerList.get(i));
                System.out.print(" ");
            }
            System.out.println(integerList.get(integerList.size() - 1));
        }
    }

    /**
     * @param graph      邻接矩阵
     * @param curNode  当前的节点
     * @param targetNode 终止结点
     */
    private static void dfsAdjMatrix(int[][] graph, int curNode, int targetNode) {
        if (curNode == targetNode) {
            res.add(new ArrayList<>(path));
        }

        // 对于从当前节点能直达的所有节点
        for (int i = 1; i <= targetNode; i++) {
            if (graph[curNode][i] == 1) {
                path.addLast(i);
                dfsAdjMatrix(graph, i, targetNode);
                path.removeLast();
            }
        }
    }
}
