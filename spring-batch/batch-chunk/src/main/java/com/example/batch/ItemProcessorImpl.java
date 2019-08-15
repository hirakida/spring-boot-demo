package com.example.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.core.Member;
import com.example.core.User;
import com.example.core.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ItemProcessorImpl implements ItemProcessor<User, Member> {
    private final UserRepository userRepository;

    @Nullable
    @Override
    public Member process(User user) throws Exception {
        log.info("process {}", user);
        user.setEnabled(false);
        userRepository.save(user);

        Member member = new Member();
        BeanUtils.copyProperties(user, member, "id", "enabled");
        return member;
    }
}
