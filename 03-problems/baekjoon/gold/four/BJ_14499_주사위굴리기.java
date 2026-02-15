package baekjoon.gold.four;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14499_주사위굴리기 {

    static int N, M, K;
    static int[] dice;
    static int[][] map;

    static final int TOP = 0, EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4, BOTTOM = 5;

    static int[] dx = {0, 1, -1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] commands = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        String result = solution(x, y, commands);

        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static String solution(int x, int y, int[] commands) {
        StringBuilder sb = new StringBuilder();

        dice = new int[7];

        for (int command : commands) {
            int nx = x + dx[command];
            int ny = y + dy[command];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

            rolling(command);

            x = nx;
            y = ny;

            if (map[y][x] == 0) {
                map[y][x] = dice[BOTTOM];
            } else {
                dice[BOTTOM] = map[y][x];
                map[y][x] = 0;
            }

            sb.append(dice[TOP]).append("\n");
        }

        return sb.toString();
    }

    private static void rolling(int direction) {
        int top = dice[TOP], bottom = dice[BOTTOM], north = dice[NORTH],
        east = dice[EAST], west = dice[WEST], south = dice[SOUTH];

        switch (direction) {
            case EAST -> {
                dice[TOP] = west;
                dice[EAST] = top;
                dice[BOTTOM] = east;
                dice[WEST] = bottom;
            } case WEST -> {
                dice[TOP] = east;
                dice[EAST] = bottom;
                dice[BOTTOM] = west;
                dice[WEST] = top;
            } case NORTH -> {
                dice[TOP] = south;
                dice[NORTH] = top;
                dice[SOUTH] = bottom;
                dice[BOTTOM] = north;
            } case SOUTH -> {
                dice[TOP] = north;
                dice[NORTH] = bottom;
                dice[SOUTH] = top;
                dice[BOTTOM] = south;
            }
        }
    }
}