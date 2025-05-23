package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toiletgo.activities.dto.ReportDto;
import toiletgo.activities.entity.Report;
import toiletgo.activities.repository.ReportRepository;
import toiletgo.user.repository.UserRepository;

@RestController
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserRepository UserRepository;

    @Autowired
    ReportRepository reviewRepository;

    @PostMapping("/api/report/toilet")
    public ResponseEntity<String> reportToilet(@RequestBody ReportDto reportDto){

        Report report = reportDto.toEn
    }



}
