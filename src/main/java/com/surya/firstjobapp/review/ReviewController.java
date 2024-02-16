package com.surya.firstjobapp.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        List<Review> allReview = reviewService.getAllReviews(companyId);
        if(allReview.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allReview,HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReviews(@PathVariable Long companyId, @RequestBody Review review){
        boolean isSaved = reviewService.createReview(companyId,review);
        if(isSaved){
            return new ResponseEntity<>("Review has benn created successfully.",HttpStatus.CREATED);
        }
        return  new ResponseEntity<>("Something went wrong, you might have entered wrong input",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public  ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId,reviewId);
        if(review!=null){
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId,@RequestBody Review review){
        boolean isUpdated = reviewService.updateReview(companyId,reviewId,review);
        if(isUpdated){
            return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Nor Updates, You might have entered wrong input.",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review Deleted Successfully.",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went Wrong.",HttpStatus.NOT_FOUND);
    }
}
