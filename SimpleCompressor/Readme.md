### Simple Java Code Compressor
Программа архивирует файл в зависимости от выбранного типа zip или gzip

#### Параметры
На входе программа ожидает 2 параметра через Scanner
1. Путь к файлу источнику (Пример: /test.txt)
2. Путь архивации (Пример: /test.zip или /test.txt.gz) - тип архивации зависит от переданного параметра

#### Способ запуска
Сборки не использовались
добавить как Simple Java project


##### Исправления
DeflaterOutputStream - заменен на OutputStream - проанализированы преимущества
OutputStream zs = strategy.compress(ous) - перенесен в try with resources, ушел лишний close