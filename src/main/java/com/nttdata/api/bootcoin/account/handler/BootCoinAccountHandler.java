package com.nttdata.api.bootcoin.account.handler;

import com.nttdata.api.bootcoin.account.document.BootCoinAccount;
import com.nttdata.api.bootcoin.account.repository.BootCoinAccountRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class BootCoinAccountHandler {

    private final BootCoinAccountRepository bootCoinAccountRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BootCoinAccountHandler.class);

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public Mono<ServerResponse> getAllAccount(ServerRequest serverRequest) {
        return  ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(bootCoinAccountRepository.findAll().log(), BootCoinAccount.class);
    }

    public Mono<ServerResponse> getAccount(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");

        return bootCoinAccountRepository.findById(id)
                .flatMap(i -> ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(i, BootCoinAccount.class)).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        var account = serverRequest.bodyToMono(BootCoinAccount.class);

        return  account.flatMap(c -> {
                    return ServerResponse.status(HttpStatus.CREATED)
                            .contentType(MediaType.TEXT_EVENT_STREAM)
                            .body(bootCoinAccountRepository.save(c), BootCoinAccount.class);
                });
    }

    public Mono<ServerResponse> edit(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BootCoinAccount.class).flatMap(v ->
             bootCoinAccountRepository.findById(v.getId()).flatMap(c -> {
                 c.setPhone(v.getPhone());
                 c.setEmail(v.getEmail());
                 c.setImei(v.getImei());
                 c.setBalance(v.getBalance());
                 c.setCardNumber(v.getCardNumber());
                 return ServerResponse.status(HttpStatus.CREATED)
                         .contentType(MediaType.TEXT_EVENT_STREAM)
                         .body(bootCoinAccountRepository.save(c), BootCoinAccount.class);
             }).switchIfEmpty(notFound));
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");
        return bootCoinAccountRepository.findById(id)
                .flatMap(c -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(bootCoinAccountRepository.delete(c), Void.class))
                .switchIfEmpty(notFound);
    }


}
