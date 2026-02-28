package baekjoon.silver.one;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14889_스타트와링크 {

    static int answer = Integer.MAX_VALUE;
    static int N;
    static int[][] skills;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        skills = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                skills[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        search(0, 0, new boolean[N]);

        return answer;
    }

    private static void search(int depth, int index, boolean[] result) {
        if (depth == N / 2) {

            answer = Math.min(answer, getMinScore(result));

            return;
        }

        for (int i = index; i < N; i++) {
            result[i] = true;
            search(depth + 1, i + 1, result);
            result[i] = false;
        }
    }

    private static int getMinScore(boolean[] result) {
        int startTeam = 0, linkTeam = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (result[i] && result[j]) startTeam += skills[i][j] + skills[j][i];
                else if (!result[i] && !result[j]) linkTeam += skills[i][j] + skills[j][i];
            }
        }

        return Math.abs(startTeam - linkTeam);
    }
}
