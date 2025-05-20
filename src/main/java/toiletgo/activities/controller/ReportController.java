package toiletgo.activities.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @PostMapping("/api/report/toilet")
    public ResponseEntity<String> reportToilet(@ResponseBody ReportDto reportDto){

    }
}
