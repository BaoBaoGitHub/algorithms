import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class IslandNumBFS {
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
                    bfs(graph, visited, i, j);
                }
            }
        }

        System.out.println(res);
    }


    private static boolean validZuobiao(int[][] graph, boolean[][] visited, int i, int j) {
        // 超出范围
        if (i < 0 || j < 0 || i >= graph.length || j >= graph[0].length) {
            return false;
        }
        // 不是陆地
        if (graph[i][j] == 0) {
            return false;
        }
        // 已经访问过了
        if (visited[i][j]) {
            return false;
        }
        return true;
    }

    private static void bfs(int[][] graph, boolean[][] visited, int i, int j) {
        if (!validZuobiao(graph, visited, i, j)) {
            return;
        }

        // 用一个 que 表示所有的节点
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{i, j});
        visited[i][j] = true;

        // que 不为空的时候，循环
        // 拿出一个节点，加入队列，标记已访问
        while (!que.isEmpty()) {
            int[] head = que.remove();
            int curI = head[0];
            int curJ = head[1];

            // 遍历当前节点周围的四个节点
            for (int[] ints : dir) {
                int nextI = curI + ints[0];
                int nextJ = curJ + ints[1];
                if (!validZuobiao(graph, visited, nextI, nextJ)) {
                    continue;
                }

                visited[nextI][nextJ] = true;
                que.add(new int[]{nextI, nextJ});
            }
        }
    }
}
