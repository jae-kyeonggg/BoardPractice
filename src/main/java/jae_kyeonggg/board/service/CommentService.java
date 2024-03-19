package jae_kyeonggg.board.service;

import jae_kyeonggg.board.domain.Comment;
import jae_kyeonggg.board.domain.User;
import jae_kyeonggg.board.domain.dto.info.WriteCommentInfo;
import jae_kyeonggg.board.repository.CommentRepository;
import jae_kyeonggg.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<Comment> getAllComments(Long postId) {
        return commentRepository.findAllByPostIdOrderByCreatedAtAsc(postId);
    }

    public Comment writeComment(WriteCommentInfo writeCommentInfo) {
        User user = userRepository.findById(writeCommentInfo.getUserId())
                .orElseThrow(IllegalArgumentException::new);
        return commentRepository.save(
                Comment.builder()
                        .postId(writeCommentInfo.getPostId())
                        .user(user)
                        .content(writeCommentInfo.getContent())
                        .build()
        );
    }
}
