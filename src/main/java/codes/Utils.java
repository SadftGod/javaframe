package codes;

public class Utils {
    public static String joinPaths(String base, String path) {
        if (base.endsWith("/")) {
            base = base.substring(0, base.length() - 1);
        }

        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        return base + "/" + path;
    }
}
