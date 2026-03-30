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
        permutation(0, 0);

        return size;
    }

    private static void permutation(int depth, int position) {
        if (depth == LIMIT) {
            int[][] labCopy = new int[N][M];

            for (int i = 0; i < N; i++) {
                labCopy[i] = lab[i].clone();
            }

            spread(labCopy);
            size = Math.max(size, count(labCopy));
            return;
        }

        for (int i = position; i < N * M; i++) {
            int row = i / M;
            int col = i % M;

            if (lab[row][col] == 1 || lab[row][col] == 2) continue;

            lab[row][col] = 1;
            permutation(depth + 1, i + 1);
            lab[row][col] = 0;
        }
    }

    // 0: empty, 1: wall, 2: virus
    private static void spread(int[][] labCopy) {
        Queue<int[]> queue = new LinkedList<>(virus);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] virusPos = queue.poll();

                int row = virusPos[0];
                int col = virusPos[1];

                for (int d = 0; d < 4; d++) {
                    int newRow = row + dr[d];
                    int newCol = col + dc[d];

                    if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= M) continue;
                    if (labCopy[newRow][newCol] == 1 || labCopy[newRow][newCol] == 2) continue;

                    labCopy[newRow][newCol] = 2;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

    }

    private static int count(int[][] labCopy) {
        int count = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (labCopy[r][c] == 0) count++;
            }
        }

        return count;
    }

}
