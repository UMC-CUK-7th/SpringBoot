package umc.spring.repository.reviewRepository;

import com.querydsl.core.Tuple;
import umc.spring.domain.Review;
import umc.spring.domain.enums.UserStatus;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Tuple> findByIdAndUserStatus(Long id, UserStatus userStatus);
}
