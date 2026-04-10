package baekjoon.gold.five;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_16935_배열돌리기3 {

    static int N, M, R;
    static int[][] array;

    static int[] command;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        array = new int[N][M];
        command = new int[R];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        String result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < R; i++) {
            int type = command[i];

            int[][] copied = new int[N][M];

            switch (type) {
                case 1 -> {
                    int k = N / 2;

                    for (int r = 0; r < k; r++) {
                        for (int c = 0; c < M; c++) {
                            int temp = array[r][c];
                            array[r][c] = array[N - 1 - r][c];
                            array[N - 1 - r][c] = temp;
                        }
                    }
                }
                case 2 -> {
                    int k = M / 2;

                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < k; c++) {
                            int temp = array[r][c];
                            array[r][c] = array[r][M - 1 - c];
                            array[r][M - 1 - c] = temp;
                        }
                    }
                }
                case 3 -> {

                    int[][] newArr = new int[M][N];
                    for (int r = 0; r < N; r++){
                        for (int c = 0; c < M; c++) {
                            newArr[c][N - 1 - r] = array[r][c];
                        }
                    }

                    array = newArr;

                    int temp = N;
                    N = M;
                    M = temp;
                }
                case 4 -> {
                    int[][] newArr = new int[M][N];
                    for (int r = 0; r < N; r++){
                        for (int c = 0; c < M; c++) {
                            newArr[M - 1 - c][r] = array[r][c];
                        }
                    }

                    array = newArr;

                    int temp = N;
                    N = M;
                    M = temp;
                }
                case 5 -> {
                    int p = N / 2;
                    int q = M / 2;

                    for (int k = 0; k < N; k++) {
                        copied[k] = array[k].clone();
                    }

                    // 1 -> 2
                    for (int r = 0; r < p; r++) {
                        for (int c = 0; c < q; c++) {
                            array[r][c + q] = copied[r][c];
                        }
                    }

                    // 2 -> 3
                    for (int r = 0; r < p; r++) {
                        for (int c = q; c < M; c++) {
                            array[r + p][c] = copied[r][c];
                        }
                    }

                    // 3 -> 4
                    for (int r = p; r < N; r++) {
                        for (int c = 0; c < q; c++) {
                            array[r][c] = copied[r][c + q];
                        }
                    }

                    // 4 -> 1
                    for (int r = 0; r < p; r++) {
                        for (int c = 0; c < q; c++) {
                            array[r][c] = copied[r + p][c];
                        }
                    }
                }
                case 6 -> {
                    int p = N / 2;
                    int q = M / 2;

                    for (int k = 0; k < N; k++) {
                        copied[k] = array[k].clone();
                    }

                    // 1 -> 4
                    for (int r = 0; r < p; r++) {
                        for (int c = 0; c < q; c++) {
                            array[r + p][c] = copied[r][c];
                        }
                    }

                    // 4 -> 3
                    for (int r = p; r < N; r++) {
                        for (int c = q; c < M; c++) {
                            array[r][c] = copied[r][c - q];
                        }
                    }

                    // 3 -> 2
                    for (int r = 0; r < p; r++) {
                        for (int c = q; c < M; c++) {
                            array[r][c] = copied[r + p][c];
                        }
                    }

                    // 2 -> 1
                    for (int r = 0; r < p; r++) {
                        for (int c = 0; c < q; c++) {
                            array[r][c] = copied[r][c + q];
                        }
                    }
                }
                default -> {
                    return "-1";
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(array[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();

    }
}