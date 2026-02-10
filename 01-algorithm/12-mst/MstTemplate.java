import java.io.*;
import java.util.*;

/**
 * 최소 신장 트리 (MST) - 크루스칼
 *
 * [입력] - 간선 리스트
 * 4 5           ← 노드수 간선수
 * 1 2 1         ← from to weight
 * 1 3 3
 * 2 3 2
 * 2 4 4
 * 3 4 5
 *
 * [출력] - MST 총 비용
 * 7
 */
public class MstTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken()); // from
            edges[i][1] = Integer.parseInt(st.nextToken()); // to
            edges[i][2] = Integer.parseInt(st.nextToken()); // weight
        }

        sb.append(kruskal(n, edges)).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 크루스칼 =====
    public static long kruskal(int n, int[][] edges) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        long totalCost = 0;
        int edgeCount = 0;

        for (int[] edge : edges) {
            if (union(edge[0], edge[1])) {
                totalCost += edge[2];
                if (++edgeCount == n - 1) break;
            }
        }
        return totalCost;
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (rank[ra] < rank[rb]) parent[ra] = rb;
        else if (rank[ra] > rank[rb]) parent[rb] = ra;
        else { parent[rb] = ra; rank[ra]++; }
        return true;
    }
}
