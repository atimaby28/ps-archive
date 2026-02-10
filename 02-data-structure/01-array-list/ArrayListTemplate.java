import java.io.*;
import java.util.*;

/**
 * 배열 / 리스트 - 누적합 (Prefix Sum)
 *
 * [입력] - 1D 누적합, 구간합 쿼리
 * 5 3           ← 배열크기 쿼리수
 * 10 20 30 40 50
 * 1 3           ← 구간 [1, 3] 합 (0-indexed)
 * 2 4
 * 0 4
 *
 * [출력]
 * 90
 * 120
 * 150
 *
 * [2D 누적합 - 입력]
 * 3 3
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * → prefix[2][2] - prefix[0][2] - prefix[2][0] + prefix[0][0]
 *   = (0,0)~(1,1) 구간합 = 1+2+4+5 = 12
 */
public class ArrayListTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        // --- 누적합 구성 ---
        long[] prefix = prefixSum(arr);

        // --- 구간합 쿼리 ---
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            // 구간합 [l, r] = prefix[r+1] - prefix[l]
            sb.append(prefix[r + 1] - prefix[l]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 1D 누적합 =====
    public static long[] prefixSum(int[] arr) {
        int n = arr.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];
        return prefix;
    }

    // ===== 2D 누적합 =====
    public static long[][] prefixSum2D(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        long[][] prefix = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                prefix[i][j] = grid[i - 1][j - 1]
                    + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
        return prefix;
        // 구간합 (r1,c1)~(r2,c2) = prefix[r2+1][c2+1] - prefix[r1][c2+1] - prefix[r2+1][c1] + prefix[r1][c1]
    }

    // ===== 배열 회전 =====
    public static void rotateRight(int[] arr, int k) {
        int n = arr.length;
        k %= n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }

    private static void reverse(int[] arr, int l, int r) {
        while (l < r) { int t = arr[l]; arr[l++] = arr[r]; arr[r--] = t; }
    }
}
