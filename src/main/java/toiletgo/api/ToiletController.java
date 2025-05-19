package toiletgo.api;

import toiletgo.dto.ToiletDto;
import toiletgo.entity.Toilet;
import toiletgo.repository.ToiletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ToiletController {
    @Autowired
    ToiletRepository toiletRepository;
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

    @GetMapping("/api/toilet/{toiletId}")
    public ResponseEntity<ToiletDto> getToilet(@PathVariable Long toiletId){
        Toilet toilet = toiletRepository.findById(toiletId).orElse(null);
        if(toilet != null){
            ToiletDto toiletDto = toilet.toDto();
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
}
