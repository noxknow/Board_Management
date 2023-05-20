package com.example.board.board.dto;

import com.example.board.board.entity.CommentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId()); // 자식한테있는 부모 entity에서 id를 꺼내온단 의미.
        // 만약에 매개변수로 boardId를 받아온다면 commentDTO.setBoardId(boardId); 이렇게도 가능 (CommentEntity에선 이렇게 함)
        return commentDTO;
    }
}
