package com.jafcalvente.spring.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MainController {

	/**
	 * Initial method. Set the initial values.
	 * @return JSON with initial values.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initialValues() {
		return "{\"getInitialValue\":1, \"postInitialValue\":1}";
	}

    @RequestMapping(value="/addClick", method = RequestMethod.GET)
    public @ResponseBody String addClickGet(@RequestParam("currentNumber") String num){
    	String newNum = (new Integer(num) + 1) + "";
    	return newNum;
    }

    @RequestMapping(value="/addClick", method = RequestMethod.POST)
    public @ResponseBody String addClickPost(@RequestParam("currentNumber") String num){
    	String newNum = (new Integer(num) + 1) + "";
    	return newNum;
    }

	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public String getGreeting(@PathVariable("name") String msg) {
		return "Hello " + msg + "!!!";
	}

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
    	
    	StringBuilder sb = new StringBuilder("c://");
    	sb.append(file.getOriginalFilename());
    	
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(sb.toString())));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + file.getOriginalFilename() + "!";
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getName() + " because the file was empty.";
        }
    }
    
}
