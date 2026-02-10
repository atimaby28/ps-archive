import java.io.*;
import java.util.*;

/**
 * 그래프 (Graph) - 인접 리스트 vs 인접 행렬
 *
 * [입력]
 * 4 5 1         ← 노드수 간선수 시작노드
 * 1 2           ← 간선 (양방향)
 * 1 3
 * 1 4
 * 2 4
 * 3 4
 *
 * === 인접 리스트 저장 결과 ===
 * 1: [2, 3, 4]
 * 2: [1, 4]
 * 3: [1, 4]
 * 4: [1, 2, 3]
 *
 * === 인접 행렬 저장 결과 ===
 *   1 2 3 4
 * 1 0 1 1 1
 * 2 1 0 0 1
 * 3 1 0 0 1
 * 4 1 1 1 0
 *
 * [출력] - 연결 요소 개수
 * 1
 */
public class GraphTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // --- 방법 1: 인접 리스트 ---
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) adjList.add(new ArrayList<>());

        // --- 방법 2: 인접 행렬 ---
        int[][] adjMatrix = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 인접 리스트
            adjList.get(u).add(v);
            adjList.get(v).add(u);

            // 인접 행렬
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }

        // --- 연결 요소 개수 (인접 리스트 활용) ---
        sb.append(countComponents(adjList, n)).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 연결 요소 개수 =====
    public static int countComponents(List<List<Integer>> adj, int n) {
        visited = new boolean[n + 1];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(adj, i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(List<List<Integer>> adj, int node) {
        visited[node] = true;
        for (int next : adj.get(node)) {
            if (!visited[next]) dfs(adj, next);
        }
    }

    // ===== 이분 그래프 판별 =====
    public static boolean isBipartite(List<List<Integer>> adj, int n) {
        int[] color = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (color[i] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            color[i] = 1;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : adj.get(cur)) {
                    if (color[next] == 0) {
                        color[next] = (color[cur] == 1) ? 2 : 1;
                        queue.offer(next);
                    } else if (color[next] == color[cur]) return false;
                }
            }
        }
        return true;
    }
}
