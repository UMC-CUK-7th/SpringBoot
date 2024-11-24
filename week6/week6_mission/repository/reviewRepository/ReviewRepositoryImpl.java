package umc.spring.repository.reviewRepository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QReview;
import umc.spring.domain.QStore;
import umc.spring.domain.QUser;
import umc.spring.domain.enums.UserStatus;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;
    private final QStore store = QStore.store;
    private final QUser user = QUser.user;

    @Override
    public List<Tuple> findByIdAndUserStatus(Long id, UserStatus userStatus) {
        return jpaQueryFactory
                .select(user.nickname, review)
                .from(review)
                .join(user).on(review.user.id.eq(user.id))
                .join(store).on(review.store.id.eq(store.id))
                .where(store.id.eq(store.id)
                        .and(user.userStatus.eq(UserStatus.ACTIVE)))
                .orderBy(review.updatedAt.desc(), user.createdAt.desc())
                .fetch();
    }
}
