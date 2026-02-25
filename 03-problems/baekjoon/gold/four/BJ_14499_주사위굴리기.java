package baekjoon.gold.four;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14499_주사위굴리기 {

    static int N, M, K;
    static int[] dice = new int[7];
    static int[][] map;
    static int[] command;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static final int TOP = 1, NORTH = 2, EAST = 3, WEST = 4, SOUTH = 5, BOTTOM = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        command = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        String result = solution(x, y);

        bw.write(result);

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution(int y, int x) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            int nx = x + dx[command[i] - 1];
            int ny = y + dy[command[i] - 1];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

            rolling(command[i]);

            if (map[ny][nx] == 0) {
                map[ny][nx] = dice[BOTTOM];
            } else {
                dice[BOTTOM] = map[ny][nx];
                map[ny][nx] = 0;
            }

            x = nx;
            y = ny;

            sb.append(dice[TOP]).append("\n");
        }

        return sb.toString();
    }

    private static void rolling(int command) {
        int temp;

        switch (command) {
            case 1 -> { // 동
                temp = dice[TOP];
                dice[TOP] = dice[WEST];
                dice[WEST] = dice[BOTTOM];
                dice[BOTTOM] = dice[EAST];
                dice[EAST] = temp;
            }
            case 2 -> { // 서
                temp = dice[TOP];
                dice[TOP] = dice[EAST];
                dice[EAST] = dice[BOTTOM];
                dice[BOTTOM] = dice[WEST];
                dice[WEST] = temp;
            }
            case 3 -> { // 북
                temp = dice[TOP];
                dice[TOP] = dice[SOUTH];
                dice[SOUTH] = dice[BOTTOM];
                dice[BOTTOM] = dice[NORTH];
                dice[NORTH] = temp;
            }
            case 4 -> { // 남
                temp = dice[TOP];
                dice[TOP] = dice[NORTH];
                dice[NORTH] = dice[BOTTOM];
                dice[BOTTOM] = dice[SOUTH];
                dice[SOUTH] = temp;
            }
            default -> {}
        }
    }
}