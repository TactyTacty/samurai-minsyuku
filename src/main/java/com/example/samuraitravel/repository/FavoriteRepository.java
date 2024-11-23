package com.example.samuraitravel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.Houses;
import com.example.samuraitravel.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
                // Page<Favorite>型のfindByUserOrderByCreatedAtDescメソッドを作成(引数はUser user, Pageable pageable)
	public Page<Favorite> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
                // Favorite型のfindByHouseAndUserメソッドを作成(引数はHouse house, User user)
	public Favorite findByHouseAndUser(Houses house, User user);

}
