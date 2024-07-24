import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DFSAdjList {
    private static List<List<Integer>> res = new ArrayList<>();
    private static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        // graph 有 n+1 个节点
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 构造图
        for (int i = 0; i < m; i++) {
            int s = in.nextInt();
            int t = in.nextInt();
            graph.get(s).add(t);
        }

        path.addLast(1);
        dfsAdjList(graph, 1, n);

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

    private static void dfsAdjList(List<List<Integer>> graph, int curNode, int targetNode) {
        if (curNode == targetNode) {
            res.add(new ArrayList<>(path));
        }

        // 对于从当前节点能直达的所有节点，继续dfs
        List<Integer> nodes = graph.get(curNode);
        for (Integer node : nodes) {
            path.addLast(node);
            dfsAdjList(graph,node,targetNode);
            path.removeLast();
        }
    }

}
