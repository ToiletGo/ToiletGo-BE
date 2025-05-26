package toiletgo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ReportDto;
import toiletgo.activities.dto.ToiletDto;
import toiletgo.activities.dto.ToiletSearchFilterDto;
import toiletgo.activities.entity.Toilet;
import toiletgo.activities.repository.ToiletRepository;
import toiletgo.user.dto.UserDto;
import toiletgo.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ToiletController {
    @Autowired
    ToiletRepository toiletRepository;
    /*
    @GetMapping("/api/toilets")
    public ResponseEntity<List<ToiletDto>> getToilets(){
        List<Toilet> toilets = toiletRepository.findAll();

        if (!toilets.isEmpty()) {
            List<ToiletDto> toiletDtos = toilets.stream()
                    .map(ToiletDto::toDto)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(toiletDtos);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }*/
    //화장실 조회(필터버전) 검토 필요
    @PostMapping("/api/toilets/filter")
    public ResponseEntity<List<ToiletDto>> getFilteredToiltets(@RequestBody ToiletSearchFilterDto requestDto){
        List<Toilet> toilets = toiletRepository.findFilteredToilets(
                requestDto.getMinLatitude(),
                requestDto.getMaxLatitude(),
                requestDto.getMinLongitude(),
                requestDto.getMaxLongitude(),
                requestDto.getHasDiaperTable(),
                requestDto.getHasHandicapAccess(),
                requestDto.getHasBidet(),
                requestDto.getHasTissue()
        );

        if (toilets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        List<ToiletDto> toiletDtos = toilets.stream()
                .map(ToiletDto::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(toiletDtos);
    }


    @GetMapping("/api/toilets")
    public ResponseEntity<List<ToiletDto>> getToilets(){
        List<Toilet> toilets = toiletRepository.findAll();

        if (!toilets.isEmpty()) {
            List<ToiletDto> toiletDtos = toilets.stream()
                    .map(toilet -> toilet.toDto())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(toiletDtos);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/api/toilet/get")
    public ResponseEntity<ToiletDto> getToilet(@RequestBody ToiletDto toiletDto){
        Toilet toilet = toiletRepository.findById(toiletDto.getToiletId()).orElse(null);
        if(toilet != null){
            return ResponseEntity.status(HttpStatus.OK).body(toiletDto);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/api/toilet")
    public ResponseEntity<String> createToilet(@RequestBody ToiletDto toiletDto){
        try{
            Toilet toilet = toiletDto.toEntity();
            toiletRepository.save(toilet);
            return ResponseEntity.status(HttpStatus.CREATED).body("화장실이 성공적으로 등록되었습니다.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 중 오류가 발생했습니다.");
        }
    }

    //admin 오류 : cascade 오류
    @PostMapping("/api/admin/toilet/delete")
    public ResponseEntity<String> deleteToilet(@RequestBody ReportDto reportDto) {
        try {
            Toilet toilet = toiletRepository.findById(reportDto.getToiletId()).orElse(null);
            if (toilet == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 유저가 존재하지 않습니다.");
            }
            toiletRepository.delete(toilet);

            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제 처리 중 오류 발생: " + e.getMessage());
        }
    }
}

