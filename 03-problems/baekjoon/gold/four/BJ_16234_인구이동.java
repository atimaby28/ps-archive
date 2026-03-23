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
        int days = 0;

        while (true) {
            List<Nation> nations;
            boolean[][] visited = new boolean[N][N];

            boolean isMoved = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    nations = bfs(i, j, visited);

                    if (nations.size() >= 2) {
                        move(nations);
                        isMoved = true;
                    }
                }
            }

            if (!isMoved) break;

            // 일자 증가
            days++;
        }

        return days;
    }

    private static void move(List<Nation> nations) {
        int nationCount = nations.size();

        int avg = 0;
        for (Nation nation : nations) {
            avg += map[nation.r][nation.c];
        }

        avg /= nationCount;

        for (Nation nation : nations) {
            map[nation.r][nation.c] = avg;
        }
    }

    private static List<Nation> bfs(int r, int c, boolean[][] visited) {
        Queue<Nation> queue = new ArrayDeque<>();
        List<Nation> nations = new ArrayList<>();

        queue.offer(new Nation(r, c));
        nations.add(new Nation(r, c));

        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Nation cur = queue.poll();

            int curR = cur.r;
            int curC = cur.c;

            for (int d = 0; d < 4; d++) {
                int newR = curR + dr[d];
                int newC = curC + dc[d];

                if (newR < 0 || newR >= N || newC < 0 || newC >= N) continue;
                if (visited[newR][newC]) continue;

                if (canMove(curR, curC, newR, newC)) {
                    nations.add(new Nation(newR, newC));
                    visited[newR][newC] = true;
                    queue.offer(new Nation(newR, newC));
                }
            }
        }

        return nations;
    }

    private static boolean canMove(int curR, int curC, int newR, int newC) {
        return Math.abs(map[curR][curC] - map[newR][newC]) >= L && Math.abs(map[curR][curC] - map[newR][newC]) <= R;
    }

    static class Nation {
        int r, c;

        Nation(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
