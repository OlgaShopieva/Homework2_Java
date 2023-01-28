package org.example;

import java.io.FileWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.lang.Exception;


public class Main {
    public static void main(String[] args) {

    }

    static StringBuilder task_1() {
        // Дана json строка { {
        // "фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}}
        // Задача написать метод(ы), который распарсить строку и выдаст ответ вида:
        // Студент Иванов получил 5 по предмету Математика. Студент Петрова получил 4 по
        // предмету Информатика. Студент Краснов получил 5 по предмету Физика.
        // Используйте StringBuilder для подготовки ответа

        String result = "{ { \"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}}\n";

        result = result.replaceAll("фамилия", "");
        result = result.replaceAll("оценка", "");
        result = result.replaceAll("предмет", "");
        result = result.replaceAll("\"\"", " ");
        result = result.replaceAll("\"", "");
        result = result.replaceAll("[{: ]", "");
        String[] array = result.split("},");
        String[] data_Array = new String[3];
        String[] text_Array = {"Студент ", " получил ", " по предмету "};
        StringBuilder result_text = new StringBuilder("");
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].replaceAll("[}}]", "");
            data_Array = array[i].split(",");
            for (int j = 0; j < text_Array.length; j++) {
                result_text.append(text_Array[j]);
                result_text.append(data_Array[j]);
            }
            result_text.append(". ");
        }
        System.out.println(result_text);
        return result_text;
    }

    static void task_2(StringBuilder string) {
        // Создать метод, который запишет результат работы в файл Обработайте исключения
        // и запишите ошибки в лог файл
        Logger logger = Logger.getAnonymousLogger();
        SimpleFormatter formatter = new SimpleFormatter();
        try {
            FileHandler file_Handler = new FileHandler("log.txt");
            file_Handler.setFormatter(formatter);
            logger.addHandler(file_Handler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            FileWriter writer = new FileWriter("text_hw2-2.txt", true);
            writer.write(string + "\n");
            writer.close();
            logger.log(Level.INFO, "Программа работает корректно");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "Возникла ошибка");
        }
    }
}
