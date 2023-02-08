package app.file.zip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "app.file")
public class FileZipperApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileZipperApplication.class, args);
	}

}
