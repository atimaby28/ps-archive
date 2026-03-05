package baekjoon.gold.three;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206_벽부수고이동하기 {

    static int N, M;
    static int[][] map;
    static int[][][] visited;

    static final int UNBROKEN = 0;
    static final int BROKEN = 1;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        return bfs(0, 0, 0, 1);
    }

    private static int bfs(int row, int col, int isBroken, int distance) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{row, col, isBroken, distance});
        visited[row][col][UNBROKEN] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int curRow = cur[0];
            int curCol = cur[1];
            int curBroken = cur[2];
            int curDistance = cur[3];

            if (curRow == N - 1 && curCol == M - 1) return curDistance;

            for (int d = 0; d < 4; d++) {
                int newRow = curRow + dr[d];
                int newCol = curCol + dc[d];

                if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= M) continue;

                if (map[newRow][newCol] == 0) { // 길
                    // 방문하지 않았다면, 전파
                    if (visited[newRow][newCol][curBroken] == 0) {
                        queue.offer(new int[]{newRow, newCol, curBroken, curDistance + 1});
                        visited[newRow][newCol][curBroken] = 1;
                    }
                } else { // 벽
                    // 부수기가 있다면, 부수기
                    if (visited[newRow][newCol][BROKEN] == 0 && curBroken == 0) {
                        queue.offer(new int[]{newRow, newCol, BROKEN, curDistance + 1});
                        visited[newRow][newCol][BROKEN] = 1;
                    }
                }

            }
        }

        return -1;
    }
}
