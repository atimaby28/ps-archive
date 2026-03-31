package baekjoon.gold.four;

import java.io.*;
import java.util.*;

public class BJ_2573_빙산 {

    static int N, M;
    static int[][] map;
    static Queue<Iceberg> icebergs;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        icebergs = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                icebergs.offer(new Iceberg(i, j, map[i][j]));
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        // melting
        while (!icebergs.isEmpty()) {
            int size = icebergs.size();

            for (int i = 0; i < size; i++) {
                Iceberg cur = icebergs.poll();

                int surface = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (map[nr][nc] != 0) continue;


                }

            }

            // isDivided
        }

        return 0;
    }

    static class Iceberg {
        int r, c;
        int height;

        public Iceberg(int r, int c, int height) {
            this.r = r;
            this.c = c;
            this.height = height;
        }
    }
}
