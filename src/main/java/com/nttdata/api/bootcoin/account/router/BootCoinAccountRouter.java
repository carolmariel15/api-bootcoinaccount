package com.nttdata.api.bootcoin.account.router;

import com.nttdata.api.bootcoin.account.handler.BootCoinAccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BootCoinAccountRouter {

    @Bean
    public RouterFunction<ServerResponse> bootCoinAccountRouterFunc(BootCoinAccountHandler bootCoinAccountHandler) {
        return RouterFunctions.route(GET("/bcaccount").and(accept(MediaType.TEXT_EVENT_STREAM)), bootCoinAccountHandler::getAllAccount)
                .andRoute(GET("/bcaccount/{id}").and(accept(MediaType.TEXT_EVENT_STREAM)), bootCoinAccountHandler::getAccount)
                .andRoute(POST("/bcaccount").and(accept(MediaType.TEXT_EVENT_STREAM)), bootCoinAccountHandler::create)
                .andRoute(PUT("/bcaccount").and(accept(MediaType.TEXT_EVENT_STREAM)), bootCoinAccountHandler::edit)
                .andRoute(DELETE("/bcaccount/{id}").and(accept(MediaType.TEXT_EVENT_STREAM)), bootCoinAccountHandler::delete);
    }

}
