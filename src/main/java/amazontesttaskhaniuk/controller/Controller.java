package amazontesttaskhaniuk.controller;

import amazontesttaskhaniuk.Service.ReportSpecificationService;
import amazontesttaskhaniuk.model.SalesAndTrafficByDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final ReportSpecificationService service;

    @GetMapping("/load-report")
    public String loadReportData() {
        service.loadReportSpecification();
        return "Report data loaded successfully!";
    }

    @GetMapping("/statistics")
    public SalesAndTrafficByDate getStatisticsByDate(@RequestParam String date) {
        Optional<SalesAndTrafficByDate> statisticsByDate = service.getStatisticsByDate(date);
        return statisticsByDate.orElse(null);
    }
    @GetMapping("/statistics/date-range")
    public List<SalesAndTrafficByDate> getStatisticsByDateRange(@RequestParam String startDate, @RequestParam String endDate ) {
        return service.getSalesAndTrafficByDateRange(startDate, endDate);
    }


}
