package kr.hs.dgsw.webblog.Service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hs.dgsw.webblog.Domain.Post;
import kr.hs.dgsw.webblog.Repository.PostRepository;
// import kr.hs.dgsw.webblog.Repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
  @Autowired
  private PostRepository postRepository;
  @Autowired
  // private UserRepository userRepository;

  @PostConstruct
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