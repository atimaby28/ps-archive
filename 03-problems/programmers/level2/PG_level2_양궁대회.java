package programmers.level2;

import java.util.Arrays;

public class PG_level2_양궁대회 {
    static final int SCORE_SIZE = 11;
    static int[] answer;
    static int maxDiff;

    public int[] solution(int n, int[] info) {
        answer = new int[]{-1};
        maxDiff = 0;

        dfs(0, n, info, new int[SCORE_SIZE]);

        return answer;
    }

    private static void dfs(int index, int remain, int[] apeach, int[] ryan) {
        // 모든 점수 칸에 대해 선택이 끝난 경우
        if (index == SCORE_SIZE) {
            // 남은 화살은 0점에 넣는다
            ryan[10] += remain;

            calculateAndUpdate(apeach, ryan);

            // 백트래킹 복구
            ryan[10] -= remain;
            return;
        }

        int need = apeach[index] + 1;

        // 1. 현재 점수를 라이언이 이기는 경우
        if (remain >= need) {
            ryan[index] = need;
            dfs(index + 1, remain - need, apeach, ryan);
            ryan[index] = 0;
        }

        // 2. 현재 점수를 포기하는 경우
        dfs(index + 1, remain, apeach, ryan);
    }

    private static void calculateAndUpdate(int[] apeach, int[] ryan) {
        int apeachScore = 0;
        int ryanScore = 0;

        for (int i = 0; i < SCORE_SIZE; i++) {
            // 둘 다 0발이면 아무도 점수 못 얻음
            if (apeach[i] == 0 && ryan[i] == 0) {
                continue;
            }

            int score = 10 - i;

            if (ryan[i] > apeach[i]) {
                ryanScore += score;
            } else {
                apeachScore += score;
            }
        }

        int diff = ryanScore - apeachScore;

        // 라이언이 이기지 못하면 후보 아님
        if (diff <= 0) {
            return;
        }

        // 점수 차이가 더 크면 갱신
        if (diff > maxDiff) {
            maxDiff = diff;
            answer = Arrays.copyOf(ryan, SCORE_SIZE);
            return;
        }

        // 점수 차이가 같으면 낮은 점수를 더 많이 맞힌 경우 선택
        if (diff == maxDiff && isBetter(ryan, answer)) {
            answer = Arrays.copyOf(ryan, SCORE_SIZE);
        }
    }

    private static boolean isBetter(int[] candidate, int[] current) {
        // 낮은 점수부터 비교해야 하므로 뒤에서부터 확인
        for (int i = 10; i >= 0; i--) {
            if (candidate[i] > current[i]) {
                return true;
            }

            if (candidate[i] < current[i]) {
                return false;
            }
        }

        return false;
    }
}
