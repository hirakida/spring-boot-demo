package com.example.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.entity.Member;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyItemWriter implements ItemWriter<Member> {

    private final MemberRepository memberRepository;

    @Override
    public void write(List<? extends Member> members) throws Exception {
        log.info("write {}", members);
        memberRepository.save(members);
    }
}
