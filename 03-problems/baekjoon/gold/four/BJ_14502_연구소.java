package baekjoon.gold.four;

import java.io.*;
import java.util.*;

public class BJ_14502_연구소 {

    static int N, M;
    static int[][] lab;
    static int size;

    static List<int[]> virus;
    static final int LIMIT = 3;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());

                if (lab[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        permutation(0);

        return size;
    }

    private static void permutation(int depth) {
        if (depth == LIMIT) {
            int[][] spreadMap = spread(virus);
            size = Math.max(size, getSize(spreadMap));
            return;
        }

        for (int i = 0; i < N * M; i++) {
            int row = i / M;
            int col = i % M;

            if (lab[row][col] == 0) {
                lab[row][col] = 1;
                permutation(depth + 1);
                lab[row][col] = 0;
            }
        }
    }

    private static int getSize(int[][] spreadMap) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (spreadMap[i][j] == 0) count++;
            }
        }

        return count;
    }

    private static int[][] spread(List<int[]> virus) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] spreadMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            spreadMap[i] = lab[i].clone();
        }

        for (int[] v : virus) {
            queue.offer(v);
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (spreadMap[nr][nc] == 1 || spreadMap[nr][nc] == 2) continue;

                spreadMap[nr][nc] = 2;
                queue.offer(new int[]{nr, nc});

            }
        }

        return spreadMap;
    }

}
