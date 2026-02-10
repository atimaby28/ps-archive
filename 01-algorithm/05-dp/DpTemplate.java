import java.io.*;
import java.util.*;

/**
 * 다이나믹 프로그래밍 (DP) - LIS
 *
 * [입력]
 * 6
 * 10 20 10 30 20 50
 *
 * [출력] - 최장 증가 부분 수열 길이
 * 4
 *
 * [배낭 문제 - 입력]
 * 4 7            ← 물건수 배낭용량
 * 6 13           ← 무게 가치
 * 4 8
 * 3 6
 * 5 12
 *
 * [배낭 문제 - 출력]
 * 14
 */
public class DpTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        sb.append(lis(arr)).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== LIS O(n log n) =====
    public static int lis(int[] arr) {
        List<Integer> dp = new ArrayList<>();
        for (int num : arr) {
            int pos = Collections.binarySearch(dp, num);
            if (pos < 0) pos = -(pos + 1);
            if (pos == dp.size()) dp.add(num);
            else dp.set(pos, num);
        }
        return dp.size();
    }

    // ===== 0/1 배낭 =====
    public static int knapsack(int n, int W, int[] weight, int[] value) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < n; i++) {
            for (int w = W; w >= weight[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
            }
        }
        return dp[W];
    }

    // ===== LCS =====
    public static int lcs(String a, String b) {
        int n = a.length(), m = b.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }
}
