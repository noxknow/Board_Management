package com.example.board.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String OriginalFileName;

    @Column
    private String storedFileName;

    // BoardEntity와 다대일 관계 Egear은 부모를 조회할 때 자식을 다 가져온다. LAZY는 필요한 부분만
    @ManyToOne(fetch = FetchType.LAZY)
    // 컬럼이름을 지정해준다. sql에서는 board_id에 bigint형으로 들어가게 된다.
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(boardEntity);
        return boardFileEntity;
    }
}
