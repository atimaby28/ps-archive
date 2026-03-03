package baekjoon.gold.three;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15683_감시 {

    static int N, M;
    static int blindSpot;
    static int[][] map;

    static List<CCTV> cctvList = new ArrayList<>();

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{-1, 0, 1, 0};

    // 0: 상, 1: 우, 2: 하, 3: 좌
    static int[][][] cctv = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
            {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}},
            {{0, 1, 2, 3}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        blindSpot = N * M + 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] > 0 && map[i][j] < 6) {
                    cctvList.add(new CCTV(i, j, map[i][j]));
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
        tracking(0);

        return blindSpot;
    }

    private static void tracking(int count) {
        if (count == cctvList.size()) {
            blindSpot = Math.min(blindSpot, getMinBlindSpot());
            return;
        }

        int type = cctvList.get(count).type;

        for (int[] direction : cctv[type]) {
            mark(count, direction);
            tracking(count + 1);
            unmark(count, direction);
        }

    }

    private static int getMinBlindSpot() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) count++;
            }
        }

        return count;
    }

    private static void mark(int count, int[] direction) {
        CCTV cur = cctvList.get(count);

        for (int d : direction) {
            int nx = cur.x;
            int ny = cur.y;

            while (true) {
                nx += dx[d];
                ny += dy[d];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) break;
                if (map[ny][nx] == 6) break;

                if (map[ny][nx] > 0 && map[ny][nx] < 6) continue;

                map[ny][nx]--;
            }
        }
    }

    private static void unmark(int count, int[] direction) {
        CCTV cur = cctvList.get(count);

        for (int d : direction) {
            int nx = cur.x;
            int ny = cur.y;

            while (true) {
                nx += dx[d];
                ny += dy[d];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) break;
                if (map[ny][nx] == 6) break;

                if (map[ny][nx] > 0 && map[ny][nx] < 6) continue;

                if (map[ny][nx] < 0) map[ny][nx]++;
            }
        }
    }

    static class CCTV {
        int x, y;
        int type;

        public CCTV(int y, int x, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
