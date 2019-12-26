### ДЗ 2

Найти максимальную длину строки в текстовом файле, разделителем считаем '\n'.

Предложить два наиболее оптимальных решения и добавить сравнительный benchmark.

При загрузке данных помнить что файлы бывают большими..

### Решение
Для тестирования организованы методы поиска:
 1. Через bufferedReader - ( более предпочтителен по результатам теста )
 2. Через Files.lines - с фильтрацией слов

### Результат беннчмарка JMH
|-HEAD-|

"searchLongestWordByBufferedReader"   "thrpt"   1,200   "2,744022"   "0,004990"    "ops/s"
"searchLongestWordByFilesWithFilter"  "thrpt"   1,200   "2,679109"   "0,004535"    "ops/s"
"searchLongestWordByBufferedReader"   "avgt"    1,200   "0,364556"   "0,000833"    "s/op"
"searchLongestWordByFilesWithFilter"  "avgt"    1,200   "0,372665"   "0,000757"    "s/op"
"searchLongestWordByBufferedReader"   "sample"  1,600   "0,363388"   "0,000275"    "s/op"
"searchLongestWordByFilesWithFilter"  "sample"  1,600   "0,373944"   "0,000554"    "s/op"
"searchLongestWordByBufferedReader"   "ss"      1,200   "0,361117"   "0,000983"    "s/op"
"searchLongestWordByFilesWithFilter"  "ss"      1,200   "0,606488"   "0,781724"    "s/op"

