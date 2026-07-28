package ru.skillfactorydemo.tgbot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skillfactorydemo.tgbot.entity.Spend;
import ru.skillfactorydemo.tgbot.repository.SpendRepository;
import ru.skillfactorydemo.tgbot.repository.StatsRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    private StatsRepository statsRepository;

    @Mock
    private SpendRepository spendRepository;

    @InjectMocks
    private StatsService statsService;

    @Test
    void shouldReturnExpensesAboveAmount() {
        // given
        LocalDate from = LocalDate.of(2024, 1, 1);
        LocalDate to = LocalDate.of(2024, 1, 31);
        BigDecimal amount = BigDecimal.valueOf(500);

        Spend spend1 = new Spend();
        spend1.setId(1L);
        spend1.setChatId(123L);
        spend1.setSpend(BigDecimal.valueOf(1000));
        spend1.setOperationDate(LocalDate.of(2024, 1, 15));

        when(spendRepository.findExpensesGreaterThan(from, to, amount))
                .thenReturn(List.of(spend1));

        // when
        List<Spend> result = statsService.getExpensesAboveAmount(from, to, amount);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getSpend()).isGreaterThan(amount);
    }
}