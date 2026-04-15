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
        int time = 0;

        StringBuilder sb = new StringBuilder();

        int count = 0;
        while (true) {
            List<int[]> cheese = bfs(0, 0, new boolean[N][M]);
            if (cheese.isEmpty()) break;

            count = melting(cheese);

            time++;
        }

        sb.append(time).append("\n").append(count);

        return sb.toString();

    }

    private static int melting(List<int[]> cheese) {
        int count = 0;

        for (int[] c : cheese) {
            map[c[0]][c[1]] = 0;
            count++;
        }

        return count;
    }

    private static List<int[]> bfs(int r, int c, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> cheese = new ArrayList<>();

        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int cr = cur[0];
            int cc = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;

                if (map[nr][nc] == 1) {
                    cheese.add(new int[]{nr, nc});
                } else {
                    queue.offer(new int[]{nr, nc});
                }

            }
        }

        return cheese;
    }

}
