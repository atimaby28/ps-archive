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

                if (map[i][j] != 0) icebergs.offer(new Iceberg(i, j, map[i][j]));
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int year = 1;

        while (!icebergs.isEmpty()) {
            int size = icebergs.size();

            List<Iceberg> melt = new ArrayList<>();

            // melting check
            for (int i = 0; i < size; i++) {
                Iceberg cur = icebergs.poll();

                int surface = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (map[nr][nc] != 0) continue;

                    surface++;
                }
                if (cur.height - surface > 0) {
                    icebergs.offer(new Iceberg(cur.r, cur.c, cur.height - surface));
                } else {
                    melt.add(cur);
                }
            }

            for (Iceberg ib : melt) {
                map[ib.r][ib.c] = 0;
            }

            // melting
            for (Iceberg ib : icebergs) {
                map[ib.r][ib.c] = ib.height;
            }

            // isDivided
            if (isDivided()) return year;

            year++;
        }

        return 0;
    }

    private static boolean isDivided() {
        boolean[][] visited = new boolean[N][M];

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                visited[i][j] = true;
                bfs(i, j, visited);
                count++;
            }
        }

        return count > 1;
    }

    private static void bfs(int r, int c, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int cr = cur[0];
            int cc = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
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
