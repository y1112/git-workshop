package com.org.admin.dto;

import com.org.admin.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name="test";
        int amount=1000;

        //when
        HelloResponseDto dto=new HelloResponseDto(name,amount);

        //then
        // assertThat: assertj라는 테스트 검증 라이브러리의 검증 메소드
        // 메소드 체이닝이 지원되어 메소드를 이어서 사용할 수 있음
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
