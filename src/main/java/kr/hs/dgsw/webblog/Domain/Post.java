package kr.hs.dgsw.webblog.Domain;

import java.time.LocalDateTime;
import java.util.List;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Table로 매핑
@Data // @Getter @Setter @EqualsAndHashCode @AllArgsConstructor 을 포함한 Lombok에서 제공하는 필드와 관련된 모든 코드를 생성함
@NoArgsConstructor  // 빈 매개변수의 생성자를 만들어줌
public class Post {
  @Id // 기본키 설정
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 DBMS에 위임한다.
  private Long id;
  @Column(nullable = false)  // nullable: NULL 허용, unique: 고유값, length: 글자 수
  private Long userId;
  @Column(nullable = false)
  private String title;
  // LOB BLOB CLOB
  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)  // 여러 개의 값이 들어갈 수 있음. (1:N)
  private List<Attachment> pictures;
  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime created;
  @UpdateTimestamp
  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime modified;

  public Post(Long userId, String title, String content) {
    this.userId = userId;
    this.title = title;
    this.content = content;
  }
}