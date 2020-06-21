package com.example;

import static com.microsoft.azure.functions.HttpMethod.GET;
import static com.microsoft.azure.functions.HttpMethod.POST;
import static com.microsoft.azure.functions.annotation.AuthorizationLevel.ANONYMOUS;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class UppercaseHandler extends AzureSpringBootRequestHandler<String, String> {

    @FunctionName("uppercase")
    public String execute(@HttpTrigger(name = "req", methods = { GET, POST }, authLevel = ANONYMOUS)
                                  HttpRequestMessage<String> request,
                          ExecutionContext context) {
        return handleRequest(request.getBody(), context);
    }
}
