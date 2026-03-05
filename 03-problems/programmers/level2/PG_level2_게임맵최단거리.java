package programmers.level2;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PG_level2_게임맵최단거리 {

    static final int SIZE = 5;
    static int[][] map = new int[SIZE][SIZE];
    static boolean[][] visited = new boolean[SIZE][SIZE];

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        for (int i = 0; i < SIZE; i++) {
            st = new  StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
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
        return bfs(0, 0);
    }

    private static int bfs(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int cr = cur[0];
            int cc = cur[1];

            if (cr == SIZE - 1 && cc == SIZE - 1) return map[SIZE - 1][SIZE - 1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nr >= SIZE || nc < 0 ||  nc >= SIZE) continue;
                if (visited[nr][nc] || map[nr][nc] == 0) continue;

                map[nr][nc] = map[cr][cc] + 1;
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        return -1;
    }
}
