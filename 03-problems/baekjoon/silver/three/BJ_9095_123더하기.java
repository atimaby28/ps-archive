package baekjoon.silver.three;

import java.io.*;

public class BJ_9095_123더하기 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            sb.append(solution(Integer.parseInt(br.readLine()))).append("\n");
        }

        bw.write(sb.toString());
        
        bw.flush();
            
        bw.close();
        br.close();
    }

    private static int solution(int n) {
        int[] dp = new int[Math.max(n + 1, 4)];

        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        if (n <= 3) return dp[n];

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}
