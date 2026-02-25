package baekjoon.silver.three;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BJ_15649_n과m1 {

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

        permutation(0, new int[M], sb);

        return sb.toString();
    }

    private static void permutation(int depth, int[] result, StringBuilder sb) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = serial[i];
                permutation(depth + 1, result, sb);
                visited[i] = false;
            }
        }
    }
}
