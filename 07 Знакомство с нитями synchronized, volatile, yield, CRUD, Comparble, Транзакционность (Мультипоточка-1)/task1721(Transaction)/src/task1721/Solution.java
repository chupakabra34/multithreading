package task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла.
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines.
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines.
4. Если условие из п.3 не выполнено, то:
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
Не забудь закрыть потоки.


Requirements:
1. Класс Solution должен содержать public static поле allLines типа List<String>.
2. Класс Solution должен содержать public static поле forRemoveLines типа List<String>.
3. Класс Solution должен содержать public void метод joinData() который может бросать исключение CorruptedDataException.
4. Программа должна считывать c консоли имена двух файлов.
5. Программа должна считывать построчно данные из первого файла в список allLines.
6. Программа должна считывать построчно данные из второго файла в список forRemoveLines.
7. Метод joinData должен удалить в списке allLines все строки из списка forRemoveLines, если в allLines содержаться все строки из списка forRemoveLines.
8. Метод joinData должен очистить список allLines и выбросить исключение CorruptedDataException, если в allLines не содержаться все строки из списка forRemoveLines.
9. Метод joinData должен вызываться в main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String fileName1 = sc.nextLine();
//        String fileName2 = sc.nextLine();
        String fileName1 = "file1.txt";
        String fileName2 = "file2.txt";

        try {
            BufferedReader file1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName1)));
            while (file1.ready()) {
                allLines.add(file1.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName1 + " not found");
        } catch (IOException e) {
            System.out.println("Can't Read File " + fileName1);
        }
        try {
            BufferedReader file2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName2)));
            while (file2.ready()) {
                forRemoveLines.add(file2.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName2 + " not found");
        } catch (IOException e) {
            System.out.println("Can't Read File " + fileName2);
        }
        try {
            new Solution().joinData();
        } catch (CorruptedDataException e) {
            System.out.println("Сработал CorruptedDataException");
        }
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
            System.out.println(allLines);
            System.out.println();
            System.out.println(forRemoveLines);
            return;
        } else {
            allLines.clear();
            System.out.println(allLines);
            System.out.println();
            System.out.println(forRemoveLines);
            throw new CorruptedDataException();
        }
    }
}
