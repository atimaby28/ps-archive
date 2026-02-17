package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {

    static int[][] gear = new int[4][8];
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 4; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = s.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine().trim());

        int result = 0;

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            result += solution(idx, dir);
        }

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int idx, int dir) {
        return -1;
    }
}
