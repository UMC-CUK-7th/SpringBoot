package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long id);
    List<Mission> findMissionsByUserIdAndMissionStatus(Long user_id, MissionStatus missionStatus);
}
