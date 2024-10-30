package com.parkour.kmp.api.batch.loader;

import com.opencsv.CSVReader;
import com.parkour.kmp.api.medcode.domain.MedCode;
import com.parkour.kmp.api.medcode.repository.MedCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MedCodeRepository medCodeRepository;

    private static final int BATCH_SIZE = 50;

    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("/data/MEDCODE_20231030.csv");
        if (medCodeRepository.count() > 0) {
            return;
        }

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] nextLine;
            List<MedCode> medCodes = new ArrayList<>();
            csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                MedCode medcode = new MedCode(
                        nextLine[6], nextLine[9], nextLine[10]
                );
                medCodes.add(medcode);
                if (medCodes.size() == BATCH_SIZE) {
                    medCodeRepository.saveAll(medCodes);
                    medCodes.clear();
                }
            }

            if (!medCodes.isEmpty()) {
                medCodeRepository.saveAll(medCodes);
            }
        }
    }
}
