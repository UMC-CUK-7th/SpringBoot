package umc.spring.repository.userRepository;

import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByIdAndMissionStatusAndRegionId(Long id, MissionStatus missionStatus, Long region_id);
}