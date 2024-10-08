package com.leftware.urltest.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UrlTesterController {

    private final String SITE_OK = "Site is OK";
    private final String SITE_DOWN = "Site is down";
    private final String SITE_ERROR = "Site is returning error code: {0}";
    private final String INCORRECT_URL = "URL is incorrect";

    @GetMapping("/urltest")
    public String getUrlStatusMessage(@RequestParam String url) {
        URI uri = URI.create(url);
        try {
            URL urlObj = uri.toURL();
            HttpURLConnection cn = (HttpURLConnection) urlObj.openConnection();
            cn.setRequestMethod("GET");
            cn.connect();

            int responseCode = cn.getResponseCode();
            if (responseCode < 400) {
                return SITE_OK;
            }

            return String.format(SITE_ERROR, responseCode);

        } catch (IllegalArgumentException | MalformedURLException e) {
            return INCORRECT_URL;
        } catch (IOException e) {
            return SITE_DOWN;
        }
    }
}
