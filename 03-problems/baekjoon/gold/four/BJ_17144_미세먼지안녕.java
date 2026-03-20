package baekjoon.gold.four;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_17144_미세먼지안녕 {
    static int R, C, T;
    static int[][] map;
    static int airTop = -1;     // 위쪽 공기청정기 행
    static int airBottom = -1;  // 아래쪽 공기청정기 행

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1) {
                    if (airTop == -1) airTop = i;
                    else airBottom = i;
                }
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        return 0;
    }
}
