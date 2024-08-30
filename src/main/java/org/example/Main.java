package org.example;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> data = new HashMap<>();
        data.put("three", "five");
        Map<String, Object> data2 = new HashMap<>();
        data2.put("test", "test");
        data.put("second", data2);

        Person person = new Person("John", data);

        String result = compileReadString(person);
        String resultForFile = compile(person);
        System.out.println(result);
        System.out.println(resultForFile);
    }

    public static String compile(Object object) {
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();
        File f = new File("src/main/resources/templates/router.mustache"); // Ajusta la ruta aquí
        try {
            StringWriter writer = new StringWriter();
            Mustache mustache = mustacheFactory.compile(new InputStreamReader(Files.newInputStream(f.toPath()), StandardCharsets.UTF_8),f.getName());
            mustache.execute(writer, object).flush();
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String compileReadString(Object object) {
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();

        String templateString = "{\n" +
                "\"name\": \"{{name}}\",\n" +
                "\"age\": \"{{_age}}\",\n" +
                "\"other_data\": \"{{data.second.test}}\"\n" +
                "}";

        InputStream inputStream = new ByteArrayInputStream(templateString.getBytes(StandardCharsets.UTF_8));
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        try {
            StringWriter writer = new StringWriter();
            Mustache mustache = mustacheFactory.compile(reader, "template");
            mustache.execute(writer, object).flush();
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

// https://v0.dev/r/YmcuGLbc14d

// https://github.com/janl/mustache.js