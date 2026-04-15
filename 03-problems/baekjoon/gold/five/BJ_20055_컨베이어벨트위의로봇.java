package baekjoon.gold.five;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어벨트위의로봇 {

    static int N, K;
    static int[][] belt;
    static boolean[][] robotPos;

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
        int steps = 1;
        robotPos = new boolean[2][N];

        while (true) {
            // 벨트의 회전
            // 윗줄
            int beltTop = belt[0][N - 1];
            boolean robotTop = robotPos[0][N - 1];
            for (int i = N - 1; i > 0; i--) {
                belt[0][i] = belt[0][i - 1];
                robotPos[0][i] = robotPos[0][i - 1];
            }

            // 아랫줄
            int beltBottom = belt[1][0];
            boolean robotBottom = robotPos[1][0];
            for (int i = 0; i < N - 1; i++) {
                belt[1][i] = belt[1][i + 1];
                robotPos[1][i] = robotPos[1][i + 1];
            }

            belt[0][0] = beltBottom;
            belt[1][N - 1] = beltTop;

            robotPos[0][0] = robotBottom;
            robotPos[1][N - 1] = robotTop;

            robotPos[0][N - 1] = false;

            // 로봇의 추가 이동
            for (int i = N - 1; i > 0; i--) {
                if (belt[0][i] > 0 && !robotPos[0][i] && robotPos[0][i - 1]){
                    robotPos[0][i] = robotPos[0][i - 1];
                    robotPos[0][i-1] = false;
                    belt[0][i]--;
                }

            }

            for (int i = 0; i < N - 1; i++) {
                if (belt[1][i] > 0 && !robotPos[1][i] && robotPos[1][i + 1]) {
                    robotPos[1][i] = robotPos[1][i + 1];
                    belt[1][i]--;
                }
            }

            if (belt[0][0] > 0 && !robotPos[0][0]) {
                robotPos[0][0] = true;
                belt[0][0]--;
            }

            robotPos[0][N - 1] = false;

            // count
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (belt[0][i] == 0) count++;
                if (belt[1][N - 1 - i] == 0) count++;
            }

            if (count >= K) break;
            steps++;
        }

        return steps;
    }

}
