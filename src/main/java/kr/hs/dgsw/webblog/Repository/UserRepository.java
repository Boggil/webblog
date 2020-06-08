package kr.hs.dgsw.webblog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hs.dgsw.webblog.Domain.User;

@Repository // 해당 클래스가 Repository로 사용됨을 알림
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByAccount(String account);
}