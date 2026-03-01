package baekjoon.gold.five;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정 {

    static int N;
    static int[][] timeTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        timeTable = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            timeTable[i][0] = Integer.parseInt(st.nextToken());
            timeTable[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int answer = 1;

        Arrays.sort(timeTable, Comparator.comparingInt((int[] o) -> o[1])
                .thenComparingInt(o -> o[0]));

        int endTime = timeTable[0][1];
        for (int i = 1; i < N; i++) {
            if (timeTable[i][0] >= endTime) {
                answer++;
                endTime = timeTable[i][1];
            }
        }

        return answer;
    }
}
