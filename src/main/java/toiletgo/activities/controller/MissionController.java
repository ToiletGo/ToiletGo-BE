package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.MissionDto;
import toiletgo.activities.dto.MissionListDto;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.repository.MissionRepository;
import toiletgo.activities.service.MissionService;
import toiletgo.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MissionController {

    private final MissionService missionService;
    
    @Autowired
    public MissionController(@Lazy MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping("/api/missions/get")
    public ResponseEntity<List<MissionListDto>> getMissions(@RequestBody UserDto userDto) {
        if (userDto == null){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        List<MissionListDto> missionListDtoList = missionService.getMissions(userDto);
        if (missionListDtoList == null){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(missionListDtoList);
    }
}