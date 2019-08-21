package report;

import model.MonthlyCrossingSummary;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoarderCrossingReportWriter {
    private Writer writer;

    public BoarderCrossingReportWriter(Path output) throws IOException {
        this.writer = Files.newBufferedWriter(output);
    }

    public void close() throws IOException{
        writer.flush();
        writer.close();
    }

    public void writeReportAll(Map<String, Map<String, List<MonthlyCrossingSummary>>> groupedByBoarderMeasure) throws IOException {
        for(String boarder : groupedByBoarderMeasure.keySet()){
            Map<String, List<MonthlyCrossingSummary>> groupedByMeasure = groupedByBoarderMeasure.get(boarder);
            for(String measure : groupedByMeasure.keySet()){
                List<MonthlyCrossingSummary> sorted = sortByDate(groupedByMeasure.get(measure));
                writeMonthlyForBoarderAndMeasure(sorted, boarder, measure);
            }
        }
    }
    private List<MonthlyCrossingSummary> sortByDate(List<MonthlyCrossingSummary> monthlySummaries) {
        return monthlySummaries.stream().sorted(Comparator.comparing(MonthlyCrossingSummary::getDate)).collect(Collectors.toList());
    }

    private void writeMonthlyForBoarderAndMeasure( List<MonthlyCrossingSummary> sortedMonthly , String boarder, String measure) throws IOException{
        int runningTotal = 0;
        int runningCount = 0;
        for(MonthlyCrossingSummary mbc : sortedMonthly){
            runningTotal+=mbc.getSum();
            runningCount++;
            int runningAvg = 0;
            runningAvg = runningTotal/runningCount;
            StringBuilder resultLine = new StringBuilder();
            resultLine.append(boarder).append(",").append(Util.formatDate(mbc.getDate())).append(",").append(measure).append(",").append(mbc.getSum()).append(",").append(runningAvg).append("\n");
            writer.append(resultLine.toString());
        }
    }
}
