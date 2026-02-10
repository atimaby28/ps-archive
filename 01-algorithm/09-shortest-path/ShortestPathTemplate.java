import java.io.*;
import java.util.*;

/**
 * 최단경로 (Shortest Path) - 다익스트라
 *
 * [입력] - 인접 리스트 (가중치 있는 방향 그래프)
 * 5 6           ← 노드수 간선수
 * 1              ← 시작노드
 * 5 1 1          ← from to weight
 * 1 2 2
 * 1 3 3
 * 2 3 4
 * 2 4 5
 * 3 4 6
 *
 * [출력] - 시작노드에서 각 노드까지 최단거리 (INF=도달불가)
 * 0
 * 2
 * 3
 * 7
 * INF
 */
public class ShortestPathTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine().trim());

        // --- 인접 리스트 구성 (가중치 포함) ---
        List<int[]>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new int[]{v, w}); // 방향 그래프
        }

        int[] dist = dijkstra(adj, start, n);

        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(dist[i]);
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 다익스트라 =====
    public static int[] dijkstra(List<int[]>[] adj, int start, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], node = cur[1];
            if (cost > dist[node]) continue;

            for (int[] next : adj[node]) {
                int nextCost = cost + next[1];
                if (nextCost < dist[next[0]]) {
                    dist[next[0]] = nextCost;
                    pq.offer(new int[]{nextCost, next[0]});
                }
            }
        }
        return dist;
    }

    // ===== 플로이드-워셜 =====
    public static void floyd(int[][] dist, int n) {
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }

    // ===== 벨만-포드 =====
    public static int[] bellmanFord(int[][] edges, int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < n - 1; i++)
            for (int[] e : edges)
                if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]])
                    dist[e[1]] = dist[e[0]] + e[2];

        for (int[] e : edges)
            if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]])
                return null; // 음수 사이클

        return dist;
    }
}
