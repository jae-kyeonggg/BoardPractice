package jae_kyeonggg.board.domain;

import jae_kyeonggg.board.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity @Getter @Builder
@AllArgsConstructor
@Table(name = "posts")
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    private String content;
    private String writer;
    @ColumnDefault("0")
    private int views;
    private Long userId;
    @ColumnDefault("0")
    private int likes;
    @ColumnDefault("0")
    private int dislikes;

    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
