package baekjoon.gold.four;

import java.io.*;
import java.util.*;

public class BJ_2636_치즈 {

    static int N, M;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
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

        int count = 0, time = 0;
        while (true) {

            // cheese
            List<int[]> cheese = bfs(0, 0);

            if (cheese.isEmpty()) break;

            count = cheese.size();

            // melting
            for (int[] pos : cheese) {
                map[pos[0]][pos[1]] = 0;
            }

            time++;
        }

        sb.append(time).append("\n").append(count);

        return sb.toString();
    }

    private static List<int[]> bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> cheese = new ArrayList<>();
        boolean[][] visited = new boolean[N][M];

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
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                if (map[nr][nc] == 1) cheese.add(new int[]{nr, nc});
                else queue.offer(new int[]{nr, nc});
            }

        }

        return cheese;
    }
}
