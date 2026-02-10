import java.io.*;
import java.util.*;

/**
 * 정렬 (Sorting)
 *
 * [입력]
 * 5
 * 5 3 1 4 2
 *
 * [출력]
 * 1 2 3 4 5
 *
 * [커스텀 정렬 - 입력]
 * 4
 * 3 5
 * 1 2
 * 3 1
 * 2 7
 *
 * [커스텀 정렬 - 출력] (첫번째 오름차순, 같으면 두번째 오름차순)
 * 1 2
 * 2 7
 * 3 1
 * 3 5
 */
public class SortingTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        sb.append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 커스텀 정렬 (2차원 배열) =====
    public static void customSort(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
    }

    // ===== 카운팅 정렬 =====
    public static void countingSort(int[] arr, int maxVal) {
        int[] count = new int[maxVal + 1];
        for (int num : arr) count[num]++;

        int idx = 0;
        for (int i = 0; i <= maxVal; i++) {
            while (count[i]-- > 0) arr[idx++] = i;
        }
    }

    // ===== 퀵 정렬 =====
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = arr[(left + right) / 2];
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                i++; j--;
            }
        }
        quickSort(arr, left, j);
        quickSort(arr, i, right);
    }

    // ===== 머지 정렬 =====
    static int[] temp;

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        temp = new int[arr.length];
        System.arraycopy(arr, left, temp, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) arr[k++] = temp[i++];
            else arr[k++] = temp[j++];
        }
        while (i <= mid) arr[k++] = temp[i++];
    }
}
