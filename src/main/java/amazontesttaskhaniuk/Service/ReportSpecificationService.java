package amazontesttaskhaniuk.Service;

import amazontesttaskhaniuk.parser.Parser;
import amazontesttaskhaniuk.model.ReportContainer;
import amazontesttaskhaniuk.model.SalesAndTrafficByDate;
import amazontesttaskhaniuk.repository.ReportContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportSpecificationService {
    @Value("${file.path}")
    private String filePath;
    private final ReportContainerRepository repository;

    public void loadReportSpecification() {

        try {
            ReportContainer reportContainer = Parser.parseJson(filePath);
            repository.save(reportContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Cacheable(value = "date", key = "#date")
    public Optional<SalesAndTrafficByDate> getStatisticsByDate(String date) {
        return repository.findBySalesAndTrafficByDate_Date(date)
                .flatMap(reportContainer -> reportContainer.getSalesAndTrafficByDate()
                        .stream()
                        .filter(data -> data.getDate().equals(date))
                        .findFirst());
    }

    @Cacheable(value = "multipleDates", key = "#startDate + '-' + #endDate")

    public List<SalesAndTrafficByDate> getSalesAndTrafficByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        return repository.findAll().stream()
                .flatMap(reportContainer -> reportContainer.getSalesAndTrafficByDate().stream()) // Flatten all SalesAndTrafficByDate entries
                .filter(data -> {
                    LocalDate dataDate = LocalDate.parse(data.getDate(), formatter);
                    return (dataDate.isEqual(start) || dataDate.isAfter(start)) &&
                            (dataDate.isEqual(end) || dataDate.isBefore(end));
                })
                .collect(Collectors.toList()); // Collect filtered results into a list
    }


}
