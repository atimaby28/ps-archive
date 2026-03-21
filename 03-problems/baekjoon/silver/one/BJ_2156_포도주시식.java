package baekjoon.silver.one;

import java.io.*;

public class BJ_2156_포도주시식 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int[] wine = new int[N];

        for (int i = 0; i < N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        int result = solution(wine);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int[] wine) {
        int[][] dp = new int[N][3];

        dp[0][1] = wine[0];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = dp[i - 1][0] + wine[i];
            dp[i][2] = dp[i - 1][1] + wine[i];
        }

        return Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2]));
    }

}
