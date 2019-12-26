### ДЗ 2

Найти максимальную длину строки в текстовом файле, разделителем считаем '\n'.

Предложить два наиболее оптимальных решения и добавить сравнительный benchmark.

При загрузке данных помнить что файлы бывают большими..

### Сборка
Gradle  

### Решение
Программа принимает путь к файлу через параметры командной строки

Class LongestWordBenchmark

Для тестирования организованы методы поиска:
 1. Через bufferedReader - ( более предпочтителен по результатам теста )
 2. Через Files.lines - с фильтрацией слов

### Результат беннчмарка JMH
[JMH log](https://github.com/GaiverK/Enterprise/blob/master/SearchLongestWord/benchmarkResult.txt)

#### Возникли сложности с JMH
Не сработала настройка через аннотации вида:
1. @BenchmarkMode(Mode.All) // Test all modes
2. @OutputTimeUnit(TimeUnit.MILLISECONDS) // Get res in miliseconds
3. @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
+ запуск org.openjdk.jmh.Main.main(args);

Через Options.Builder - работает отлично.

