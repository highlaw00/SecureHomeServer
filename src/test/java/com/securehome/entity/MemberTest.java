package com.securehome.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {
    @Test
    @DisplayName("사용자 생성 단위 테스트")
    void createUser() {
        Member member = new Member();
        member.setName("Choi Yool");
        Assertions.assertThat(member.getName()).isEqualTo("Choi Yool");
    }
}
