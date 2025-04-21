import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String FNAME = "notext.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    \nОберіть опцію :
                    1. Записати до файлу
                    2. Прочитати вміст файлу
                    3. Вивести діапазон рядків
                    4. Вийти
                    """);
            System.out.println("Тут :");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    wrFile(sc);
                } else if (choice == 2) {
                    rFile();
                } else if (choice == 3) {
                    rRange(sc);
                } else if (choice == 4) {
                    System.out.println("До побачення!");
                    sc.close();
                    return;
                } else {
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
                }
            } else {
                System.out.println("Будь ласка, введіть число!");
                sc.next();
            }
        }
    }

    public static void wrFile(Scanner sc) {
        System.out.println("Введіть кілька рядків (введіть 'exit' для завершення):");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FNAME, true))) {
            int currentLineNumber = countLinesInFile() + 1;

            while (true) {
                System.out.print(currentLineNumber + ": ");
                String line = sc.nextLine();
                if (line.equals("exit")) {
                    break;
                }
                writer.write(line);
                writer.newLine();
                currentLineNumber = currentLineNumber + 1;
            }

            System.out.println("\nТекст успішно записано до файлу.");
        } catch (IOException e) {
            System.out.println("Помилка при записі до файлу: " + e.getMessage());
        }
    }

    public static void rFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FNAME))) {
            String line;
            int lineNumber = 1;
            System.out.println("\nВміст файлу:");
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber = lineNumber + 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не існує.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }

    public static void rRange(Scanner sc) {
        System.out.print("Введіть номер початкового рядка: ");
        int start = sc.nextInt();
        System.out.print("Введіть номер кінцевого рядка: ");
        int end = sc.nextInt();
        sc.nextLine();
        if (start <= 0 || end < start) {
            System.out.println("Некоректний діапазон.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FNAME))) {
            String line;
            int lineNumber = 1;
            System.out.println("\nРядки з " + start + " по " + end + ":");

            while ((line = reader.readLine()) != null) {
                if (lineNumber >= start && lineNumber <= end) {
                    System.out.println(lineNumber + ": " + line);
                }
                if (lineNumber > end) break;
                lineNumber = lineNumber + 1;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не існує.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }

    public static int countLinesInFile() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FNAME))) {
            while (reader.readLine() != null) {
                count = count + 1;
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
        return count;
    }
}
