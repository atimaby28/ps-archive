import java.io.*;
import java.util.*;

/**
 * 위상정렬 (Topological Sort)
 *
 * [입력] - 방향 그래프 (선수과목)
 * 6 6           ← 노드수 간선수
 * 1 2           ← 1을 먼저 해야 2 가능
 * 1 3
 * 2 4
 * 3 4
 * 4 5
 * 5 6
 *
 * [출력] - 위상정렬 순서
 * 1 2 3 4 5 6
 */
public class TopologicalSortTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        int[] inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            inDegree[v]++;
        }

        List<Integer> result = topologicalSort(adj, inDegree, n);

        if (result == null) {
            sb.append("사이클 존재");
        } else {
            for (int node : result) sb.append(node).append(' ');
        }
        sb.append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 위상정렬 (BFS, Kahn's Algorithm) =====
    public static List<Integer> topologicalSort(List<List<Integer>> adj, int[] inDegree, int n) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);

            for (int next : adj.get(cur)) {
                if (--inDegree[next] == 0) queue.offer(next);
            }
        }

        if (result.size() != n) return null;
        return result;
    }
}
