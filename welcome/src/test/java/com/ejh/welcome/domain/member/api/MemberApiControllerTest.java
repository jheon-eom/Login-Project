package com.ejh.welcome.domain.member.api;

import com.ejh.welcome.domain.member.application.MemberService;
import com.ejh.welcome.domain.member.dto.JoinRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class MemberApiControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("회원가입 테스트")
    void create() throws Exception {

        // 가입 내역
        JoinRequest joinMemDTO = JoinRequest.builder()
                .email("e4033jh@daum.net")
                .password("12345678a")
                .name("eomjh")
                .nickname("eomjh")
                .birth(LocalDate.of(1995, 6, 17))
                .build();

        String body = objectMapper.writeValueAsString(joinMemDTO);

    }

}
