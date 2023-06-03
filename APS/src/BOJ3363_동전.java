import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ3363_동전 {

    static Set<Integer> light, heavy;
    static int[] left, right;
    static char op;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        light = new HashSet<Integer>();
        heavy = new HashSet<Integer>();
        left = new int[4];
        right = new int[4];

        for (int i = 0; i < 12; i++) {
            light.add(i);
            heavy.add(i);
        }

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                left[i] = Integer.parseInt(st.nextToken());
            }
            op = st.nextToken().charAt(0);
            for (int j = 0; j < 4; j++) {
                right[i] = Integer.parseInt(st.nextToken());
            }
            //
            if (op == ' ') {

            }
        }


    }
}
