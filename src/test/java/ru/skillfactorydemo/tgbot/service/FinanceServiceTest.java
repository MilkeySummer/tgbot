package ru.skillfactorydemo.tgbot.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skillfactorydemo.tgbot.entity.Income;
import ru.skillfactorydemo.tgbot.entity.Spend;
import ru.skillfactorydemo.tgbot.repository.IncomeRepository;
import ru.skillfactorydemo.tgbot.repository.SpendRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FinanceServiceTest {

    @Mock
    private SpendRepository spendRepository;

    @Mock
    private IncomeRepository incomeRepository;

    private FinanceService financeService;
    private long testStartTime;

    @BeforeEach
    public void setUp() {
        financeService = new FinanceService(incomeRepository, spendRepository);
        testStartTime = System.nanoTime(); // Наносекунды для более точного замера

        // Красивое форматирование времени
        String formattedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
        System.out.println("⏰ Тест начат в: " + formattedTime);
    }

    @AfterEach
    public void tearDown() {
        long duration = System.nanoTime() - testStartTime;
        double millis = duration / 1_000_000.0;

        String formattedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
        System.out.println("⏰ Тест завершен в: " + formattedTime);
        System.out.println("⏱️  Длительность: " + String.format("%.2f", millis) + " мс");
        System.out.println("----------------------------------------");
    }

    @DisplayName("ADD_INCOME_test - проверка добавления дохода")
    @Test
    public void addFinanceOperationAddIncomeTest() {
        when(incomeRepository.save(any(Income.class))).thenReturn(new Income());

        String price = "150.0";
        Long chatId = 500L;

        String message = financeService.addFinanceOperation("/addincome", price, chatId);

        Assertions.assertEquals("Доход в размере " + price + " был успешно добавлен", message);
        verify(incomeRepository, times(1)).save(any(Income.class));
        verify(spendRepository, never()).save(any(Spend.class));
    }

    @DisplayName("non_ADD_INCOME_test - проверка добавления расхода")
    @Test
    public void addFinanceOperationElseBranchTest() {
        when(spendRepository.save(any(Spend.class))).thenReturn(new Spend());

        String price = "200.0";
        Long chatId = 250L;

        String message = financeService.addFinanceOperation("/nan", price, chatId);

        Assertions.assertEquals("Расход в размере " + price + " был успешно добавлен", message);
        verify(spendRepository, times(1)).save(any(Spend.class));
        verify(incomeRepository, never()).save(any(Income.class));
    }

    @DisplayName("ADD_INCOME_case_insensitive - проверка регистронезависимости")
    @Test
    public void addFinanceOperationCaseInsensitiveTest() {
        when(incomeRepository.save(any(Income.class))).thenReturn(new Income());

        String price = "300.0";
        Long chatId = 100L;

        String message = financeService.addFinanceOperation("/ADDINCOME", price, chatId);

        Assertions.assertEquals("Доход в размере " + price + " был успешно добавлен", message);
        verify(incomeRepository, times(1)).save(any(Income.class));
        verify(spendRepository, never()).save(any(Spend.class));
    }

    @DisplayName("invalid_price_test - проверка обработки неверного формата цены")
    @Test
    public void addFinanceOperationInvalidPriceTest() {
        String invalidPrice = "abc";
        Long chatId = 500L;

        Assertions.assertThrows(NumberFormatException.class, () -> {
            financeService.addFinanceOperation("/addincome", invalidPrice, chatId);
        });

        verify(incomeRepository, never()).save(any());
        verify(spendRepository, never()).save(any());
    }

    // Дополнительный тест, который показывает, как выглядит "медленный" тест
    @DisplayName("slow_test_demo - демонстрация замедления")
    @Test
    public void slowTestDemo() throws InterruptedException {
        // Имитируем "медленный" тест (например, с реальной БД или API)
        Thread.sleep(100); // 100 миллисекунд задержки
        Assertions.assertTrue(true);
    }
}