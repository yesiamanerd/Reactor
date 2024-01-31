package com.rp.assignments;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Assignment01 {

    public static void main(String[] args) {
        // read a file and return content
        // read file 1 and return content
        readFile("File01.txt").subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        // read file 2 and return content
        readFile("File02.txt").subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        // create a file and write content
        Mono.fromRunnable(writeFile("File03.txt", "This is file 3"))
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        () -> {
                            System.out.println("process is done. Creating file...");
                        }
                );

        // read file 3 and return content
        readFile("File03.txt").subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        // delete a file
        Mono.fromRunnable(deleteFile("File03.txt"))
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        () -> {
                            System.out.println("process is done. Deleting file...");
                        }
                );
    }

    private static Mono<String> readFile(String fileName) {

        return Mono.fromSupplier(() -> {

            StringBuilder contentBuilder = new StringBuilder();
            String path = Paths.get("src", "main", "resources", fileName).toString();

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "Error reading the file";
            }

            return contentBuilder.toString();
        });
    }

    private static Runnable writeFile(String fileName, String content) {
        return () ->
        {
            String path = Paths.get("src", "main", "resources", fileName).toString();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                bw.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private static Runnable deleteFile(String fileName) {
        return () ->
        {
            Path path = Paths.get("src", "main", "resources", fileName);

            try {
                Files.delete(path);
                System.out.println(fileName + " was deleted successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error occurred while deleting the file.");
            }
        };
    }
}
