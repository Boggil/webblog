package kr.hs.dgsw.webblog.Service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hs.dgsw.webblog.Domain.Post;
import kr.hs.dgsw.webblog.Repository.PostRepository;
// import kr.hs.dgsw.webblog.Repository.UserRepository;

@Service // 해당 클래스가 Service라는 것을 알려줌
public class PostServiceImpl implements PostService {
  @Autowired  // 각 상황의 타입에 맞는 loC컨테이너 안에 존재하는 Bean을 자동으로 주입해주게 됨
  private PostRepository postRepository;
  @Autowired
  // private UserRepository userRepository;

  @PostConstruct // 객체가 생성된 후 별도의 초기화 작업을 위해 실행하는 메소드를 선언함
  private void init() {
    // User user = userRepository.save(new User("h1", "1234", "하이", "hi@naver.com"))
  }

  @Override
  public Post create(Post post) {
    return postRepository.save(post);
  }

  @Override
  public Post read(Long id) {
    return postRepository.findById(id).isPresent() ? postRepository.findById(id).get() : null;
  }

  @Override
  public Post readByUserId(Long userId) {
    return postRepository
      .findTopByUserIdOrderByIdDesc(userId)
      .orElse(null);
  }

  @Override
  public Post update(Long id, Post post) {
    return postRepository.findById(id)
      .map(found -> {
        found.setTitle(Optional.ofNullable(post.getTitle()).orElse(found.getTitle()));
        found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
        found.setPictures(Optional.ofNullable(post.getPictures()).orElse(found.getPictures()));
        return postRepository.save(found);
      })
      .orElse(null);
  }

  @Override
  public boolean delete(Long id) {
    try {
      postRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public List<Post> readAll() {
    return postRepository.findAll();
  }
  
}