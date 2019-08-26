package report;

import model.MonthlyCrossingSummary;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

    public void writeHeader(String s) throws  Exception {
        writer.append(s + "\n");
    }

    public void writeResult(List<MonthlyCrossingSummary> list) throws Exception{
        for(MonthlyCrossingSummary mbc : list) {
            writer.append(mbc.toString());
        }
    }
}
