 package com.example.samuraitravel.service;
 
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.Houses;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.FavoriteRepository;
 
 @Service
 public class FavoriteService {
     private final FavoriteRepository favoriteRepository;        
     
     public FavoriteService(FavoriteRepository favoriteRepository) {        
         this.favoriteRepository = favoriteRepository;        
     }     
     
     @Transactional
     public void create(Houses house, User user) {
         // Favoriteクラスのfavoriteインスタンスを作成(コンストラクタの引数は無し)
         Favorite favorite= new Favorite();
         // favoriteインスタンスのsetHouseメソッドを使用(引数はhouse)
         favorite.setHouse(house);
         // favoriteインスタンスのsetUserメソッドを使用(引数はuser)
         favorite.setUser(user);
         // favoriteRepositoryインスタンスのsaveメソッドを使用(引数はfavorite)
         favoriteRepository.save(favorite);
     }
     
     public boolean isFavorite(Houses house, User user) {
         // if文で分岐(条件：favoriteRepository.findByHouseAndUserメソッド(引数はhouse,user)がnullではない場合)
         // 条件を満たしている場合：trueを返却
         // 条件を満たしていない場合：falseを返却
    	 if(favoriteRepository.findByHouseAndUser(house, user) != null) {
    		    return true;
    		} else {
    		    return false;
    		}
     }    
 }
