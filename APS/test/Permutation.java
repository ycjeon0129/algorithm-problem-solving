package ssafy;

/*
    순열 (Permutation)
    서로 다른 것들 중 몇개를 뽑아서 한줄로 나열하는 것
    서로 다른 n개 중 r개를 택한다.
 */
public class Permutation {
    static int N;
    static int M;
    static int[] result;
    static boolean[] visited;

    public static void main(String[] args) {
        N = 6;
        M = 3;

        result = new int[M];
        visited = new boolean[N];
        BitPermu(0, 0);
    }

    // 중복이 없는 순열
    private static void permutation(int cnt) {
        if (cnt == M) {
            print();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                result[cnt] = i + 1;
                visited[i] = true;
                permutation(cnt + 1);
                visited[i] = false;
            }
        }
    }

    // 중복 있는
    private static void DuplePermutation(int cnt) {
        if (cnt == M) {
            print();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                result[cnt] = i + 1;
                visited[i] = true;
                DuplePermutation(cnt + 1);
                visited[i] = false;
            }
        }
    }

    private static void print() {
        for (int i = 0; i < M; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    private static void BitPermu(int cnt, int flag) {
        if (cnt == M) {
            print();
            return;
        }
        for (int i = 0; i < N; i++) {
            if ((flag & 1 << i) != 0) continue;
            result[cnt] = i;
            BitPermu(cnt + 1, flag | 1 << i);
        }
    }
}

