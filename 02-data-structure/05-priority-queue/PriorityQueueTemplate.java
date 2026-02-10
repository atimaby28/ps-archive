import java.io.*;
import java.util.*;

/**
 * 우선순위 큐 (Priority Queue) - 중앙값 구하기
 *
 * [입력] - 수가 하나씩 추가될 때마다 중앙값 출력
 * 5
 * 1
 * 5
 * 2
 * 10
 * -99
 *
 * [출력]
 * 1
 * 1
 * 2
 * 2
 * 2
 */
public class PriorityQueueTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    // 최대힙 (왼쪽 절반) + 최소힙 (오른쪽 절반)
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine().trim());
            addNumber(num);
            sb.append(getMedian()).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void addNumber(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.offer(num);
        else minHeap.offer(num);

        // 균형 맞추기 (maxHeap 크기 >= minHeap 크기)
        if (maxHeap.size() > minHeap.size() + 1) minHeap.offer(maxHeap.poll());
        else if (minHeap.size() > maxHeap.size()) maxHeap.offer(minHeap.poll());
    }

    public static int getMedian() {
        return maxHeap.peek();
    }

    // ===== K번째 최솟값 =====
    public static int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxPQ.offer(num);
            if (maxPQ.size() > k) maxPQ.poll();
        }
        return maxPQ.peek();
    }
}
