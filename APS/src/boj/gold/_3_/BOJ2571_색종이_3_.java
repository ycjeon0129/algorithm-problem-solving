package boj.gold._3_;
// ~

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2571_색종이_3_ {

    static int N, maxArea = 100;
    static boolean[][] confettis;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        confettis = new boolean[101][101];

        int X, Y;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            for (int x = X; x < X + 10; x++) {
                for (int y = Y; y < Y + 10; y++) {
                    confettis[y][x] = true;
                }
            }
        }

        getVertical();
        getHorizontal();
        System.out.println(maxArea);
    }

    static void getHorizontal() {
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100; i++) {
                if (confettis[i][j]) {
                    i = sizeOfHorizontal(i, j);
                }
            }
        }
    }

    static int sizeOfHorizontal(int i, int j) {
        int ni = i, nj = j, endPoint = 99, nextPivot = 0;
        boolean flag = false;
        while (confettis[ni][nj]) {
            while (ni < endPoint) {
                ni++;
                if (!confettis[ni][nj]) {
                    if (!flag) {
                        flag = true;
                        nextPivot = ni;
                    }
                    endPoint = ni;
                    ni = i;
                    maxArea = Math.max(maxArea, ((nj+1)-j) * ((endPoint)-i));
                    break;
                }

            }
            if (nj<99) {
                nj++;
            }
        }
        return nextPivot;
    }

    static void getVertical() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (confettis[i][j]) {
//                    int nextPivot = sizeOfVertical(i, j);
                    j = sizeOfVertical(i, j);
                }
            }
        }
    }

    static int sizeOfVertical(int i, int j) {
        int ni = i, nj = j, endPoint = 99, nextPivot = 0;
        boolean flag = false;
        while (confettis[ni][nj]) {
            while (nj < endPoint) {
                nj++;
                if (!confettis[ni][nj]) {
                    if (!flag) {
                        flag = true;
                        nextPivot = nj;
                    }
                    endPoint = nj;
                    nj = j;
                    maxArea = Math.max(maxArea, ((ni+1)-i) * ((endPoint)-j));
                    break;
                }

            }
            if (ni<99) {
                ni++;
            }
        }
        return nextPivot;
    }
}
