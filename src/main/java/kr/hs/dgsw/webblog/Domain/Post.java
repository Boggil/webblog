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

@Entity
@Data
@NoArgsConstructor
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
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