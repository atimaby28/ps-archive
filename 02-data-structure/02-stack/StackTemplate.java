import java.io.*;
import java.util.*;

/**
 * 스택 (Stack) - 괄호 검사 & 모노톤 스택
 *
 * [괄호 검사 - 입력]
 * (())()
 *
 * [괄호 검사 - 출력]
 * YES
 *
 * [모노톤 스택(오큰수) - 입력]
 * 4
 * 3 5 2 7
 *
 * [모노톤 스택 - 출력] - 각 원소의 오른쪽에서 자기보다 큰 첫 번째 수 (-1이면 없음)
 * 5 7 7 -1
 */
public class StackTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] result = nextGreaterElement(arr);

        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(result[i]);
        }
        sb.append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 괄호 검사 =====
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    // ===== 모노톤 스택 (오큰수) =====
    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return result;
    }

    // ===== 히스토그램에서 가장 큰 직사각형 =====
    public static long largestRectangle(int[] heights) {
        int n = heights.length;
        long maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, (long) height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
