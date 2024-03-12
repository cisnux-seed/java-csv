package dev.cisnu.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class CsvTest {
    @Test
    void createCSV() throws IOException {
        final var writer = new StringWriter();

        final var printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.printRecord("Fajra", "Risqulla", 100);
        printer.printRecord("Budi", "Nugraha", 95);
        printer.flush();

        final var csv = writer.getBuffer().toString();
        System.out.println(csv);
    }

    @Test
    void readCSV() throws IOException {
        final var path = Path.of("sample.csv");
        final var reader = Files.newBufferedReader(path);

        final var parser = new CSVParser(reader, CSVFormat.DEFAULT);
        for (final var record : parser) {
            System.out.println("First Name : " + record.get(0));
            System.out.println("Last Name : " + record.get(1));
            System.out.println("Value : " + record.get(2));
        }
    }

    @Test
    void createCSVWithHeader() throws IOException {
        final var writer = new StringWriter();

        final var format = CSVFormat.DEFAULT.builder()
                .setHeader("First Name", "Last Name", "Value")
                .build();

        final var printer = new CSVPrinter(writer, format);
        printer.printRecord("Fajra", "Risqulla", 100);
        printer.printRecord("Budi", "Nugraha", 95);
        printer.flush();

        final var csv = writer.getBuffer().toString();
        System.out.println(csv);
    }

    @Test
    void readCSVWithHeader() throws IOException {
        final var path = Path.of("sample.csv");
        final var reader = Files.newBufferedReader(path);

        final var format = CSVFormat.DEFAULT.builder().setHeader().build();
        final var parser = new CSVParser(reader, format);
        for (final var record : parser) {
            System.out.println("First Name : " + record.get("First Name"));
            System.out.println("Last Name : " + record.get("Last Name"));
            System.out.println("Value : " + record.get("Value"));
        }
    }

    @Test
    void createCSVWithTab() throws IOException {
        final var writer = new StringWriter();

        final var format = CSVFormat.TDF.builder()
                .setHeader("First Name", "Last Name", "Value")
                .build();

        final var printer = new CSVPrinter(writer, format);
        printer.printRecord("Fajra", "Risqulla", 100);
        printer.printRecord("Budi", "Nugraha", 95);
        printer.flush();

        final var csv = writer.getBuffer().toString();
        System.out.println(csv);
    }
}
