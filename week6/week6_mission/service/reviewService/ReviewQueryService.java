package umc.spring.service.reviewService;

import com.querydsl.core.Tuple;
import umc.spring.domain.Review;
import umc.spring.domain.enums.UserStatus;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> findReview(Long id);
    List<Tuple> findReviewsByIdAndUserStatus(Long id, UserStatus userStatus);
}
