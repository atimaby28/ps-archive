package baekjoon.gold.four;

import java.io.*;
import java.util.*;

public class BJ_14502_연구소 {

    static int N, M;
    static int[][] lab;
    static Queue<int[]> virus;

    static int safeZone = 0;
    static final int MAX_WALL = 3;

    static int[] dr = {-1, 1 , 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];
        virus = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) virus.add(new int[] {i, j});
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        // 0: 빈칸, 1: 벽, 2: 바이러스
        dfs(0, 0);

        return safeZone;
    }

    private static void dfs(int start, int wallCount) {
        if (wallCount == MAX_WALL) {
            int[][] copyLab = new int[N][M];

            for (int i = 0; i < N; i++)
                copyLab[i] = lab[i].clone();

            safeZone = Math.max(safeZone, spreadVirus(copyLab));
            return;
        }

        for (int i = start; i < N * M; i++) {
            int r = i / M;
            int c = i % M;

            if (lab[r][c] != 0) continue;

            lab[r][c] = 1;
            dfs(i + 1, wallCount + 1);
            lab[r][c] = 0;
        }
    }

    private static int spreadVirus(int[][] copyLab) {
        int safeZone = 0;
        Queue<int[]> newVirus = new ArrayDeque<>(virus);

        while (!newVirus.isEmpty()) {
            int size = newVirus.size();

            for (int k = 0; k < size; k++) {
                int[] cur =  newVirus.poll();

                int curR = cur[0];
                int curC = cur[1];

                for (int i = 0; i < 4; i++) {
                    int newR = curR + dr[i];
                    int newC = curC + dc[i];

                    if (newR < 0 || newR >= N || newC < 0 || newC >= M) continue;
                    if (copyLab[newR][newC] != 0) continue;

                    copyLab[newR][newC] = 2;
                    newVirus.offer(new int[] {newR, newC});
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (copyLab[i][j] == 0) safeZone++;
            }
        }

        return safeZone;
    }
}
