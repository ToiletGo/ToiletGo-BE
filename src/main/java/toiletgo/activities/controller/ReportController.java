package toiletgo.activities.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ReportDto;
import toiletgo.activities.service.ReportService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    /**
     * POST /api/report/create
     * 새로운 화장실/리뷰 신고 등록
     */
    @PostMapping("/api/report/create")
    public ResponseEntity<String> reportToilet(@RequestBody ReportDto reportDto) {
        try {
            reportService.createReport(reportDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("신고가 성공적으로 등록되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("등록 중 오류가 발생했습니다.");
        }
    }

    /**
     * GET /api/admin/reports
     * 관리자용: 모든 신고 조회
     */
    @GetMapping("/api/admin/reports")
    public ResponseEntity<List<ReportDto>> getReports() {
        List<ReportDto> dtos = reportService.getAllReports();
        if (dtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(dtos);
    }

    /**
     * POST /api/admin/report/delete
     * 관리자용: 특정 신고 삭제
     */
    @PostMapping("/api/admin/report/delete")
    public ResponseEntity<String> deleteReport(@RequestBody ReportDto reportDto) {
        try {
            reportService.deleteReport(reportDto.getReportId());
            return ResponseEntity.ok("삭제 완료 처리되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제 중 오류 발생: " + e.getMessage());
        }
    }
}