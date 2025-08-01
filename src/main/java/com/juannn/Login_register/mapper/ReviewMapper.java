package com.juannn.Login_register.mapper;

import com.juannn.Login_register.dto.reviews.request.ReviewRequest;
import com.juannn.Login_register.dto.reviews.response.ReviewResponse;
import com.juannn.Login_register.model.review.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    Review toReview(ReviewRequest request);

    @Mapping(source = "user.name", target = "username")
    ReviewResponse toReviewResponse(Review review);
}