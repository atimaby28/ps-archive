package baekjoon.gold.five;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어벨트위의로봇 {

    static int N, K;
    static int[][] belt;
    static boolean[] robotPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

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
        int rotation = 0;

        robotPos = new boolean[N];

        while (true) {
            rotation++;

            // 벨트 이동
            moveBelt();

            // 로봇 이동
            moveRobot();

            // 로봇 올리기
            if (!robotPos[0] && belt[0][0] > 0) {
                robotPos[0] = true;
                belt[0][0]--;
            }

            // 0의 갯수 >= K이면 종료
            int zeroDurability = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < N; j++) {
                    if (belt[i][j] == 0) zeroDurability++;
                }
            }

            if (zeroDurability >= K) break;

        }

        return rotation;
    }

    private static void moveRobot() {
        // 밸트와 회전
        for (int i = N - 1; i > 0; i--) {
            robotPos[i] = robotPos[i - 1];
        }

        robotPos[0] = false;
        if (robotPos[N - 1]) robotPos[N - 1] = false;

        // 가능하면 자체이동
        for (int i = N - 1; i > 0; i--) {
            if (robotPos[i - 1] && !robotPos[i] && belt[0][i] > 0) {
                robotPos[i] = true;
                robotPos[i - 1] = false;
                belt[0][i]--;
            }
        }

        if (robotPos[N - 1]) robotPos[N - 1] = false;
    }

    private static void moveBelt() {
        int down = belt[0][N - 1];
        int up = belt[1][0];

        for (int i = N - 1; i > 0; i--) {
            belt[0][i] = belt[0][i - 1];
        }

        for (int i = 0; i < N - 1; i++) {
            belt[1][i] = belt[1][i + 1];
        }

        belt[1][N - 1] = down;
        belt[0][0] = up;
    }
}
