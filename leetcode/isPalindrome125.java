public class isPalindrome125 {
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1)
            return true;

        int left = -1, right = s.length();
        s = s.toLowerCase();

        while (left < right) {
            do {
                left++;
            } while (left < s.length() && !isAlphanumeric(s.charAt(left)));
            if (left >= s.length())
                return false;
            do {
                right--;
            } while (right > 0 && !isAlphanumeric(s.charAt(right)));
            if (right <= 0)
                return false;
            if (s.charAt(left) != s.charAt(right))
                return false;
        }
        return true;
    }

    private boolean isAlphanumeric(char c) {
        if (c >= 'a' && c <= 'z')
            return true;
        if (c >= 'A' && c <= 'Z')
            return true;
        if (c >= '0' && c <= '9')
            return true;
        return false;
    }

    public static void main(String[] args) {

        System.out.println(new isPalindrome125().isPalindrome("A man, a plan, acanal:Panama"));
        System.out.println(new isPalindrome125().isPalindrome("race a car"));
        System.out.println(new isPalindrome125().isPalindrome(" "));
        System.out.println(new isPalindrome125().isPalindrome(".,"));
    }
}