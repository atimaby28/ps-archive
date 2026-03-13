package baekjoon.gold.five;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_2565_전깃줄 {

    static int N;
    static int[][] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        lines = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int[] dp = new int[N];

        Arrays.sort(lines, Comparator.comparingInt(o -> o[0]));
        Arrays.fill(dp, 1);

        int lis = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (lines[i][1] < lines[j][1])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
            lis = Math.max(lis, dp[i]);
        }

        return N - lis;
    }
}
