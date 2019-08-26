package report;

import model.BorderCrossingInfo;
import model.GeoCoordinate;
import model.MonthlyCrossingSummary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BorderCrossingAnalyzer {
    private Path inputFile;

    public BorderCrossingAnalyzer(Path inputFilePath) {
        this.inputFile = inputFilePath;
    }

    public Map<String, Map<String, List<MonthlyCrossingSummary>>> summarizeMonthlyCrossing() throws Exception{
        Map<Integer, MonthlyCrossingSummary> monthlyCrossingSummary =  generateMonthlyCrossingSummary(inputFile);
        Map<String, Map<String, List<MonthlyCrossingSummary>>> groupedByBoarderMeasure = groupByBoarderAndMeasure(monthlyCrossingSummary);
        return groupedByBoarderMeasure;
    }

    private Map<String, Map<String, List<MonthlyCrossingSummary>>> groupByBoarderAndMeasure(Map<Integer, MonthlyCrossingSummary> monthlyCrossingSummary) {
        return monthlyCrossingSummary.values().stream().collect(Collectors.groupingBy(MonthlyCrossingSummary::getBorder, Collectors.groupingBy(MonthlyCrossingSummary::getMeasure)));
    }

    private  Map<Integer, MonthlyCrossingSummary> generateMonthlyCrossingSummary(Path input) throws Exception {
        Map<Integer, MonthlyCrossingSummary> map = new HashMap<>();
        Stream<String> lineStream = Files.lines(input);
        lineStream.skip(1).map(line->parceData(line)).forEach(d->consumeAndDiscard(d,map));
        return map;
    }

    private static void consumeAndDiscard(BorderCrossingInfo d, Map<Integer, MonthlyCrossingSummary> map) {
        if(map.containsKey(d.hashCode())){
            MonthlyCrossingSummary mbc = map.get(d.hashCode());
            mbc.addToSum(d.getValue());
        }else{
            MonthlyCrossingSummary mbc = new MonthlyCrossingSummary(d.getBorder(),d.getDate(),d.getMeasure());
            mbc.addToSum(d.getValue());
            map.put(d.hashCode(),mbc);
        }
    }

    static BorderCrossingInfo parceData(String dataLine) {
        BorderCrossingInfo bcInfo = new BorderCrossingInfo();
        String[] a = dataLine.split(",");
        bcInfo.setPortName(a[0].trim());
        bcInfo.setState(a[1].trim());
        bcInfo.setPortCode(Integer.parseInt(a[2].trim()));
        bcInfo.setBorder(a[3].trim());
        bcInfo.setDate(toDate(a[4].trim()));
        bcInfo.setMeasure(a[5].trim());
        bcInfo.setValue(Integer.parseInt(a[6].trim()));
        bcInfo.setLocation(toGeoCoor(a[7].trim()));

        return bcInfo;
    }

    private static Date toDate(String strDate)  {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy hh:mm:ss a");
        try {
            return formatter.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new Date();
    }

    private static GeoCoordinate toGeoCoor(String loc) {
        String dataPart = loc.substring(loc.indexOf("(") +1, loc.lastIndexOf(")"));
        String [] a = dataPart.split(" ");
        return new GeoCoordinate(Double.parseDouble(a[0].trim()), Double.parseDouble(a[1].trim()));
    }

    public  List<MonthlyCrossingSummary> computerRunningAverage(Map<String, Map<String, List<MonthlyCrossingSummary>>> groupedByBoarderMeasure) throws IOException {
        List<MonthlyCrossingSummary> result = new ArrayList<>();
        for(String boarder : groupedByBoarderMeasure.keySet()){
            Map<String, List<MonthlyCrossingSummary>> groupedByMeasure = groupedByBoarderMeasure.get(boarder);
            for(String measure : groupedByMeasure.keySet()){
                List<MonthlyCrossingSummary> sorted = sortByDate(groupedByMeasure.get(measure));
                result.addAll(computeMonthlyRunningAverage(sorted));
            }
        }
        return result;
    }
    private List<MonthlyCrossingSummary> sortByDate(List<MonthlyCrossingSummary> monthlySummaries) {
        return monthlySummaries.stream().sorted(Comparator.comparing(MonthlyCrossingSummary::getDate)).collect(Collectors.toList());
    }

    private List<MonthlyCrossingSummary> computeMonthlyRunningAverage(List<MonthlyCrossingSummary> sortedMonthly) throws IOException{
        int runningTotal = 0;
        int runningCount = 0;
        List<MonthlyCrossingSummary> monthlyByMeasure = new ArrayList<>();
        for(MonthlyCrossingSummary mbc : sortedMonthly){
            int runningAvg = 0;
            runningAvg = runningCount == 0? 0 : (int)Math.round(runningTotal*1d/runningCount);
            mbc.setRunningAvg(runningAvg);
            monthlyByMeasure.add(mbc);
            runningCount++;
            runningTotal+=mbc.getSum();
        }
        return monthlyByMeasure;
    }

}
