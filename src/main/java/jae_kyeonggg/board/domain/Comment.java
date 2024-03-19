package jae_kyeonggg.board.domain;

import jae_kyeonggg.board.config.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity @Getter @Builder
@AllArgsConstructor
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @NotNull @Column(length = 300, columnDefinition = "LONGTEXT")
    private String content;
    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean reported;
    @ColumnDefault("0")
    private int commentLike;
    @ColumnDefault("0")
    private int commentDislike;
    @ColumnDefault("0")
    private int commentReportCount;
}
