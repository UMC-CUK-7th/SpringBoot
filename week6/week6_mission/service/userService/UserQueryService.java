package umc.spring.service.userService;

import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> findUser(Long id);
    List<User> findUsersByIdAndMissionStatusAndRegionId(Long id, MissionStatus missionStatus, Long region_id);
}
