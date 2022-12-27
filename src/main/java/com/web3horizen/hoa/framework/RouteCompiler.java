package com.web3horizen.hoa.framework;

public class RouteCompiler {
    public static String compile(String path) {
        if (!path.endsWith("/")) {
            path += "/";
        }

        if (path.endsWith("/")) {
            path += "?";
        }

        String re = "^" + path + "$";
        return re.replaceAll("@\\{([^}|$]+)\\}", "(?<$1>.+)");
    }
}
