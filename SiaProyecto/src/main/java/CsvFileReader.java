import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {
    private String delimiter;

    public CsvFileReader(String delimiter) {
        this.delimiter = delimiter;
    }

    public void leerCsv(String archivo) {
        List<String[]> data;
        data = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                data.add(values);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
