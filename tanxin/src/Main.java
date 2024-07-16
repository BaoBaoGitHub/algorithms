import java.util.Arrays;

public class Main {
    public int findContentChildren(int[] g, int[] s) {
        int res = 0;
        if (g == null || g.length == 0 || s == null || s.length == 0) {
            return res;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int index = 0;
        for (int i = 0; i < g.length; i++) {
            int weikou = g[i];
            for (int j = index; j < s.length; j++) {
                if (s[j] >= weikou) {
                    index = j + 1;
                    res++;
                    break;
                }
            }
        }

        return res;
    }
}
