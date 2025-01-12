package com.example.samuraitravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Houses;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private final ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Transactional
	public void create(Houses house, User user, ReviewRegisterForm reviewRegisterForm) {
                      // Reviewクラスのreviewインスタンスを作成(コンストラクタは無し)
		Review review = new Review();
                      // reviewインスタンスへreviewRegisterFormから受け取った値をセット
		review.setScore(reviewRegisterForm.getScore());
		review.setContent(reviewRegisterForm.getContent());
		review.setHouse(house);
		review.setUser(user);
                      // reviewRepositoryのsaveメソッド(引数review)を実施
		reviewRepository.save(review);
	}

	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
                      // Reviewクラスのreviewインスタンスを作成し、reviewRepository.getReferenceByIdメソッド(引数はreviewEditForm.getId())をセット
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
                      // reviewインスタンスへreviewEditFormのscoreとcontentをセット
		review.setScore(reviewEditForm.getScore());
		review.setContent(reviewEditForm.getContent());
                      // reviewRepositoryのsaveメソッド(引数review)を実施
		reviewRepository.save(review);
	}

	public boolean hasUserAlreadyReviewed(Houses house, User user) {
                      // if文で分岐(条件：reviewRepository.findByHouseAndUserメソッド(引数はhouse,user)がnullではない場合)
                      // 条件を満たしている場合：trueを返却
                      // 条件を満たしていない場合：falseを返却
		if(reviewRepository.findByHouseAndUser(house, user) != null) {
		    return true;
		} else {
		    return false;
		}
	}
}