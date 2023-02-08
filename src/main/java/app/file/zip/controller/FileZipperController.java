package app.file.zip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
public class FileZipperController {
	
	@GetMapping(value = {"/","index","home"})
	public ModelAndView HomePage() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index");
	    return modelAndView;
	}


	public ResponseEntity<Object> getGeneralFile(@RequestPart MultipartFile file){
		System.out.println("File Name : "+file.getOriginalFilename());
		return null;
	}
}
