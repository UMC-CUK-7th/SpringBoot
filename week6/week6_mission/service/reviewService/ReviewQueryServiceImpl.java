package umc.spring.service.reviewService;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.domain.enums.UserStatus;
import umc.spring.repository.reviewRepository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Review> findReview(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Tuple> findReviewsByIdAndUserStatus(Long id, UserStatus userStatus) {
        List<Tuple> filteredReviews = reviewRepository.findByIdAndUserStatus(id, userStatus);

        filteredReviews.forEach(review -> System.out.println("Review: " + review));

        return filteredReviews;
    }
}
