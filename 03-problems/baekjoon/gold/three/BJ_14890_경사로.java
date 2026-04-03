package baekjoon.gold.three;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14890_경사로 {

    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

     private static int solution() {
        int count = 0;

        for (int row = 0; row < N; row++) {
            int[] col = new int[N];

            for (int i = 0; i < N; i++) {
                col[i] = map[i][row];
            }

            if (checking(map[row], new boolean[N])) count++;
            if (checking(col, new boolean[N])) count++;
        }

        return count;
    }

    private static boolean checking(int[] levels, boolean[] isPlaced) {

        for (int i = 1; i < N; i++) {
            int diff = levels[i] - levels[i - 1];

            if (diff == 0) continue;

            if (diff == 1) { // 오르막
                if (i - L < 0) return false;

                for (int j = i - 1; j >= i -L; j--) {
                    if (isPlaced[j]) return false;
                    if (levels[j] != levels[i] - 1) return false;

                    isPlaced[j] = true;
                }

            } else if (diff == -1) { // 내리막
                if (i + L > N) return false;

                for (int j = i; j < i + L; j++) {
                    if (isPlaced[j]) return false;
                    if (levels[j] != levels[i - 1] - 1) return false;

                    isPlaced[j] = true;
                }

            } else {
                return false;
            }
        }

        return true;
    }

}
