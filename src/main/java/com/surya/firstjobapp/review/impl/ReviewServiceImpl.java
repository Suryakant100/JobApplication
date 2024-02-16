package com.surya.firstjobapp.review.impl;

import com.surya.firstjobapp.company.Company;
import com.surya.firstjobapp.company.CompanyService;
import com.surya.firstjobapp.review.Review;
import com.surya.firstjobapp.review.ReviewRepository;
import com.surya.firstjobapp.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CompanyService companyService;
    @Override
    public List<Review> getAllReviews(Long compId) {
        return reviewRepository.findByCompanyId(compId);
    }

    @Override
    public boolean createReview(Long compId,Review review) {
        Company company = companyService.getCompanyById(compId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long compId,Long reviewId) {
        List<Review> allReview = reviewRepository.findByCompanyId(compId);
        return allReview.stream()
                .filter(review -> review.getId()
                        .equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long compId, Long reviewId, Review review) {
        if(companyService.getCompanyById(compId)!=null){
            List<Review> reviews = companyService.getCompanyById(compId).getReviews();
            Optional<Review> review1 = reviews.stream().filter(review2 -> review2.getId().equals(reviewId)).findFirst();
            if(review1.isPresent()){
                Review toUpdtedReview = review1.get();
                toUpdtedReview.setTitle(review.getTitle());
                toUpdtedReview.setDescription(review.getDescription());
                toUpdtedReview.setRating(review.getRating());
                reviewRepository.save(toUpdtedReview);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    @Override
    public boolean deleteReview(Long compId, Long reviewId) {
        if(companyService.getCompanyById(compId)!=null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(compId,company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
