package com.alevat.cabbage.account;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import me.ccampo.spring.aws.lambda.SpringRequestHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringHandler extends SpringRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final ApplicationContext context =
            new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

}
