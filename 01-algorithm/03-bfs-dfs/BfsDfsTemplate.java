import java.io.*;
import java.util.*;

/**
 * BFS / DFS
 *
 * [입력] - 인접 리스트 방식
 * 4 5 1       ← 노드수 간선수 시작노드
 * 1 2         ← 간선 (양방향)
 * 1 3
 * 1 4
 * 2 4
 * 3 4
 *
 * [출력]
 * BFS: 1 2 3 4
 * DFS: 1 2 4 3
 *
 * [2D 격자 BFS 입력] - 최단거리
 * 5 5
 * 1 0 1 1 1
 * 1 0 1 0 1
 * 1 0 1 0 1
 * 1 1 1 0 1
 * 0 0 0 0 1
 *
 * [2D 격자 BFS 출력] - (0,0)에서 (4,4)까지 최단거리
 * 8
 */
public class BfsDfsTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static List<List<Integer>> adj;
    static boolean[] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 수
        int m = Integer.parseInt(st.nextToken()); // 간선 수
        int start = Integer.parseInt(st.nextToken()); // 시작 노드

        // --- 인접 리스트 구성 ---
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v); // 양방향
            adj.get(v).add(u);
        }

        // 정렬 (작은 번호부터 방문하기 위해)
        for (int i = 1; i <= n; i++) Collections.sort(adj.get(i));

        // --- BFS ---
        sb.append("BFS: ");
        bfs(start, n);
        sb.append('\n');

        // --- DFS ---
        sb.append("DFS: ");
        visited = new boolean[n + 1];
        dfs(start);
        sb.append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== BFS (인접 리스트) =====
    public static void bfs(int start, int n) {
        boolean[] vis = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        vis[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(' ');

            for (int next : adj.get(cur)) {
                if (!vis[next]) {
                    vis[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

    // ===== DFS (인접 리스트, 재귀) =====
    public static void dfs(int node) {
        visited[node] = true;
        sb.append(node).append(' ');

        for (int next : adj.get(node)) {
            if (!visited[next]) dfs(next);
        }
    }

    // ===== BFS 2D 격자 최단거리 =====
    public static int bfsGrid(int[][] grid, int sx, int sy, int ex, int ey) {
        int n = grid.length, m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == ex && cur[1] == ey) return dist[ex][ey];

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (dist[nx][ny] != -1 || grid[nx][ny] == 0) continue;

                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                queue.offer(new int[]{nx, ny});
            }
        }
        return -1;
    }
}
