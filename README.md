# 🥑 **AbacatePay _Java_ SDK**

Welcome to the official [AbacatePay](https://github.com/AbacatePay) Java SDK repository! This project was created to facilitate integration with the AbacatePay API, providing a robust, modular and easy-to-use SDK. 🍃
<div align="center">
  <img src="https://cdn.discordapp.com/attachments/1133480741876019206/1332413972103565322/OIG2.UhRqOqltmix.jpeg?ex=67952ab6&is=6793d936&hm=23ea047d3577a6bd0e5476739e736b6b9f3276406a6a6538f386e62e82467e75" alt="Imagem" width="300" />
</div>

## 📋 **Table of Contents**

1. [📖Introduction](#Introduction)
	- [📁 Structure of the Repository](#repository-structure)
	- [📂 Main Folders](#main-folders)
2. [🛠️ Components](#components)
	- [📂 `main` package](#main-package)
	- [📂 `billing` package](#billing-package)
	- [📂 `clients` package](#clients-package)
	- [📠 Installation](#installation)
3. [💻 Code Example](#code-example)

----------

##Introduction

The AbacatePay Java SDK is designed for developers who want to integrate the 🥑AbacatePay API into their Java projects ☕. It provides classes and methods to facilitate authentication, handling charges, and communicating with the API in an intuitive and standardized way.

----------

### **Repository Structure**

- **Package Director:**
Contains the core of the SDK, including the main modules such as `clients`, `model` and the main class `AbacatePay.java`.

**Location:**
[`src/main/java/com/abacatepay`](https://github.com/DaviJoseMach/abacatepay-java-sdk/tree/main/src/main/java/com/abacatepay)

- **Test Package:**
Contains the automated tests to validate the operation of the SDK functions.

**Location:**
[`src/test/java/com/abacatepay`](https://github.com/DaviJoseMach/abacatepay-java-sdk/tree/main/src/test/java/com/abacatepay)

----------

### **Main Packages**

#### **`main` package**

- [`clients`](https://github.com/DaviJoseMach/abacatepay-java-sdk/tree/main/src/main/java/com/abacatepay/clients):
Defines interfaces for communicating with an API.

- [`model`](https://github.com/DaviJoseMach/abacatepay-java-sdk/tree/main/src/main/java/com/abacatepay/model):
Contains the data models for requests and responses.

- [`AbacatePay.java`](https://github.com/DaviJoseMach/abacatepay-java-sdk/blob/main/src/main/java/com/abacatepay/AbacatePay.java):
The main class that manages authentication and API calls.

#### **`billing` package**

Scripts related to billing, such as creation and listing.

- **Example:** Structures for billing data (e.g. `CreateBillingData`) and API responses (e.g. `CreateBillingResponse`).

#### **`clients` package**

Define interfaces that represent API routes. Example:

```java
@RequestLine("POST /billing/create")
CreateBillingResponse create(body CreateBillingData);

```
Here's what you need to install for it to work properly:

### Installation

1. **Java**: Make sure you have JDK 8 or higher installed on your system.

2. **Maven**: You need Maven to manage dependencies and build the project. If you don't have it, install it with the command:

```bash
sudo apt install maven

```

3. **SDK Dependencies**: This SDK uses Feign for HTTP requests. Add the Feign dependency to your `pom.xml`:

```xml
<dependency>
<groupId>io.github.openfeign</groupId>
<artifactId>feign-core</artifactId>
<version>11.7</version>
</dependency>

```

4. **API Configuration**: You need the AvocadoPay API key. - make sure you have it and make sure to pass it as a parameter when instantiating the `AbacatePay` class:

```java
AbacatePay avocadoPay = new AbacatePay("YOUR_API_KEY");

```

## **Code Example**

Below, we explain the main points of the SDK code example:

###Code:

```java
package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.clients.factories.AbacatePayClientFactory;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import feign.RequestInterceptor;

public class AvocadoPay {

private static final String API_BASE_URL = "https://api.abacatepay.com/v1";

private final AvocadoPayClient client;

public AvocadoPay(String apiKey) {
if (apiKey == null || apiKey.isEmpty()) {
throw new IllegalArgumentException("API Key not provided");

}
this.client = AvocadoPayClientFactory.create(API_BASE_URL, requestInterceptor(apiKey));

}
private RequestInterceptor requestInterceptor(String apiKey) {
return template -> {
template.header("Authorization ", "Cardholder " + apiKey);
template.header("Content-Type", "application/json");

};

public CreateBillingResponse reateBilling(CreateBillingData data) {
return client.create(data);
}

public ListBillingResponse listBillings() {
return client.list();
}
}

```

### **Code Explanation:**

1. **Base URL Configuration:**

- The API URL (`https://api.abacatepay.com/v1`) is stored in `API_BASE_URL` for easy reuse.

2. **Authentication and Headers:**

- A `RequestInterceptor` automatically adds `Authorization` (with the API key), `Content-Type` and other headers to each request. 3. **AbacatePay Client (`AbacatePayClient`):**

- Created from a factory (`AbacatePayClientFactory`), provides methods for calls such as `create` and `list`.

4. **Creating a Bill (`createBilling`):**

- Sends the billing data (`CreateBillingData`) and returns the response.

5. **Listing Billings (`listBillings`):**

- Makes a request to list billings.
