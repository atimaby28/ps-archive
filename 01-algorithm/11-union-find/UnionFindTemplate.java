import java.io.*;
import java.util.*;

/**
 * 유니온파인드 (Union-Find)
 *
 * [입력]
 * 6 4           ← 노드수 연산수
 * 1 1 2         ← 0:합치기 1:확인 / a b
 * 1 3 4
 * 0 1 3         ← union(1, 3)
 * 1 1 4         ← isConnected(1, 4)?
 *
 * [출력]
 * NO
 * NO
 * YES
 */
public class UnionFindTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) {
                union(a, b);
            } else {
                sb.append(isConnected(a, b) ? "YES" : "NO").append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void init(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;
    }

    public static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (rank[ra] < rank[rb]) parent[ra] = rb;
        else if (rank[ra] > rank[rb]) parent[rb] = ra;
        else { parent[rb] = ra; rank[ra]++; }
        return true;
    }

    public static boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }
}
