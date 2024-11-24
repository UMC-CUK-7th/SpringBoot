package umc.spring.repository.userRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.*;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QUserMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QUser user = QUser.user;
    private final QUserMission userMission = QUserMission.userMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    @Override
    public List<User> findByIdAndMissionStatusAndRegionId(Long id, MissionStatus missionStatus, Long region_id) {
        return jpaQueryFactory
                .select(store.name, store.foodCategory.id, mission)
                .from(user)
                .join(userMission).on(user.id.eq(userMission.user.id))
                .join(mission).on(mission.id.eq(userMission.mission.id))
                .join(store).on(store.id.eq(mission.store.id))
                .where(user.id.eq(user.id)
                        .and(userMission.missionStatus.eq(MissionStatus.CHALLENGING))
                        .and(store.region.id.eq(region_id)))
                .orderBy(mission.deadline.desc(), userMission.updatedAt.desc())
                .fetch();
    }
}
