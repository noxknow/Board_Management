package com.example.board.board.repository;

import com.example.board.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set board_hits = board_hits+1 where id=?
    // nativeQuery = true라는 옵션을 주게되면 실제 DB에서 사용되는 쿼리를 쓸 수 있다.
    // BoardEntity 이 자리에는 보통 테이블을 쓰게 되지만, 이번엔 Entity로 정의한 쿼리를 사용한다.
    // (그래서 db의 컬럼이 아닌 Entity의 컬럼 이름을 써야한다.)
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
