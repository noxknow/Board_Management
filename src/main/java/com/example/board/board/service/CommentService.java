package com.example.board.board.service;

import com.example.board.board.dto.CommentDTO;
import com.example.board.board.entity.BoardEntity;
import com.example.board.board.entity.CommentEntity;
import com.example.board.board.repository.BoardRepository;
import com.example.board.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        // 부모 엔티티 (BoardEntity) 조회
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            // Entity객체는 DB를 다루는 곳이기 때문에 Entity를 보호하기 위해 객체를 만들지않고 메서드를 바로 부르는것이다. (이렇게 안하고
            // Builder를 쓰기도 한다.)
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    @Transactional
    public List<CommentDTO> findAll(Long boardId) {
        // select * from comment_table where board_id = ? order by id desc; 이 쿼리를 위해 repository에 메서드 정의
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        // entity리스트 DTO리스트로 변환
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
