package com.more.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정없이 이 어노테이션사용하면 H2 데이터베이스를 자동으로 실행해줌
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After  //JUnit 에서 단위 테스트 끝날 때마다 수행되는 메소드를 지정. 보통은 배포전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용함. 여러테스트가 동시에 수행되면 테스트 DB인 H2에 데이터가 그대로남아 다음 테스트 실패할 수 있음

    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title   = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("onemore@gmail.com").build());  //테이블 posts 에 insert/update 쿼리를 실행함. id 값이 있다면 update 없으면 insert 쿼리실행

        //when
        List<Posts> postsList = postsRepository.findAll();  //테이블 posts 에 있는 모든 데이터를 조회해오는 메소드

        //then
        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);




    }

}
