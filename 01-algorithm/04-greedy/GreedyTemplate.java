import java.io.*;
import java.util.*;

/**
 * 그리디 (Greedy) - 회의실 배정
 *
 * [입력]
 * 5               ← 회의 수
 * 1 4             ← 시작 끝
 * 3 5
 * 0 6
 * 5 7
 * 3 8
 *
 * [출력] - 최대 회의 수
 * 3
 */
public class GreedyTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        int[][] meetings = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken()); // 시작
            meetings[i][1] = Integer.parseInt(st.nextToken()); // 끝
        }

        sb.append(meetingRoom(meetings)).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 회의실 배정 (끝나는 시간 기준 정렬) =====
    public static int meetingRoom(int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int count = 0, lastEnd = 0;
        for (int[] m : meetings) {
            if (m[0] >= lastEnd) {
                count++;
                lastEnd = m[1];
            }
        }
        return count;
    }

    // ===== 동전 거스름돈 =====
    public static int coinChange(int amount) {
        int[] coins = {500, 100, 50, 10};
        int count = 0;
        for (int coin : coins) {
            count += amount / coin;
            amount %= coin;
        }
        return count;
    }

    // ===== 구간 스케줄링 (겹치는 구간 최소 제거) =====
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int count = 0, lastEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] >= lastEnd) lastEnd = interval[1];
            else count++;
        }
        return count;
    }
}
