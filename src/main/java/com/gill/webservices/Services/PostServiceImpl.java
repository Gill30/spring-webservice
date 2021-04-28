package com.gill.webservices.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gill.webservices.DAO.UserDao;
import com.gill.webservices.model.Post;
import com.gill.webservices.model.User;
import com.gill.webservices.repository.PostRepository;
import com.gill.webservices.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	

	@Override
	public Post createPost(Post post) {
		return postRepository.save(post);
	}
	
}
	
