package jae_kyeonggg.board.service;

import jae_kyeonggg.board.domain.Post;
import jae_kyeonggg.board.domain.dto.info.EditPostInfo;
import jae_kyeonggg.board.domain.dto.info.SavePostInfo;
import jae_kyeonggg.board.domain.dto.response.EditPostResponse;
import jae_kyeonggg.board.domain.dto.response.GetPostResponse;
import jae_kyeonggg.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(SavePostInfo savePostInfo) {
        Post savedPost = postRepository.save(Post.builder()
                .title(savePostInfo.getTitle())
                .content(savePostInfo.getContent())
                .writer(savePostInfo.getWriter())
                .build());
        return savedPost.getId();
    }

    @Transactional
    public EditPostResponse edit(Long postId, EditPostInfo editPostInfo) {
        Post foundPost = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        foundPost.edit(editPostInfo.getTitle(), editPostInfo.getContent());
        Post savedPost = postRepository.save(foundPost);
        EditPostResponse response = EditPostResponse.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .writer(savedPost.getWriter())
                .createdAt(savedPost.getCreatedAt())
                .updatedAt(savedPost.getUpdatedAt())
                .build();
        return response;
    }

    public GetPostResponse getDetail(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        postRepository.increaseViews(postId);
        GetPostResponse response = GetPostResponse.builder()
                .id(postId)
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .views(post.getViews())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
        return response;
    }

    public List<Post> getByWriter(String writer) {
        return postRepository.findByWriter(writer);
    }

    public List<Post> getByKeyword(String keyword) {
        return postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

    public List<Post> findAll(Boolean descending) {
        return descending ? postRepository.findAllByOrderByCreatedAtDesc() : postRepository.findAllByOrderByCreatedAtAsc();
    }
}
