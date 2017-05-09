package com.company.debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import com.psddev.dari.util.IoUtils;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

public class ResourceFileReader {

    public static URL getFileAsURL(String resourcePath) throws IOException {
        return ResourceFileReader.class.getResource(StringUtils.ensureStart(resourcePath, "/"));
    }

    public static InputStream getFileAsInputStream(String resourcePath) throws IOException {
        return ResourceFileReader.class.getResourceAsStream(StringUtils.ensureStart(resourcePath, "/"));
    }

    public static <T> List<T> processFileByLine(String resourcePath, Function<String, T> lineFunction) throws IOException {

        List<T> output = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(getFileAsInputStream(resourcePath)))) {

            for (String line : (Iterable<String>) () -> buffer.lines().iterator()) {

                T value = lineFunction.apply(line);
                if (value != null) {
                    output.add(value);
                }
            }
        }

        return output;
    }

    public static void processFileByLine(String resourcePath, Consumer<String> lineConsumer) throws IOException {
        processFileByLine(resourcePath, (s) -> { lineConsumer.accept(s); return (Void) null; });
    }

    public static List<String> getFileLines(String resourcePath) throws IOException {
        return processFileByLine(resourcePath, Function.identity());
    }

    public static Object getFileAsJsonObject(String resourcePath) throws IOException {
        return ObjectUtils.fromJson(IoUtils.toString(getFileAsInputStream(resourcePath), StandardCharsets.UTF_8));
    }

    public static List<Object> getFileAsJsonArray(String resourcePath) throws IOException {

        try {
            Object object = getFileAsJsonObject(resourcePath);

            if (object instanceof List) {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) object;
                return list;
            }

        } catch (RuntimeException e) {
            // ignore
        }

        return new ArrayList<>();
    }

    public static Map<String, Object> getFileAsJsonMap(String resourcePath) throws IOException {

        try {
            Object object = getFileAsJsonObject(resourcePath);

            if (object instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                return map;
            }

        } catch (RuntimeException e) {
            // ignore
        }

        return new LinkedHashMap<>();
    }
}
