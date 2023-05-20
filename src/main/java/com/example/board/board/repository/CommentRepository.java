package com.example.board.board.repository;

import com.example.board.board.entity.BoardEntity;
import com.example.board.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // select * from comment_table where board_id = ? order by id desc; 이 쿼리를 위해 repository에 메서드 정의
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
