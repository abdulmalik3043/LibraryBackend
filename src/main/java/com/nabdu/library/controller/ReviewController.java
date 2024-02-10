package com.nabdu.library.controller;

import com.nabdu.library.requestmodels.ReviewRequest;
import com.nabdu.library.service.ReviewService;
import com.nabdu.library.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${myapp.allowed-origins}")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController (ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/secure/user/book")
    @CrossOrigin
    public Boolean reviewBookByUser(@RequestHeader(value="Authorization") String token,
                                    @RequestParam Long bookId) throws Exception {
    	System.out.println("helllooo");
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "sub");

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value="Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "sub");
        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }
}
