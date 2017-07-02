package com.example.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.domain.Account;

@Component
public class MyItemProcessor implements ItemProcessor<Account, Account> {
    @Override
    public Account process(final Account account) throws Exception {
        Account newAccount = new Account();
        BeanUtils.copyProperties(account, newAccount);
        newAccount.setName(StringUtils.replace(account.getName(), "user", "account"));
        return newAccount;
    }
}
