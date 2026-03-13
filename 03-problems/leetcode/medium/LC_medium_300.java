package leetcode.medium;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LC_medium_300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int result = solution(nums);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(int[] nums) {
        int answer = 0;

        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}
