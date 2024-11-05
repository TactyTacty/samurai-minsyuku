package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Houses;

public interface HouseRepository extends JpaRepository<Houses, Integer> {
	public Page<Houses> findByNameLike(String keyword, Pageable pageable);
	
	public Page<Houses> findByNameLikeOrAddressLikeOrderByCreatedAtDesc(String nameKeyword, String addressKeyword, Pageable pageable);  
    public Page<Houses> findByNameLikeOrAddressLikeOrderByPriceAsc(String nameKeyword, String addressKeyword, Pageable pageable);  
    public Page<Houses> findByAddressLikeOrderByCreatedAtDesc(String area, Pageable pageable);
    public Page<Houses> findByAddressLikeOrderByPriceAsc(String area, Pageable pageable);
    public Page<Houses> findByPriceLessThanEqualOrderByCreatedAtDesc(Integer price, Pageable pageable);
    public Page<Houses> findByPriceLessThanEqualOrderByPriceAsc(Integer price, Pageable pageable); 
    public Page<Houses> findAllByOrderByCreatedAtDesc(Pageable pageable);
    public Page<Houses> findAllByOrderByPriceAsc(Pageable pageable);
    
    public List<Houses> findTop10ByOrderByCreatedAtDesc();
}
