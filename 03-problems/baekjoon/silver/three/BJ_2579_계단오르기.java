package baekjoon.silver.three;

import java.io.*;

public class BJ_2579_계단오르기 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int[] stairs = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int result = solution(stairs);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int[] stairs) {
        if (N == 1) return stairs[1];
        if (N == 2) return stairs[1] + stairs[2];

        int[][] dp = new int[N + 1][2];

        dp[1][0] = stairs[1];
        dp[1][1] = stairs[1];
        dp[2][0] = stairs[2];
        dp[2][1] = stairs[1] + stairs[2];

        for (int i = 3; i <= N; i++) {
            dp[i][0] = stairs[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
            dp[i][1] = stairs[i] + dp[i - 1][0];
        }

        return Math.max(dp[N][0], dp[N][1]);
    }
}
