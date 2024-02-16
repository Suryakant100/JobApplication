package com.surya.firstjobapp.review;

import java.util.List;

public interface ReviewService {
     List<Review> getAllReviews(Long compId);
     boolean createReview(Long compId,Review review);

     Review getReviewById(Long compId,Long reviewId);
     boolean updateReview(Long compId, Long reviewId, Review review);

     boolean deleteReview(Long compId, Long reviewId);
}
