package umc.spring.repository.missionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QUserMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QUserMission userMission = QUserMission.userMission;

    @Override
    public List<Mission> findByUserIdAndMissionStatus(Long user_id, MissionStatus missionStatus) {
        return jpaQueryFactory
                .selectFrom(mission)
                .join(userMission).on(userMission.user.id.eq(user_id))
                .where(userMission.user.id.eq(user_id)
                        .and(userMission.missionStatus.eq(missionStatus)))
                .orderBy(mission.deadline.asc())
                .fetch();
    }
}
