# dannil-file-filter
Утилита фильтрации содержимого файлов:
- Разделяет входные данные на **integers**, **floats**, **strings**
- Поддерживает опции: `-o` (путь вывода), `-p` (префикс), `-a` (append), `-s` (short stats), `-f` (full stats)

### Стек технологий:
- Java 22, Maven 3.9.9
### Библиотеки:
- jcommander 1.82

### Инструкция по запуску:
Используя IDE Inteleji:  
Комбинация Alt+Shift+F10 -> Edite Configurations в Program arguments вписать команду  
Пример команды:  
```-o=src/main/resources/files/out -p=sample_ -s src/main/resources/files/in/in1.txt src/main/resources/files/in/in2.txt```