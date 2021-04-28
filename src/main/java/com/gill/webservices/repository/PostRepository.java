package com.gill.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gill.webservices.model.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
