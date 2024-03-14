package jae_kyeonggg.board;

import jae_kyeonggg.board.domain.dto.info.SavePostInfo;
import jae_kyeonggg.board.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardApplicationTest {

    @Autowired
    PostService postService;

    @Test
    void createData() {
        for (int i = 1; i <= 100; i++) {
            postService.save(new SavePostInfo(String.format("titleEx%03d", i), "contentEx", "writerEx", 1L));
        }
    }

}