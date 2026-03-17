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

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                array[0][i] = Integer.parseInt(st.nextToken());
                array[1][i] = Integer.parseInt(st.nextToken());
            }

            sb.append(solution(n, array));

        }

        bw.write(sb.toString());

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int n, int[][] array) {
        return 0;
    }
}
