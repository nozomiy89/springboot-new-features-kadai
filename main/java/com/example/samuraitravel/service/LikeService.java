package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Like;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.LikeRepository;
import com.example.samuraitravel.repository.UserRepository;

@Service
public class LikeService {
	private final LikeRepository likeRepository;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	
	public LikeService(LikeRepository likeRepository, HouseRepository houseRepository, UserRepository userRepository) {
		this.likeRepository = likeRepository;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void addLikes(Integer houseId, Integer userId) {
		Like like = new Like();
		House house = houseRepository.getReferenceById(houseId);
		User user = userRepository.getReferenceById(userId);
		
		like.setHouse(house);
		like.setUser(user);
		likeRepository.save(like);
	}
	
	@Transactional
	public void removeLikes(Integer houseId, Integer userId) {
		Like like = likeRepository.findByHouseIdAndUserId(houseId, userId);
		
		likeRepository.delete(like);
	}
}
