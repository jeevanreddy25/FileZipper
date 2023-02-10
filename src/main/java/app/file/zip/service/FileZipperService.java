package app.file.zip.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileZipperService {

	public String convertToZipFile(MultipartFile multipartFile) throws IOException {
		log.info("Entered into conversion method....");
		File file = convertFile(multipartFile);
		String originalFileName = multipartFile.getOriginalFilename();
		log.info("originalFileName : "+originalFileName);
		String fileName = originalFileName.substring(0, originalFileName.indexOf("."));
		String suffix = originalFileName.substring(originalFileName.indexOf("."), originalFileName.length());
		FileInputStream fis = new FileInputStream(file);
	    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileName+".zip"));
	    zos.putNextEntry(new ZipEntry(fileName+suffix));
	    byte[] buffer = new byte[25*1024*1024];
	    int bytesRead;
	    while((bytesRead=fis.read(buffer))>0) {
	        zos.write(buffer,0,bytesRead);
	    }
	    zos.closeEntry();
	    zos.close();
	    fis.close();
	    File zipFile = new File(fileName+".zip");
	    downLoadZipeFile(zipFile);
		return "Success";
	}

	private ResponseEntity<Object> downLoadZipeFile(File zippedFile) throws IOException {
		InputStreamResource res = new InputStreamResource(new FileInputStream(zippedFile));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment;filename=\"%s\"", zippedFile.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return ResponseEntity.ok().headers(headers).contentLength(zippedFile.length())
				.contentType(MediaType.parseMediaType("application/txt")).body(res);
	}

	private File convertFile(MultipartFile multipartFile) throws IOException {
		String originalFileName = multipartFile.getOriginalFilename();
		log.info("File Name : "+originalFileName);
		String prefix = originalFileName.substring(0, originalFileName.indexOf("."));
		log.info("Prefix : "+ prefix);
		String suffix = originalFileName.substring(originalFileName.indexOf("."), originalFileName.length());
		log.info("suffix : "+ suffix);
		File tempFile = File.createTempFile(prefix, suffix);
		tempFile.deleteOnExit();
		multipartFile.transferTo(tempFile);
		return tempFile;
	}
}
