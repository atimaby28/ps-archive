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
            if (gears[i][0] == 1) result += 1 << i;
        }

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static void solution(int index, int dir) {
        int[] dirs = new int[gearCount];
        dirs[index] = dir;

        propagate(index, dirs);

        for (int i = 0; i < gearCount; i++) {
            if (dirs[i] == 0) continue;
            rotate(i, dirs[i]);
        }

    }

    private static void rotate(int index, int d) {
        if (d == 1) {
            int temp = gears[index][cog - 1];
            for (int i = cog - 1; i > 0; i--) {
                gears[index][i] = gears[index][i - 1];
            }
            gears[index][0] = temp;
        } else {
            int temp = gears[index][0];
            for (int i = 0; i < cog - 1; i++) {
                gears[index][i] = gears[index][i + 1];
            }
            gears[index][cog - 1] = temp;
        }
    }

    private static void propagate(int index, int[] dirs) {
        for (int i = index - 1; i >= 0; i--) {
            if (gears[i][2] != gears[i + 1][6]) dirs[i] = -dirs[i + 1];
            else break;
        }
        for (int i = index + 1; i < gearCount; i++) {
            if (gears[i][6] != gears[i - 1][2]) dirs[i] = -dirs[i - 1];
            else break;
        }
    }
}
