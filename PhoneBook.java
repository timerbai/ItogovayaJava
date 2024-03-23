// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что в во входной структуре будут повторяющиеся имена
// с разными телефонами, их необходимо считать, как одного человека с разными телефонами.
// Вывод должен быть отсортирован по убыванию числа телефонов.

import java.io.*;
import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        Map<String, List<String>> phoneBook = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Введите имя или пустую строку: ");
                String name = scanner.nextLine();
                if (name.isEmpty()) {
                    break;
                }
                
                System.out.print("Введите номер тел в формате +70000000000: ");
                String phoneNumber = scanner.nextLine();

                phoneBook.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNumber);
            }

            try (PrintWriter writer = new PrintWriter("phones.txt")) {
                for (Map.Entry<String, List<String>> entry : phoneBook.entrySet()) {
                    writer.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Сортировка по убыванию чисел телефонов

        List<Map.Entry<String, List<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
        sortedEntries.sort(Comparator.comparing(entry -> -entry.getValue().size()));

        for (Map.Entry<String, List<String>> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}