package app.file.zip.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.file.zip.service.FileZipperService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class FileZipperController {
	
	@Autowired
	FileZipperService fileZipperService;
	
	@GetMapping(value = {"/","/home"})
	public String homePage() {
	    return "index";
	}

	@PostMapping("/index")
	public void getGeneralFile(@RequestParam("file") MultipartFile file,Model model) throws IOException{
		log.info("Api Called through html");
		fileZipperService.convertToZipFile(file);
		model.addAttribute("res", "Downnload Zip File");
//		return "index";
	}
}
