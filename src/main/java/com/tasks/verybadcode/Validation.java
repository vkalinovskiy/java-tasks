//неверный формат названия пакета
package somecompany.com;

//лучше подключать только используемые классы
import java.io.*;
import java.util.ArrayList;
//неиспользуемый класс
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class Validation {
    //Свойство класса должно быть protected/private
    //Используется абсолютный путь к файлу с привязкой к операционной системе
    public String inputText = "C:\\WorkFiles\\input.txt";

    //Неверный формат javadoc
    /**
     * @param name
     * @return
     */
    //Название метода должно быть в camelCase
    //ArrayList<Object> name стоит заменить на List<String> nameList
    public Boolean check_validation(ArrayList<Object> name) {
        List<String> errors = new ArrayList<>();
        //стоит заменить на Sting name : nameList
        for (Object name1 : name) {
            if (!errors.contains(name1)) {
                //Каждый раз открывается файл
                if (GetFile().contains(name1)) {
                    //с учетом правок выше, приведение не нужно
                    errors.add((String) name1);
                }
            }
        }

        //стоит использовать try-with-resource
        BufferedWriter writer;
        try {
            //пусть к файлу следует вынести в отдельную переменную
            writer = new BufferedWriter(new FileWriter(new File("C:\\WorkFiles\\output.txt")));
            //лучше использовать String.join для формирования строки
            IntStream.range(0, errors.size()).forEach(i -> handleError(writer, errors.get(i), i, errors.size()));
            writer.flush();
            //стоит отслеживать IOException
        } catch (Exception e) {
            //нет смысла выбрасывать пустое исключение
            throw new RuntimeException();
        }
        //можно заменить на return errors.isEmpty()
        if (errors.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    //приватный метод не стоит делать статическим
    //неочевидынй названия параметров
    private static void handleError(BufferedWriter writer, String error, int i, int s) {
        try {
            writer.append(error);
            if (i < s - 1) {
                writer.append(";");
            }
        //стоит отслеживать IOException
        } catch (Exception e) {
            System.out.println("Bad error:" + e);
            //не подходящий по логике exception
            throw new ClassCastException();
        }
    }

    //название метода должно быть в camelCase
    //метод отвечающий за реализацию должен быть скрытым
    public List<String> GetFile() {
        try {
            //стоит использовать try-with-resource
            //неочеваижные названия переменных
            FileReader r = new FileReader(new File(inputText));
            BufferedReader r1 = new BufferedReader(r);
            //для цикла можно использовать StringBuilder
            String text = "";
            //неочевидное название переменной
            String s;
            //лишние скобки
            while (((s = r1.readLine()) != null))
                text += s;
            List<String> result = new ArrayList<>();
            String[] result1 = text.split(";");
            //ненужный цикл, метод split() уже вернул список
            for (Integer indexOfName = 0; indexOfName < result1.length; indexOfName++) {
                result.add(result1[indexOfName]);
            }
            return result;
            //отлавливаются все ошибки включая jvm
        } catch (Throwable t1) {
        }
        return null;
    }

}