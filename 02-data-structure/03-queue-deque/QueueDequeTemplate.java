import java.io.*;
import java.util.*;

/**
 * 큐 / 덱 (Queue / Deque) - 슬라이딩 윈도우 최댓값
 *
 * [입력]
 * 8 3           ← 배열크기 윈도우크기
 * 1 3 -1 -3 5 3 6 7
 *
 * [출력] - 각 윈도우의 최댓값
 * 3 3 5 5 6 7
 */
public class QueueDequeTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] result = maxSlidingWindow(arr, k);

        for (int i = 0; i < result.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(result[i]);
        }
        sb.append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 슬라이딩 윈도우 최댓값 (덱) =====
    public static int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // 인덱스 저장

        for (int i = 0; i < n; i++) {
            // 윈도우 범위 벗어난 앞쪽 제거
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1)
                deque.pollFirst();
            // 현재 값보다 작은 뒤쪽 제거
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i])
                deque.pollLast();

            deque.offerLast(i);

            if (i >= k - 1)
                result[i - k + 1] = arr[deque.peekFirst()];
        }
        return result;
    }
}
