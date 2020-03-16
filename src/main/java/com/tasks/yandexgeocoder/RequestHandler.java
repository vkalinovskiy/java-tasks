package com.tasks.yandexgeocoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    protected String baseUrl;
    protected Map<String, String> getParams = new HashMap<>();

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void addGetParam(String name, String value) {
        getParams.put(name, value);
    }

    protected URL getRequestUrl() throws UnsupportedEncodingException, MalformedURLException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : getParams.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String queryString = result.toString();
        queryString = queryString.length() > 0 ? queryString.substring(0, queryString.length() - 1) : queryString;
        String url = baseUrl + "?" + queryString;

        return new URL(url);
    }
}

