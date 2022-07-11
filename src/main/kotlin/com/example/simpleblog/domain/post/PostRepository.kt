package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.commenet.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>{
}