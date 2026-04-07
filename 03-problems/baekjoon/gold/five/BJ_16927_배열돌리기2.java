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

        map = new int[N][M];

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
                // 위쪽 행: ← 왼쪽으로
                for (int j = left; j < right; j++) map[top][j] = map[top][j + 1];
                // 오른쪽 열: ↑ 위로
                for (int i = top; i < bottom; i++) map[i][right] = map[i + 1][right];
                // 아래쪽 행: → 오른쪽으로
                for (int j = right; j > left; j--) map[bottom][j] = map[bottom][j - 1];
                // 왼쪽 열: ↓ 아래로
                for (int i = bottom; i > top + 1; i--) map[i][left] = map[i - 1][left];
                map[top + 1][left] = temp;
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
