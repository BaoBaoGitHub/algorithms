public class Main {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }

        int left = 0, right = s.length - 1;
        while (left < right) {
            char t = s[left];
            s[left] = s[right];
            s[right] = t;

            left++;
            right--;
        }
    }

    public String reverseStr(String s, int k) {
        char[] sCharArray = s.toCharArray();
        for (int i = 0; i * 2 * k < s.length(); i++) {
            // 反转 [2ik,2ik+k)
            reverse(sCharArray, 2 * i * k, 2 * i * k + k);
        }
        return new String(sCharArray);
    }

    private static void reverse(char[] sCharArray, int begin, int end) {
        end = Math.min(end, sCharArray.length) - 1;
        if (begin >= sCharArray.length) {
            return;
        }

        while (begin < end) {
            char t = sCharArray[begin];
            sCharArray[begin] = sCharArray[end];
            sCharArray[end] = t;

            begin++;
            end--;
        }
    }

    public static String replaceNumber(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                cnt++;
            }
        }

        char[] newChars = new char[s.length() + 5 * cnt];
        for (int i = 0; i < s.length(); i++) {
            newChars[i] = s.charAt(i);
        }

        int oldIndex = s.length() - 1;
        int newIndex = newChars.length - 1;
        while (oldIndex < newIndex) {
            if (newChars[oldIndex] >= '0' && newChars[oldIndex] <= '9') {
                // newIndex 填充 number 字符
                for (int i = 0; i < "number".length(); i++) {
                    newChars[newIndex - i] = "rebmun".charAt(i);
                }
                newIndex -= 6;
            } else {
                newChars[newIndex] = newChars[oldIndex];
                newIndex--;
            }
            oldIndex--;
        }

        return new String(newChars);
    }

    public void rightRotate(char[] chars, int k) {
        reverseChars(chars, 0, chars.length - 1);
        reverseChars(chars, 0, k - 1);
        reverseChars(chars, k, chars.length - 1);
    }

    public void reverseChars(char[] chars, int left, int right) {
        while (left < right) {
            char t = chars[left];
            chars[left] = chars[right];
            chars[right] = t;

            left++;
            right--;
        }
    }

    public boolean repeatedSubstringPattern(String s) {
        // 假设 s 可以由s'组成，那么 1. s 的长度一定是 s'的倍数，2. s'一定是 s 的前缀
        for (int i = 1; i < s.length(); i++) {
            String substring = s.substring(0, i);
            if (s.length() % substring.length() != 0) {
                continue;
            }
            int j = substring.length();
            for (; j < s.length(); j += substring.length()) {
                String ss = s.substring(j, j + substring.length());
                if(!ss.equals(substring)){
                    break;
                }
                // 匹配到头说明找到一个结果
                // 匹配到一半匹配不上，说明不行
            }
            if(j>=s.length()){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.replaceNumber("a1b2c3"));
    }
}
