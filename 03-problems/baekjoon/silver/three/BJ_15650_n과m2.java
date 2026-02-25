package baekjoon.silver.three;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BJ_15650_n과m2 {
    static int N, M;
    static int[] serial;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new  StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        serial =  IntStream.range(1, N + 1).toArray();
        visited = new boolean[N];

        String result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        combination(0, 0, new int[M], sb);

        return sb.toString();
    }

    private static void combination(int depth, int index, int[] result, StringBuilder sb) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i < N; i++) {
            result[depth] = serial[i];
            combination(depth + 1, i + 1, result, sb);

        }
    }
}
