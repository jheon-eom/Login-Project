package com.ejh.welcome.domain.member.api;

import com.ejh.welcome.domain.member.WithAuthUser;
import com.ejh.welcome.domain.member.application.MemberService;
import com.ejh.welcome.domain.member.dto.JoinRequest;
import com.ejh.welcome.domain.member.dto.MemberResponse;
import com.ejh.welcome.domain.member.dto.MemberUpdateDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.ejh.welcome.domain.member.util.GivenMember.toEntityNoEncoder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        when(memberService.signUp(any())).thenReturn("e4033jh@daum.net");

        mockMvc.perform(post("/members").content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("member/created",
                        requestFields(
                                fieldWithPath("email").description("회원 이메일"),
                                fieldWithPath("password").description("회원 비밀번호"),
                                fieldWithPath("name").description("회원 이름"),
                                fieldWithPath("birth").description("회원 생년월일"),
                                fieldWithPath("nickname").description("회원 별칭")
                        )))
                .andDo(print());
    }

    @Test
    @DisplayName("회원조회 By Email")
    @WithAuthUser
    void findByEmail() throws Exception {
        when(memberService.findByEmail(any())).thenReturn(MemberResponse.from(toEntityNoEncoder()));

        mockMvc.perform(get("/members/email")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("member/findOne",
                        responseFields(
                                fieldWithPath("email").description("회원 이메일"),
                                fieldWithPath("name").description("회원 이름"),
                                fieldWithPath("nickname").description("회원 별칭"),
                                fieldWithPath("age").description("회원 나이")
                        )))
                .andDo(print());
    }

    @Test
    @DisplayName("회원정보 수정")
    @WithAuthUser
    void update() throws Exception {
        MemberUpdateDTO updateDTO = MemberUpdateDTO.builder()
                .email("e4033jh@daum.net")
                .password("123123aaa")
                .nickname("jheom")
                .build();

        String body = objectMapper.writeValueAsString(updateDTO);

        mockMvc.perform(patch("/members").content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("member/update",
                        requestFields(
                                fieldWithPath("email").description("회원 이메일"),
                                fieldWithPath("password").description("회원 비밀번호"),
                                fieldWithPath("nickname").description("회원 별칭")
                        )))
                .andDo(print());
    }

    @Test
    @DisplayName("회원 탈퇴")
    @WithAuthUser
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/members"))
                .andExpect(status().isNoContent())
                .andDo(document("member/delete"))
                .andDo(print());
    }

}
