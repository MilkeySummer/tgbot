package ru.skillfactorydemo.tgbot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import ru.skillfactorydemo.tgbot.entity.Income;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IncomeRepositoryTest {

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    public void testRepo() {
        long countBefore = incomeRepository.count();

        for (int i = 0; i < 10; i++) {
            incomeRepository.save(new Income());
        }

        long countAfter = incomeRepository.count();
        assertEquals(10, countAfter - countBefore);
    }

    @Test
    public void testDataScripts() {
        Optional<Income> byId = incomeRepository.findById(12345L);
        assertTrue(byId.isPresent());
        assertEquals(new BigDecimal("3000.00"), byId.get().getIncome());
    }
}