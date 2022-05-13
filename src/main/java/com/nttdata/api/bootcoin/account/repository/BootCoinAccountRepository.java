package com.nttdata.api.bootcoin.account.repository;

import com.nttdata.api.bootcoin.account.document.BootCoinAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BootCoinAccountRepository extends ReactiveMongoRepository<BootCoinAccount, String> {
}
