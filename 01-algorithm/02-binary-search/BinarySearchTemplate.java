import java.io.*;
import java.util.*;

/**
 * 이분탐색 (Binary Search)
 *
 * [입력] - 정렬된 배열에서 target 찾기
 * 5
 * 1 3 5 7 9
 * 7
 *
 * [출력] - target의 인덱스 (없으면 -1)
 * 3
 *
 * [파라메트릭 서치 - 입력] - 나무 자르기 (BOJ 2805)
 * 4 7
 * 20 15 10 17
 *
 * [파라메트릭 서치 - 출력] - 절단기 높이의 최댓값
 * 15
 */
public class BinarySearchTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int target = Integer.parseInt(br.readLine().trim());

        int result = binarySearch(arr, target);
        sb.append(result).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 기본 이분탐색 =====
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // ===== Lower Bound (target 이상인 첫 위치) =====
    public static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    // ===== Upper Bound (target 초과인 첫 위치) =====
    public static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    // ===== 파라메트릭 서치 =====
    public static int parametricSearch(int[] arr, int target) {
        int left = 1, right = 1_000_000_000;
        int answer = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid, arr, target)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    private static boolean check(int mid, int[] arr, int target) {
        long sum = 0;
        for (int h : arr) {
            if (h > mid) sum += h - mid;
        }
        return sum >= target;
    }
}
