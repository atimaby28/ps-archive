package leetcode.medium;

import java.io.*;
import java.util.*;

public class LC_medium_994 {

    static int M, N;
    static int rotten;

    static Queue<int[]> rottenPosition;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] grid = new int[M][N];
        rottenPosition = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution(grid);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int[][] grid) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 2) {
                    rottenPosition.add(new int[]{i, j});
                } else if (grid[i][j] == 1) rotten++;
            }
        }

        int minute = bfs(grid);

        return rotten == 0 ?  minute : -1;
    }

    private static int bfs(int[][] grid) {
        int minute = 0;

        while (!rottenPosition.isEmpty()) {
            boolean isRotten = false;
            int rottenSize = rottenPosition.size();

            for (int i = 0; i < rottenSize; i++) {
                int[] rottenPos = rottenPosition.poll();

                int cr = rottenPos[0];
                int cc =  rottenPos[1];

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
                    if (grid[nr][nc] == 0 || grid[nr][nc] == 2) continue;

                    rotten--; isRotten = true;
                    grid[nr][nc] = 2;
                    rottenPosition.add(new int[]{nr, nc});
                }
            }
            if (isRotten) minute++;
        }

        return minute;
    }
}
