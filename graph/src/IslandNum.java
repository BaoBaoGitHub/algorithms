import java.util.Scanner;

// 岛屿数量 dfs 版
public class IslandNum {
    private static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] graph = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = in.nextInt();
            }
        }

        int res = 0;
        // 从所有的点开始遍历
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 找到一个 新 岛屿的起点，遍历该岛屿上所有的点
                if (graph[i][j] == 1 && visited[i][j] == false) {
                    res++;
                    dfs(graph, visited, i, j);
                }
            }
        }

        System.out.println(res);
    }

    private static void dfs(int[][] graph, boolean[][] visited, int i, int j) {
        // 递归终止条件
        if (i < 0 || i >= graph.length || j < 0 || j >= graph[0].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        if (graph[i][j] == 0) {
            return;
        }

        // 标记当前节点
        visited[i][j] = true;

        // 从当前节点开始，往四个方向 dfs
        for (int k = 0; k < dir.length; k++) {
            dfs(graph, visited, i + dir[k][0], j + dir[k][1]);
        }
    }
}
