package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class CleanupIdiom {

    static class NeedsCleanup {
        private static long counter = 1;
        private final long id = counter++;
        public void dispose() {
            System.out.println("NeedsCleanup " + id + " disposed");
        }
    }

    static class ConstructionException extends Exception {}

    static class NeedsCleanup2 extends NeedsCleanup {
        public NeedsCleanup2() throws ConstructionException {}
    }

    public static void main(String[] args) {

        // Section 1:
        NeedsCleanup nc1 = new NeedsCleanup();
        try {
            // ...
        } finally {
           nc1.dispose();
        }

        // Section 2:
        NeedsCleanup nc2 = new NeedsCleanup();
        NeedsCleanup nc3 = new NeedsCleanup();
        try {
            // ...
        } finally {
            nc3.dispose();
            nc2.dispose();
        }

        // Section 3:
        try {
            NeedsCleanup2 nc4 = new NeedsCleanup2();
            try {
                NeedsCleanup2 nc5 = new NeedsCleanup2();
                try {
                    // ...
                } finally {
                    nc5.dispose();
                }
            } catch (ConstructionException e) {
                System.out.println(e);
            } finally {
                nc4.dispose();
            }
        } catch (ConstructionException e) {
            System.out.println(e);
        }
    }
}
