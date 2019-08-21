package report;

import model.MonthlyCrossingSummary;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class MainApplication {
    private static BorderCrossingAnalyzer dataAnalyzer;
    private static BoarderCrossingReportWriter reportWriter;
    public static void main(String[] args) throws Exception {
        if(args.length < 1 || Util.isEmpty(args[0])){
            throw new RuntimeException("Input file argument required. Please provide input file.");
        }
        Path input = Paths.get(args[0]);
        Path output = Paths.get("output/report.csv");
        if(args.length > 1 && !Util.isEmpty(args[1])) {
            output = Paths.get(args[1]);
        }
        reportWriter = new BoarderCrossingReportWriter(output);
        dataAnalyzer = new BorderCrossingAnalyzer(input);
        Map<String, Map<String, List<MonthlyCrossingSummary>>> monthlyCrossingsSummerizedByBoarderMeasure = dataAnalyzer.summarizeMonthlyCrossing();
        reportWriter.writeReportAll(monthlyCrossingsSummerizedByBoarderMeasure);
        reportWriter.close();
    }


}
