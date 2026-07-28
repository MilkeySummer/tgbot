package ru.skillfactorydemo.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillfactorydemo.tgbot.entity.Spend;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpendRepository extends JpaRepository<Spend, Long> {
    @Query("SELECT s FROM Spend s WHERE s.operationDate BETWEEN :from AND :to AND s.spend > :amount")
    List<Spend> findExpensesGreaterThan(@Param("from") LocalDate from,
                                        @Param("to") LocalDate to,
                                        @Param("amount") java.math.BigDecimal amount);
}
