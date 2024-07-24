import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class IslandMaxAreaBFS {
    private static final int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] graph = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        int res = 0;

        // 从每一个地方开始走，计算每一个岛屿的最大面积
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    int cnt = bfs(graph, visited, i, j);
                    res = Math.max(cnt, res);
                }
            }
        }

        System.out.println(res);
    }

    /**
     * 从 i，j 开始的岛屿的面积
     *
     * @param graph
     * @param visited
     * @param i
     * @param j
     * @return
     */
    private static int bfs(int[][] graph, boolean[][] visited, int i, int j) {
        // 递归终止条件
        if (!valid(graph, visited, i, j)) {
            return 0;
        }

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{i, j});
        visited[i][j] = true;
        int cnt = 1;

        while (!que.isEmpty()) {
            int[] head = que.remove();
            int curI = head[0];
            int curJ = head[1];

            for (int[] step : dir) {
                int nextI = curI + step[0];
                int nextJ = curJ + step[1];
                if (valid(graph, visited, nextI, nextJ)) {
                    cnt++;
                    visited[nextI][nextJ] = true;
                    que.add(new int[]{nextI, nextJ});
                }
            }
        }

        return cnt;
    }

    /**
     * 判断当前坐标是否是未访问过的陆地
     *
     * @param graph
     * @param visited
     * @param i
     * @param j
     * @return
     */
    private static boolean valid(int[][] graph, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= graph.length || j < 0 || j >= graph[0].length) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        if (graph[i][j] == 0) {
            return false;
        }

        return true;
    }
}
