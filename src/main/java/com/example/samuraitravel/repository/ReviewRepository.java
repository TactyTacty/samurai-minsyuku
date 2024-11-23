package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Houses;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
           // List<Review>型のfindTop6ByHouseOrderByCreatedAtDescメソッドを作成(引数はHouse house)
	public List<Review> findTop6ByHouseOrderByCreatedAtDesc(Houses house);
           // ReviewクラスのfindByHouseAndUserメソッドを作成(引数はHouse house, User user)
	public Review findByHouseAndUser(Houses house, User user);
           // long型でcountByHouseメソッドを作成(引数はHouse house)
	public int countByHouse(Houses house);
           // Page<Review>型でfindByHouseOrderByCreatedAtDescメソッドを作成(引数はHouse house, Pageable pageable)
	public Page<Review> findByHouseOrderByCreatedAtDesc(Houses house, Pageable pageable);
}
