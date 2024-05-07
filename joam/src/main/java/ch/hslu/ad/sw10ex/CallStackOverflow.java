package ch.hslu.ad.sw10ex;

/**
 * Test the Call Stack depth.
 * The default stack size varies by JVM implementation, but it’s typically around 1MB for a standard JVM.
 * The stack size can be increased by specifying the java VM option -Xss (Example 2MB: -Xss2m)
 */
@SuppressWarnings("ALL")
public class CallStackOverflow {
    private static int depth = 0;

    static void recursiveCall() {
        depth++;
        recursiveCall();
    }

    public static void main(String[] args) {
        try {
            recursiveCall();
        } catch (StackOverflowError e) {
            System.out.printf("Call Stack depth was %s when StackOverflowError was thrown.", depth);
        }
    }
}
