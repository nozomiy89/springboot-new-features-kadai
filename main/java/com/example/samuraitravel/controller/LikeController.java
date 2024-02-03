package com.example.samuraitravel.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samuraitravel.entity.Like;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.LikeRepository;
import com.example.samuraitravel.security.UserDetailsImpl;

@Controller
@RequestMapping("/likes")
public class LikeController {
	private final LikeRepository likeRepository;
	
	public LikeController(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl ,Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		User user = userDetailsImpl.getUser();
		Page<Like> likePage = likeRepository.findByUserOrderByCreatedAtDesc(user, pageable);
		
		model.addAttribute("likePage", likePage);
		
		return "likes/index";
	}
}