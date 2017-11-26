package sample.spring.chapter15.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public String showUploadForm() {
		return "uploadForm";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(
			@RequestParam("myFileField") MultipartFile file) throws IOException {
		ModelMap modelData = new ModelMap();
		
		if (!file.isEmpty()) {
			// -- save the uploaded file on the filesystem
			String successMessage = "File successfully uploaded";
			modelData.put("uploadMessage", successMessage);
			return new ModelAndView("uploadForm", modelData);
		}
		String failureMessage = "File couldn't be uploaded.";
		modelData.put("uploadMessage", failureMessage);
		return new ModelAndView("uploadForm", modelData);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException() {
		ModelMap modelData = new ModelMap();
		String failureMessage = "Exception occurred during upload.";
		modelData.put("uploadMessage", failureMessage);
		return new ModelAndView("uploadForm", modelData);
	}
}