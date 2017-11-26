package sample.spring.chapter10.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "sayHelloController")
@RequestMapping("/saySomething")
public class HelloWorldController {

	@RequestMapping("/sayhello")
	public ModelAndView sayHello() {
		Map<String, String> modelData = new HashMap<String, String>();
		modelData.put("msg", "Hello World !!");
		return new ModelAndView("helloworld", modelData);
	}

}
