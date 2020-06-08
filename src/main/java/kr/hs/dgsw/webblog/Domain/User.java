package kr.hs.dgsw.webblog.Domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity // Table로 매핑
@Data // @Getter @Setter @EqualsAndHashCode @AllArgsConstructor 을 포함한 Lombok에서 제공하는 필드와 관련된 모든 코드를 생성함
@NoArgsConstructor  // 빈 매개변수의 생성자를 만들어줌
public class User { // User class
  @Id // 기본키 설정
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 DBMS에 위임한다.
  private long id;
  @Column(nullable = false, unique = true, length = 20)  // nullable: NULL 허용, unique: 고유값, length: 글자 수
  private String account;
  @Column(nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  public void setPassword(String password) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");  // 패스워드 저장, 단방향 암호화
      md.update(password.getBytes(), 0, password.getBytes().length);
      this.password = new BigInteger(1, md.digest()).toString(16);
    } catch (NoSuchAlgorithmException e) {
      Logger logger = LoggerFactory.getLogger(User.class);
      logger.warn(e.getMessage());
    }
  }

  @Column(nullable = false)
  private String name;  
  @Column(unique = true)
  private String email;
  @Column(unique = true)
  private String phone;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String profilePath;
  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime created;
  @UpdateTimestamp
  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime modified;

  public User(String account, String password, String name, String email, String phone, String profilePath) {
    this.account = account;
    setPassword(password);
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.profilePath = profilePath;
  }
}