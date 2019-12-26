### ДЗ 2

Найти максимальную длину строки в текстовом файле, разделителем считаем '\n'.

Предложить два наиболее оптимальных решения и добавить сравнительный benchmark.

При загрузке данных помнить что файлы бывают большими..

### Сборка
Gradle  

### Решение
Программа принимает путь к файлу через параметры командной строки

Class LongestLineBenchmark

Для тестирования организованы методы поиска:
 1. Через bufferedReader
 2. Через Files.lines

### Результат беннчмарка JMH
[benchmark result](https://github.com/GaiverK/Enterprise/blob/master/SearchLongestLine/benchmark.txt)


