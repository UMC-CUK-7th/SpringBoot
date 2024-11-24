package umc.spring.service.userService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.userRepository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findUsersByIdAndMissionStatusAndRegionId(Long id, MissionStatus missionStatus, Long region_id) {
        List<User> filteredUsers = userRepository.findByIdAndMissionStatusAndRegionId(id, missionStatus, region_id);

        filteredUsers.forEach(user -> System.out.println("User: " + user));

        return filteredUsers;
    }
}
