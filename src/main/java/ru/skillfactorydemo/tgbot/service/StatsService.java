package ru.skillfactorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillfactorydemo.tgbot.entity.Spend;
import ru.skillfactorydemo.tgbot.repository.SpendRepository;
import ru.skillfactorydemo.tgbot.repository.StatsRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final StatsRepository statsRepository;
    private final SpendRepository spendRepository;  // <-- ДОБАВИТЬ ЭТО

    public int getCountOfIncomesThatGreater(BigDecimal amount) {
        return statsRepository.getCountOfIncomesThatGreaterThan(amount);
    }

    public List<Spend> getExpensesAboveAmount(LocalDate from, LocalDate to, BigDecimal amount) {
        return spendRepository.findExpensesGreaterThan(from, to, amount);
    }
}