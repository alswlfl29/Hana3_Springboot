package com.study.Ex12H2DB;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class MemberRepositoryTest extends Ex12H2DbApplicationTests{
    // test 코드에서는 생성자 주입 안됨.
    @Autowired
    MemberRepository memberRepository;

    @BeforeAll // static 메소드
    static void setup(){
        System.out.println("@BeforeAll - 클래스를 초기화시 한 번 수행");
    }

    @BeforeEach // non-static 메소드
    void init(){
        System.out.println("@BeforeEach - @Test 메소드를 호출시마다 한 번 수행");
        save();
    }

    @Test // 테스트할 메소드 사용
    @DisplayName("save 테스트") // 콘솔에 출력되는 메소드 이름
    public void save(){
        memberRepository.save(new MemberEntity(Long.valueOf(1),
                "hong","1234","홍길동","ROLE_USER", LocalDate.parse("2023-01-01")));
        memberRepository.save(new MemberEntity(Long.valueOf(2),
                "tom","1234","톰아저씨","ROLE_USER", LocalDate.parse("2023-02-01")));
        memberRepository.save(new MemberEntity(Long.valueOf(3),
                "admin","1234","관리자","ROLE_ADMIN", LocalDate.parse("2023-03-01")));

        List<MemberEntity> list = memberRepository.findAll();
        for(MemberEntity m : list){
            System.out.println(m.getUserName());
        }
        // 단정함수 (Assert Function)
        // assertEquals(3,list.size()); // 테스트 성공
        // assertEquals(4,list.size()); // 테스트 실패
    }

    @Test
    @DisplayName("findById 테스트")
    public void findById(){
        // Optional 클래스: JDK 9부터 제공, null safety 를 제공한다.
        Optional<MemberEntity> member = memberRepository.findById(1L);
        member.ifPresent(unwrappedMemberEntity -> {
            // null이 아닐 때 수행되는 람다식
            System.out.println(unwrappedMemberEntity.getUserName());
            assertEquals("홍길동",unwrappedMemberEntity.getUserName());
        });
        if(member.isPresent()){
            String name = member.get().getUserName();
            assertEquals("홍길동",name);
        }else{
            fail("member 값 가져오기 실패");
        }
    }


    @Test
    @DisplayName("count 테스트")
    public void count(){
        // SQL: select count(*) from member;
        Long count = memberRepository.count();
        System.out.println("count: "+count);
        assertEquals(3,count);
    }
    @Test
    @DisplayName("쿼리메소드 테스트")
    public void query(){
        // 패턴: find + By + 필드이름 + And + 필드이름
        List<MemberEntity> list =
                memberRepository.findByUserId("tom");

        assertEquals(1,list.size());
        assertEquals("톰아저씨",list.get(0).getUserName());

        List<MemberEntity> list2 =
                // SQL: select * from member where user_id=:userId
                //      and user_name=:userName order by id desc limit 0;
                memberRepository.findFirst5ByUserIdAndUserNameOrderByIdDesc(
                        "hong","홍길동"
                );
        assertEquals(1,list2.size());

        Boolean isExist
                = memberRepository.existsByJoindateLessThanEqual(LocalDate.now());
        System.out.println("isExist: "+isExist);

        long count = memberRepository.countByUserNameIgnoreCaseLike("길");
        System.out.println("count: "+count);
    }
    @Test
    @DisplayName("JPQL 테스트")
    public void jpqlQuery(){
        List<MemberEntity> list =
                memberRepository.findByUserId_JPQL_Query("tom");
        assertEquals(1, list.size());
        assertEquals("톰아저씨",list.get(0).getUserName());
    }

    @Test
    @DisplayName("Native Query 테스트")
    public void nativeQuery(){
        List<MemberEntity> list =
                memberRepository.findByUserId_nativeQuery("admin");
        assertEquals(1,list.size());
        assertEquals("관리자",list.get(0).getUserName());
    }

    @Test
    @DisplayName("update native SQL 테스트")
    public void update_nativeSql(){
        int result = memberRepository.updateById_nativeQuery(1L, "hong3");
        assertEquals(1, result);

        Optional<MemberEntity> member =
                memberRepository.findById(1L);
        assertEquals("hong3", member.get().getUserId() );
    }

    @Test
    @DisplayName("delete 테스트")
    public void delete(){
        Optional<MemberEntity> member = memberRepository.findById(1L);
        member.ifPresent(unwrapMember->{
            memberRepository.delete(unwrapMember);
            System.out.println("삭제 성공");

            Optional<MemberEntity> oldMember = memberRepository.findById(1L);;
            assertFalse(oldMember.isPresent());
        });

    }

    // 연습문제
    // 1. member 테이블 안에 암호가 "1234"인 회원이 있는지 테스트 하시오.

    @Test
    @DisplayName("문제1")
    public void pr01(){
        boolean isExists = memberRepository.existsByUserPw("1234");
        assertTrue(isExists);
    }
    // 2. 23년도 3월에 가입한 회원의 수가 1인지 테스트하시오.
    @Test
    @DisplayName("문제2")
    public void pr02(){
      LocalDate start = LocalDate.of(2023,3,1);
      LocalDate end = LocalDate.of(2023,3,31);
      boolean isExists = memberRepository.existsByJoindateBetween(start, end);
      assertTrue(isExists);
    }

    // 3. "lee"라는 아이디로 회원가입하고자 할때, 아이디 중복인지 테스트하시오.
    @Test
    @DisplayName("문제3")
    public void pr03(){
        boolean isExists = memberRepository.existsByUserId("lee");
        assertFalse(isExists);
    }
    // 4. "tom"이라는 아이디의 회원정보를 수정하고, 잘 수정되었는지 테스트하시오.
    //     톰아저씨 -> 마이클, 암호 -> 3456
    // 쿼리 메소드 또는 Native SQL, JPQL 방법 중 하나를 사용하시오.
    @Test
    @DisplayName("문제4")
    public void pr04(){
        int result = memberRepository.updateByUserId_nativeQuery("tom", "마이클","3456");
        assertEquals(1, result);
        List<MemberEntity> list =
                memberRepository.findByUserId("tom");
        if(list != null && !list.isEmpty()){
            assertEquals("마이클",list.get(0).getUserName());
            assertEquals("3456",list.get(0).getUserPw());
        }else{
            System.out.println("null 이거나 size 0 입니다.");
        }

    }
}
