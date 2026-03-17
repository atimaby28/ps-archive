package baekjoon.silver.one;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_9465_스티커 {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[][] array = new int[2][n];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    array[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(solution(n, array)).append("\n");

        }

        bw.write(sb.toString());

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int n, int[][] array) {
        int[][] dp = new int[2][n];

        if (n == 1) return Math.max(array[0][0], array[1][0]);

        dp[0][0] = array[0][0];
        dp[1][0] = array[1][0];
        dp[0][1] = array[1][0] + array[0][1];
        dp[1][1] = array[0][0] + array[1][1];

        for (int i = 2; i < n; i++) {
            dp[0][i] = array[0][i] + Math.max(dp[1][i - 1], dp[1][i - 2]);
            dp[1][i] = array[1][i] + Math.max(dp[0][i - 1], dp[0][i - 2]);
        }

        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }
}
