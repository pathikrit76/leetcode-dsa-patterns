import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] sFreq = new int[26];
        int[] pFreq = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pFreq[p.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            sFreq[s.charAt(i) - 'a']++;

            if (i >= p.length()) {
                sFreq[s.charAt(i - p.length()) - 'a']--;
            }

            if (Arrays.equals(sFreq, pFreq)) {
                res.add(i - p.length() + 1);
            }
        }

        return res;
    }
}
