package task3;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                System.err.println("Ошибка: Необходимо указать 3 файла!");
                System.err.println("values.json tests.json report.json");
                System.exit(1);
            }

            String valuesPath = args[0];
            String testsPath = args[1];
            String reportPath = args[2];

            checkFileExists(valuesPath);
            checkFileExists(testsPath);

            Map<Integer, String> valuesMap = loadValues(valuesPath);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode testsRoot = processTestsFile(mapper, testsPath, valuesMap);
            saveReport(mapper, reportPath, testsRoot);

            System.out.println("Обработка завершена успешно!");

        } catch (IOException ex) {
            System.err.println("Ошибка: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void checkFileExists(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("Файл не найден: " + filePath);
        }
    }

    private static Map<Integer, String> loadValues(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(file));

        if (!root.has("values")) {
            throw new IOException("В файле отсутствует обязательное поле 'values'");
        }

        Map<Integer, String> valueMap = new HashMap<>();
        for (JsonNode node : root.get("values")) {
            if (!node.has("id") || !node.has("value")) {
                throw new IOException("Некорректная структура данных в файле values.json");
            }
            valueMap.put(node.get("id").asInt(), node.get("value").asText());
        }
        return valueMap;
    }

    private static JsonNode processTestsFile(ObjectMapper mapper, String file,
                                             Map<Integer, String> valuesMap) throws IOException {
        JsonNode testsRoot = mapper.readTree(new File(file));

        if (!testsRoot.has("tests")) {
            throw new IOException("В файле отсутствует обязательное поле 'tests'");
        }

        fillValues(testsRoot.get("tests"), valuesMap);
        return testsRoot;
    }

    private static void fillValues(JsonNode node, Map<Integer, String> valuesMap) {
        if (node.isArray()) {
            for (JsonNode item : node) {
                fillValues(item, valuesMap);
            }
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            if (objectNode.has("id")) {
                int id = objectNode.get("id").asInt();
                if (valuesMap.containsKey(id)) {
                    objectNode.put("value", valuesMap.get(id));
                }
            }
            if (objectNode.has("values")) {
                fillValues(objectNode.get("values"), valuesMap);
            }
        }
    }

    private static void saveReport(ObjectMapper mapper, String file,
                                   JsonNode reportData) throws IOException {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(file), reportData);
        } catch (IOException e) {
            throw new IOException("Не удалось сохранить отчет в файл: " + file, e);
        }
    }
}