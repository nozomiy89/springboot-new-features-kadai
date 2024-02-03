package com.example.samuraitravel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Like;
import com.example.samuraitravel.entity.User;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	Like findByHouseAndUser(House house, User user);
	
	Like findByHouseIdAndUserId(Integer houseId, Integer userId);
	
	public Page<Like> findByUserOrderByCreatedAtDesc(User user,Pageable pageable);
}