package baekjoon.silver.three;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_3273_두수의합 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[] arrays = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrays[i] = Integer.parseInt(st.nextToken());
        }

        int target = Integer.parseInt(br.readLine());

        int result = solution(arrays, target);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int[] arrays, int target) {
        int answer = 0;

        Arrays.sort(arrays);

        int left = 0, right = N - 1;

        while (left < right) {
            int sum = arrays[left] + arrays[right];

            if (sum > target) {
                right--;
            } else if (sum <= target){
                left++;
            } else {
                answer++;
                left++;
                right--;
            }
        }

        return answer;
    }
}
