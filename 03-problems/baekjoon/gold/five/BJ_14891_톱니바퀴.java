package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {

    static int gearCount = 4, cog = 8;
    static int[][] gears = new int[gearCount][cog];
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 4; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = s.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine().trim());

        int result = 0;

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            solution(idx - 1, dir);
        }

        for (int i = 0; i < gearCount; i++) {
            if (gears[i][0] == 1) result += (int) Math.pow(2, i);
        }

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static void solution(int idx, int dir) {
        int[] rotations = new int[gearCount];
        rotations[idx] = dir;

        // 왼쪽 전파
        for (int i = idx; i > 0; i--) {
            if (gears[i - 1][2] != gears[i][6]) {
                rotations[i - 1] = -rotations[i];
            } else break;
        }

        // 오른쪽 전파
        for (int i = idx; i < gearCount - 1; i++) {
            if (gears[i][2] != gears[i + 1][6]) {
                rotations[i + 1] = -rotations[i];
            } else break;
        }

        // 한번에 회전
        for (int i = 0; i < gearCount; i++) {
            if (rotations[i] != 0) rotate(i, rotations[i]);
        }

    }

    private static void rotate(int idx, int rotation) {
        if (rotation == 1) {
            int temp = gears[idx][cog - 1];
            for (int i = cog - 1; i > 0; i--) {
                gears[idx][i] = gears[idx][i - 1];
            }
            gears[idx][0] = temp;
        } else {
            int temp = gears[idx][0];
            for (int i = 0; i < cog - 1; i++) {
                gears[idx][i] = gears[idx][i + 1];
            }
            gears[idx][cog - 1] = temp;
        }
    }

}
