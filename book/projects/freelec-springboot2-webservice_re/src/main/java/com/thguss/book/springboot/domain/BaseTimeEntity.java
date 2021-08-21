package com.thguss.book.springboot.domain;

import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 이 클래스 상속할 경우 필드들도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 포함 (자동생성)
public abstract class BaseTimeEntity { // 모든 Entity의 상위 클래스
    
    @CreatedDate // 저장될 때 시간
    private LocalDateTime createdDate;
    
    @LastModifiedDate // 변경할 때의 시간
    private LocalDateTime modifiedDate;
}
