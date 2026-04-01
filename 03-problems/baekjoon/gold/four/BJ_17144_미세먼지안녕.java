package baekjoon.gold.four;

import java.io.*;
import java.util.*;

public class BJ_17144_미세먼지안녕 {
    static int R, C, T;
    static int[][] map;
    static int airTop = -1;     // 위쪽 공기청정기 행
    static int airBottom = -1;  // 아래쪽 공기청정기 행

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

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
        while (T-- > 0) {
            Queue<Dust> dustQueue = new ArrayDeque<>();

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] == 0 || map[r][c] == -1) continue;
                    dustQueue.offer(new Dust(r, c, map[r][c]));
                }
            }

            // spread
            spread(dustQueue);

            // conditioning
            conditioning();
        }

        return counting();
    }

    private static void conditioning() {

        // 1. 왼쪽 열: 아래로 당김 (청정기 입구 쪽부터)
        for (int r = airTop - 1; r > 0; r--) map[r][0] = map[r - 1][0];
        // 2. 윗행: 왼쪽으로 당김
        for (int c = 0; c < C - 1; c++) map[0][c] = map[0][c + 1];
        // 3. 오른쪽 열: 위로 당김
        for (int r = 0; r < airTop; r++) map[r][C-1] = map[r + 1][C - 1];
        // 4. 청정기 행: 오른쪽으로 당김
        for (int c = C - 1; c > 1; c--) map[airTop][c] = map[airTop][c - 1];
        // 5. 청정기에서 나오는 바람 = 0
        map[airTop][1] = 0;

        // 1. 왼쪽 열: 위로 당김 (청정기 입구 쪽부터)
        for (int r = airBottom + 1; r < R - 1; r++) map[r][0] = map[r+1][0];
        // 2. 아랫행: 왼쪽으로 당김
        for (int c = 0; c < C - 1; c++) map[R - 1][c] = map[R - 1][c + 1];
        // 3. 오른쪽 열: 아래로 당김
        for (int r = R - 1; r > airBottom; r--) map[r][C-1] = map[r-1][C-1];
        // 4. 청정기 행: 오른쪽으로 당김
        for (int c = C - 1; c > 1; c--) map[airBottom][c] = map[airBottom][c - 1];
        // 5. 청정기에서 나오는 바람 = 0
        map[airBottom][1] = 0;

    }

    private static void spread(Queue<Dust> dustQueue) {
        int[][] diff = new int[R][C];

        for (Dust dust : dustQueue) {
            int count = 0;
            for (int i = 0; i < 4; i++) {
                int nr = dust.row + dr[i];
                int nc = dust.col + dc[i];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if ( map[nr][nc] == -1) continue;

                count++;
                diff[nr][nc] += map[dust.row][dust.col] / 5;
            }

            map[dust.row][dust.col] -= (map[dust.row][dust.col] / 5) * count;
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] += diff[r][c];
            }
        }

    }


    private static int counting() {
        int amount = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) amount += map[r][c];
            }
        }

        return amount;
    }

    static class Dust {
        int row, col;
        int amount;

        public Dust(int row, int col, int amount) {
            this.row = row;
            this.col = col;
            this.amount = amount;
        }
    }
}
