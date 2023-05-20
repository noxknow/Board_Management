package com.example.board.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp // 생성되었을 때 시간
    @Column(updatable = false) // 수정 시에 관여를 안하게끔
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(insertable = false) // 입력 시에 관여를 안하게끔
    private LocalDateTime updatedTime;
}
