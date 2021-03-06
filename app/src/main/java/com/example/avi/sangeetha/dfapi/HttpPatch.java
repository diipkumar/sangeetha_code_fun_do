package com.example.avi.sangeetha.dfapi;


import org.apache.http.client.methods.HttpPost;

/**
 * Creates a PATCH method for Apache
 */
public class HttpPatch extends HttpPost {
    public static final String METHOD_PATCH = "PATCH";

    public HttpPatch(final String url) {
        super(url);
    }

    @Override
    public String getMethod() {
        return METHOD_PATCH;
    }
}