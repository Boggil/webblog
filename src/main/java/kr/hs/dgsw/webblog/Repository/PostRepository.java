package kr.hs.dgsw.webblog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hs.dgsw.webblog.Domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  Optional<Post> findTopByUserIdOrderByIdDesc(Long userId);
}