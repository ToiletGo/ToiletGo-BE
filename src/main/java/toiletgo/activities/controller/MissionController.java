package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toiletgo.activities.dto.MissionListDto;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.repository.MissionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MissionController {
    @Autowired
    MissionRepository missionRepository;

    @GetMapping("/api/missions/{userId}")
    public ResponseEntity<List<MissionListDto>> getMissions(@PathVariable String userId) {
        List<Mission> missions = missionRepository.findRandomMissionsByUserId(userId);
        if (missions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        List<MissionListDto> missionListDtoList = missions.stream()
                .map(mission -> mission.toDto())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(missionListDtoList);
    }

    @PatchMapping("/api/missions/complete/{userId}/{mission_id}")
    public ResponseEntity<String> completeMission(@PathVariable String userId, @PathVariable Long missionId) {
        try {
            Mission mission = missionRepository.findById(missionId).orElse(null);
            if (mission == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 미션이 존재하지 않습니다.");
            }
            mission.setIsCompleted(true);
            mission.setCompletedAt(LocalDateTime.now());

            missionRepository.save(mission);

            return ResponseEntity.status(HttpStatus.OK).body("미션 완료 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("미션 완료 처리 중 오류 발생: " + e.getMessage());
        }

    }
}