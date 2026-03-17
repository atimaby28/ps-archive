package baekjoon.silver.one;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_11660_구간합구하기5 {

    static int N, M;
    static int[][] array;
    static int[][] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int sRow = Integer.parseInt(st.nextToken());
            int sCol = Integer.parseInt(st.nextToken());
            int eRow = Integer.parseInt(st.nextToken());
            int eCol = Integer.parseInt(st.nextToken());

            sb.append(prefixSum[eRow][eCol] - prefixSum[sRow - 1][eCol] - prefixSum[eRow][sCol - 1] + prefixSum[sRow - 1][sCol - 1]).append("\n");

        }

        bw.write(sb.toString());

        bw.flush();

        bw.close();
        br.close();
    }

    private static void solution() {
        prefixSum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + array[i - 1][j - 1];
            }
        }
    }
}
