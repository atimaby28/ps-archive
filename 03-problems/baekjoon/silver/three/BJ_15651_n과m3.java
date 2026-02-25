package baekjoon.silver.three;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BJ_15651_n과m3 {
    static int N, M;
    static int[] serial;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new  StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        serial =  IntStream.range(1, N + 1).toArray();

        String result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        repPerm(0, new int[M], sb);

        return sb.toString();
    }

    private static void repPerm(int depth, int[] result, StringBuilder sb) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = 0; i < N; i++) {
            result[depth] = serial[i];
            repPerm(depth + 1, result, sb);
        }
    }
}
