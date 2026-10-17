package br.edu.infnet.biblioteca_irlan_api.livro;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class LivroBatchConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<LivroCSV> livroReader(@Value("#{jobParameters['filePath']}") String filePath) {
        return new FlatFileItemReaderBuilder<LivroCSV>()
                .name("livroReader")
                .resource(new FileSystemResource(filePath))
                .delimited()
                .names("titulo", "autor")
                .targetType(LivroCSV.class)
                .build();
    }

    @Bean
    public ItemProcessor<LivroCSV, Livro> livroProcessor() {
        return item -> {
            Livro livro = new Livro();
            livro.setTitulo(item.titulo());
            livro.setAutor(item.autor());
            return livro;
        };
    }

    @Bean
    public JpaItemWriter<Livro> livroWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Livro>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step livroStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                          FlatFileItemReader<LivroCSV> livroReader, JpaItemWriter<Livro> livroWriter) {
        return new StepBuilder("livroStep", jobRepository)
                .<LivroCSV, Livro>chunk(10, transactionManager)
                .reader(livroReader)
                .processor(livroProcessor())
                .writer(livroWriter)
                .build();
    }

    @Bean
    public Job importLivroJob(JobRepository jobRepository, Step livroStep) {
        return new JobBuilder("importLivroJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(livroStep)
                .build();
    }
}
