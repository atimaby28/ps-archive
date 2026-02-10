import java.io.*;
import java.util.*;

/**
 * 백트래킹 (Backtracking) - 순열/조합
 *
 * [입력] - N과 M (순열)
 * 4 2           ← n m
 *
 * [출력] - 4개 중 2개 순열
 * 1 2
 * 1 3
 * 1 4
 * 2 1
 * 2 3
 * 2 4
 * 3 1
 * 3 2
 * 3 4
 * 4 1
 * 4 2
 * 4 3
 */
public class BacktrackingTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int[] selected;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        selected = new int[m];
        visited = new boolean[n + 1];

        permutation(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 순열 (nPm) =====
    public static void permutation(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                if (i > 0) sb.append(' ');
                sb.append(selected[i]);
            }
            sb.append('\n');
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = i;
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }

    // ===== 조합 (nCm) =====
    public static void combination(int depth, int start) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                if (i > 0) sb.append(' ');
                sb.append(selected[i]);
            }
            sb.append('\n');
            return;
        }
        for (int i = start; i <= n; i++) {
            selected[depth] = i;
            combination(depth + 1, i + 1);
        }
    }
}
