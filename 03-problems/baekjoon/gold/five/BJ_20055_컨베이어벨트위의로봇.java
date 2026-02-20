package baekjoon.gold.five;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어벨트위의로봇 {

    static int N, K;
    static int[][] belt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2][N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            belt[0][i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) {
            belt[1][i] = Integer.parseInt(st.nextToken());
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        boolean[] isRobot = new boolean[N];
        int zeroCount = 0, rotations = 0;

        while (true) {
            // 벨트 회전
            int down = belt[0][N - 1];
            int up = belt[1][0];

            for (int i = N - 1; i > 0; i--) {
                belt[0][i] = belt[0][i - 1];
            }

            for (int i = 0; i < N - 1; i++) {
                belt[1][i] = belt[1][i + 1];
            }

            belt[0][0] = up;
            belt[1][N - 1] = down;

            // 로봇 회전
            for (int i = N - 1; i > 0; i--) {
                isRobot[i] = isRobot[i - 1];
            }
            isRobot[0] = false;
            isRobot[N - 1] = false;

            for (int i = N - 1; i > 0; i--) {
                if (isRobot[i - 1] && belt[0][i] > 0 && !isRobot[i]) {
                    belt[0][i]--;
                    if (belt[0][i] == 0) zeroCount++;
                    isRobot[i] = isRobot[i - 1];
                    isRobot[i - 1] = false;
                }
            }
            isRobot[N - 1] = false;

            if (belt[0][0] != 0 && !isRobot[0]) {
                isRobot[0] = true;
                belt[0][0]--;
                if (belt[0][0] == 0) zeroCount++;
            }

            rotations++;

            if (zeroCount >= K) break;
        }

        return rotations;
    }
}
