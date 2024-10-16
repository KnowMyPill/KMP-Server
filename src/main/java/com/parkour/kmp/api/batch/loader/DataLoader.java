package com.parkour.kmp.api.batch.loader;

import com.opencsv.CSVReader;
import com.parkour.kmp.api.medcode.domain.MedCode;
import com.parkour.kmp.api.medcode.repository.MedCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.InputStreamReader;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MedCodeRepository medCodeRepository;


    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("/data/MEDCODE_20231030.csv");

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] nextLine;
            csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                MedCode medcode = new MedCode(
                        nextLine[6], nextLine[9], nextLine[10], nextLine[11]
                );
                medCodeRepository.save(medcode);
            }
        }
    }
}
