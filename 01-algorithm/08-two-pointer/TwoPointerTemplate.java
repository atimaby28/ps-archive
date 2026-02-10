import java.io.*;
import java.util.*;

/**
 * 투 포인터 / 슬라이딩 윈도우
 *
 * [입력] - 부분합이 target인 구간 개수
 * 10 5           ← 배열크기 target
 * 1 2 3 4 2 5 3 1 1 2
 *
 * [출력]
 * 3
 *
 * [슬라이딩 윈도우 - 입력] - 크기 K 구간 최대합
 * 7 3            ← 배열크기 윈도우크기
 * 2 1 5 1 3 2 7
 *
 * [슬라이딩 윈도우 - 출력]
 * 12
 */
public class TwoPointerTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        sb.append(twoPointerSum(arr, target)).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 투 포인터 (부분합 = target) =====
    public static int twoPointerSum(int[] arr, int target) {
        int left = 0, sum = 0, count = 0;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            while (sum > target) sum -= arr[left++];
            if (sum == target) count++;
        }
        return count;
    }

    // ===== 슬라이딩 윈도우 (고정 크기 K) =====
    public static int slidingWindowMax(int[] arr, int k) {
        int sum = 0, maxSum = 0;
        for (int i = 0; i < k; i++) sum += arr[i];
        maxSum = sum;
        for (int i = k; i < arr.length; i++) {
            sum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // ===== 양쪽 끝 투 포인터 (정렬된 배열에서 합 = target) =====
    public static boolean twoSum(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) return true;
            else if (sum < target) left++;
            else right--;
        }
        return false;
    }
}
