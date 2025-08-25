# dannil-file-filter
Утилита фильтрации содержимого файлов:
- Разделяет входные данные на **integers**, **floats**, **strings**
- Поддерживает опции: `-o` (путь вывода), `-p` (префикс), `-a` (append), `-s` (short stats), `-f` (full stats)

## Стек технологий:
- Java 22, Maven 3.9.9
## Библиотеки:
- jcommander 1.82

## Инструкция по запуску:
Используя IDE Inteleji:  
Комбинация Alt+Shift+F10 -> Edite Configurations в Program arguments вписать команду  
Пример команды:  
```-o=src/main/resources/files/out -p=sample_ -s src/main/resources/files/in/in1.txt src/main/resources/files/in/in2.txt```

## Возможности
- Делит входные строки на:
    - **integers** → `<prefix>integers.txt`
    - **floats** → `<prefix>floats.txt`
    - **strings** → `<prefix>strings.txt`
- Поддерживает опции:
    - `-o` — путь к папке для вывода (по умолчанию текущая)
    - `-p` — префикс для имён файлов
    - `-a` — режим добавления (append)
    - `-s` — короткая статистика
    - `-f` — полная статистика
- Обрабатывает ошибки:
    - отсутствующие файлы пропускаются
    - некорректные строки определяются как текст (по типу 2.2.2)

## Сборка
```bash
mvn clean package -DskipTests