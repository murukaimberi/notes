import java.util.*;

public class LongestSubstring {

    public static int findLongestSubstring(String str) {
        if (str != null && str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int windowStart = 0, longest = 0, windowLength = 0, i = 0;

        Hashtable<Character, Integer> map = new Hashtable<Character, Integer>();

        for (i = 0; i < n; i++) {
            if (!map.containKey(str.charAt(i))) {
                map.put(str.charAt(i), i);
            } else {
                if (map.get(str.charAt(i)) >= windowStart) {
                    windowLength = i - windowStart;
                    if (longest < windowLength) {
                        longest = windowLength;
                    }
                    windowStart = map.replace(str.charAt(i), i);
                }
            }
        }

        if (longest < i - windowStart) {
            longest = i - windowStart;
        }

        return longest;
    }
}