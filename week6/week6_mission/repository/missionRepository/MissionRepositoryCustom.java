package umc.spring.repository.missionRepository;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findByUserIdAndMissionStatus(Long user_id, MissionStatus missionStatus);
}
