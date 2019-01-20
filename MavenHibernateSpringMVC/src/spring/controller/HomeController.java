package spring.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public ModelAndView home() throws IOException {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/index")
	public ModelAndView index() throws IOException {
		return new ModelAndView("index");
	}
}
