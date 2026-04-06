package baekjoon.gold.five;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_16927_배열돌리기2 {
    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st =  new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        int layers = Math.min(N, M) / 2;

        for (int l = 0; l < layers; l++) {
            int top = l, left = l;
            int bottom = N - 1 - l, right = M - 1 - l;
            int perimeter = 2 * (bottom - top + right - left);
            int rot = R % perimeter;

            for (int r = 0; r < rot; r++) {
                int temp = map[top][left];
                for (int i = top; i < bottom; i++) map[i][left] = map[i + 1][left];
                for (int j = left; j < right; j++) map[bottom][j] = map[bottom][j + 1];
                for (int i = bottom; i > top; i--) map[i][right] = map[i - 1][right];
                for (int j = right; j > left + 1; j--) map[top][j] = map[top][j - 1];
                map[top][left + 1] = temp;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j > 0) sb.append(' ');
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
