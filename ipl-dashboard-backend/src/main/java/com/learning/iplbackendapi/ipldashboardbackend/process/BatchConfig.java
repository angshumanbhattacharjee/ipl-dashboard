package com.learning.iplbackendapi.ipldashboardbackend.process;

import javax.sql.DataSource;

import com.learning.iplbackendapi.ipldashboardbackend.Listener.JobCompletionNotificationListener;
import com.learning.iplbackendapi.ipldashboardbackend.data.MatchInput;
import com.learning.iplbackendapi.ipldashboardbackend.model.MatchModel;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  final String[] FIELD_NAMES = { "id", "city", "date", "player_of_match", "venue", "neutral_venue", "team1", "team2",
      "toss_winner", "toss_decision", "winner", "result", "result_margin", "eliminator", "method", "umpire1",
      "umpire2" };

  @Bean
  public FlatFileItemReader<MatchInput> reader() {
    return new FlatFileItemReaderBuilder<MatchInput>().name("MatchItemReader")
        .resource(new ClassPathResource("ipl-match-data.csv")).delimited().names(FIELD_NAMES)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {
          {
            setTargetType(MatchInput.class);
          }
        }).build();
  }

  @Bean
  public MatchDataProcessor processor() {
    return new MatchDataProcessor();
  }

  @Bean
  public JdbcBatchItemWriter<MatchModel> writer(DataSource dataSource) {

    return new JdbcBatchItemWriterBuilder<MatchModel>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql(
            "INSERT INTO match_model (id, city, date, player_of_match, venue, neutral_venue, team1, team2, toss_winner_team, toss_decision, match_winner, result, result_margin, eliminator, method, umpire1, umpire2)"
                + "VALUES (:id, :city, :date, :playerOfMatch, :venue, :neutralVenue, :team1, :team2, :tossWinnerTeam, :tossDecision, :matchWinner, :result, :resultMargin, :eliminator, :method, :umpire1, :umpire2)")
        .dataSource(dataSource).build();
  }

  @Bean
  public Job importMatchJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory
            .get("importMatchJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
  }

  @Bean
  public Step step1(JdbcBatchItemWriter<MatchModel> writer) {
    return stepBuilderFactory
            .get("step1")
            .<MatchInput, MatchModel>chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
  }

}
