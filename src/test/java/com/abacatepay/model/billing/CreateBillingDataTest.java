package com.abacatepay.model.billing;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateBillingDataTest {

    @Test
    void testBuilder() {
        BillingKind frequency = BillingKind.ONE_TIME;
        BillingMethod method = BillingMethod.PIX;
        CreateBillingProduct product = CreateBillingProduct.builder().build();
        String returnUrl = "http://example.com/return";
        String completionUrl = "http://example.com/completion";

        CreateBillingData billingData = CreateBillingData.builder()
                .frequency(frequency)
                .methods(Collections.singletonList(method))
                .products(Collections.singletonList(product))
                .returnUrl(returnUrl)
                .completionUrl(completionUrl)
                .build();

        assertEquals(frequency, billingData.getFrequency(), "Frequency must be set correctly");
        assertEquals(1, billingData.getMethods().size(), "Methods must be set correctly");
        assertEquals(method, billingData.getMethods().get(0), "Method must be set correctly");
        assertEquals(1, billingData.getProducts().size(), "Products must be set correctly");
        assertEquals(product, billingData.getProducts().get(0), "Product must be set correctly");
        assertEquals(returnUrl, billingData.getReturnUrl(), "Return URL must be set correctly");
        assertEquals(completionUrl, billingData.getCompletionUrl(), "Completion URL must be set correctly");
    }

    @Test
    void testSettersAndGetters() {
        CreateBillingData billingData = CreateBillingData.builder().build();
        BillingKind frequency = BillingKind.ONE_TIME;
        BillingMethod method = BillingMethod.PIX;
        CreateBillingProduct product = CreateBillingProduct.builder().build();
        String returnUrl = "http://example.com/return";
        String completionUrl = "http://example.com/completion";


        billingData.setFrequency(frequency);
        billingData.setMethods(Collections.singletonList(method));
        billingData.setProducts(Collections.singletonList(product));
        billingData.setReturnUrl(returnUrl);
        billingData.setCompletionUrl(completionUrl);

        assertEquals(frequency, billingData.getFrequency(), "Frequency must be set correctly");
        assertEquals(1, billingData.getMethods().size(), "Methods must be set correctly");
        assertEquals(method, billingData.getMethods().get(0), "Method must be set correctly");
        assertEquals(1, billingData.getProducts().size(), "Products must be set correctly");
        assertEquals(product, billingData.getProducts().get(0), "Product must be set correctly");
        assertEquals(returnUrl, billingData.getReturnUrl(), "Return URL must be set correctly");
        assertEquals(completionUrl, billingData.getCompletionUrl(), "Completion URL must be set correctly");
    }
}