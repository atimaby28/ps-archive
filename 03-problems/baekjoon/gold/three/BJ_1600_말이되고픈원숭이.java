package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1600_말이되고픈원숭이 {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dr = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dc = {0, 0, -1, 1, -2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int r = 0; r < H; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < W; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        // 0: 평지, 1: 장애물
        return bfs(0, 0);
    }

    private static int bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{r, c, 0, 0});
        visited[r][c][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int curR = cur[0];
            int curC = cur[1];
            int curK = cur[2];
            int curMove = cur[3];

            if (curR == H - 1 && curC == W - 1) return curMove;

            for (int d = 0; d < 12; d++) {
                int newR = curR + dr[d];
                int newC = curC + dc[d];

                if (newR < 0 || newR >= H || newC < 0 || newC >= W) continue;
                if (curK >= K && d >= 4) break;

                if (curK < K && d >= 4) {
                    if (map[newR][newC] == 1 || visited[newR][newC][curK + 1]) continue;
                    visited[newR][newC][curK + 1] = true;
                    queue.offer(new int[]{newR, newC, curK + 1, curMove + 1});
                } else {
                    if (map[newR][newC] == 1 || visited[newR][newC][curK]) continue;
                    visited[newR][newC][curK] = true;
                    queue.offer(new int[]{newR, newC, curK, curMove + 1});
                }
            }
        }

        return -1;
    }
}
