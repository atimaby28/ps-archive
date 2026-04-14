package baekjoon.gold.four;

import java.io.*;
import java.util.*;

public class BJ_16234_인구이동 {

    static int N, L, R;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int day = 0;
        while (true) {
            boolean[][] visited = new boolean[N][N];

            List<Nation> nations = new ArrayList<>();

            boolean isMoveded = false;
            // get diff
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    nations = bfs(i, j, visited);

                    if (nations.size() > 1) {
                        move(nations);
                        isMoveded = true;
                    }
                }
            }

            if (!isMoveded) break;


            day++;
        }

        return day;
    }

    private static void move(List<Nation> nations) {
        int size = nations.size();

        int totalCount = 0;
        for (Nation nation : nations) {
            totalCount += nation.population;
        }

        int avg = totalCount / size;

        for (Nation nation : nations) {
            map[nation.r][nation.c] = avg;
        }

    }

    private static List<Nation> bfs(int r, int c, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<Nation> nations = new ArrayList<>();

        queue.offer(new int[]{r, c});
        nations.add(new Nation(r, c, map[r][c]));
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;

                // check logic
                int diff = Math.abs(map[nr][nc] - map[cr][cc]);
                if (diff >= L && diff <= R) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                    nations.add(new Nation(nr, nc, map[nr][nc]));
                }
            }
        }

        return nations;
    }

    static class Nation {
        int r, c;
        int population;

        public Nation(int r, int c, int population) {
            this.r = r;
            this.c = c;
            this.population = population;
        }
    }

}
