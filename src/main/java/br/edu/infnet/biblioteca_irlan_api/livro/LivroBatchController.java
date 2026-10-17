package br.edu.infnet.biblioteca_irlan_api.livro;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/livros/batch")
public class LivroBatchController {

    private final JobLauncher jobLauncher;
    private final Job importLivroJob;

    public LivroBatchController(JobLauncher jobLauncher, Job importLivroJob) {
        this.jobLauncher = jobLauncher;
        this.importLivroJob = importLivroJob;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file) {
        try {
            Path tempFile = Files.createTempFile("livros_", ".csv");
            file.transferTo(tempFile.toFile());

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("filePath", tempFile.toString())
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(importLivroJob, jobParameters);

            return ResponseEntity.ok("Processamento em lote de livros iniciado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao iniciar processamento: " + e.getMessage());
        }
    }
}
