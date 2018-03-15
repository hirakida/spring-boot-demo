package com.example.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.entity.Member;
import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyItemProcessor implements ItemProcessor<User, Member> {

    private final UserRepository userRepository;

    @Override
    public Member process(final User user) throws Exception {
        user.setEnabled(false);
        userRepository.save(user);

        Member member = new Member();
        BeanUtils.copyProperties(user, member, "id", "enabled");
        return member;
    }
}
