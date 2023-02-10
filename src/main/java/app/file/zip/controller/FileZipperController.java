package app.file.zip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import app.file.zip.service.FileZipperService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class FileZipperController {
	
	@Autowired
	FileZipperService fileZipperService;
	
	@GetMapping(value = {"/","/home"})
	public ModelAndView homePage() {
		ModelAndView mo = new ModelAndView();
		mo.setViewName("index");
	    return mo;
	}

//	@PostMapping("/index")
//	public void getGeneralFile(@RequestParam("file") MultipartFile file,Model model) throws IOException{
//		log.info("Api Called through html");
//		fileZipperService.convertToZipFile(file);
//		model.addAttribute("res", "Downnload Zip File");
////		return "index";
//	}
}
