package AWS_Board.board.Dto;

import AWS_Board.board.Domain.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardEntity toEntity() {
        BoardEntity entity = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return entity;
    }

    @Builder
    public BoardDto(Long id, String writer, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
