package com.securehome.repository;

import com.securehome.TestContainerSupport;
import com.securehome.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest extends TestContainerSupport {

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    private final String userName = "test1";

    @Test
    void memberSaveTest() {
        Member member = new Member();
        member.setName(userName);
        member = memberRepository.save(member);
        Assertions.assertThat(member.getId()).isNotNull();
    }

    @Test
    void memberFindByName() {
        Member member = new Member();
        member.setName(userName);
        member = memberRepository.save(member);
        em.flush();
        em.clear();
        List<Member> members = memberRepository.findByName(userName);
        Member findMember = members.get(0);
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
    }
}
