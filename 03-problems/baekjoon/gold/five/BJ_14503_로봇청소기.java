package baekjoon.gold.five;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14503_로봇청소기 {

    static int N, M;
    static int[][] map;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution(y, x, d);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int y, int x, int d) {
        int cleanRoom = 0;

        while (true) {
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (map[y][x] == 0) {
                map[y][x] = -1;
                cleanRoom++;
            }

            boolean isCleaned = false;
            for (int i = 0; i < 4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];

                if (cx < 0 || cx >= M || cy < 0 || cy >= N) continue;

                if (map[cy][cx] == 0) {
                    isCleaned = true;
                    break;
                }
            }

            if (!isCleaned) {
                // 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                // 2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                int cx = x - dx[d];
                int cy = y - dy[d];

                if (cx < 0 || cx >= M || cy < 0 || cy >= N) break;
                if (map[cy][cx] == 1) break;

                x = cx;
                y = cy;
            } else {
                // 3-1. 반시계 90.
                d = rotate(d);

                int cx = x + dx[d];
                int cy = y + dy[d];

                if (cx < 0 || cx >= M || cy < 0 || cy >= N) continue;

                // 3-2. 바라보는 방향 기준 앞쪽이 청소되지 않았다면 한 칸 전진.
                if (map[cy][cx] == 0) {
                    x = cx;
                    y = cy;
                }
            }
        }

        return cleanRoom;
    }

    private static int rotate(int d) {

        switch (d) {
            case 0 -> { return 3; }
            case 1 -> { return 0; }
            case 2 -> { return 1; }
            case 3 -> { return 2; }
            default -> { return -1; }
        }
    }
}
