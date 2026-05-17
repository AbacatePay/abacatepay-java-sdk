package com.abacatepay;

import com.abacatepay.clients.factories.AbacatePayClientFactory;
import com.abacatepay.model.AbacatePayBilling;
import com.abacatepay.model.IAbacatePay;
import com.abacatepay.model.IAbacatePayBilling;
import feign.RequestInterceptor;

public class AbacatePay implements IAbacatePay {

    private static final String API_BASE_URL = "https://api.abacatepay.com/v1";
    private static final String SDK_NAME = "abacatepay-java-sdk";
    private static final String SDK_VERSION = resolveVersion();

    private final String apiKey;
    private final String userAgent;
    private final IAbacatePayBilling billing;

    public AbacatePay(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("API key not provided");
        }

        this.apiKey = apiKey;
        this.userAgent = buildUserAgent();
        this.billing = new AbacatePayBilling(
                AbacatePayClientFactory.create(
                        API_BASE_URL,
                        requestInterceptor()
                )
        );
    }

    private RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("Authorization", "Bearer " + apiKey);
            template.header("Content-Type", "application/json");
            template.header("User-Agent", userAgent);
        };
    }

    @Override
    public IAbacatePayBilling billing() {
        return billing;
    }

    private static String resolveVersion() {
        Package pkg = AbacatePay.class.getPackage();
        String version = null;

        if (pkg != null) {
            version = pkg.getImplementationVersion();
        }

        if (version == null || version.isBlank()) {
            version = "dev";
        }

        return version;
    }

    private static String buildUserAgent() {
        return String.format(
                "%s/%s Java/%s (%s %s)",
                SDK_NAME,
                SDK_VERSION,
                System.getProperty("java.version"),
                System.getProperty("os.name"),
                System.getProperty("os.version")
        );
    }
}
