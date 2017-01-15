package eisnow.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liqiang
 *
 */
@Controller
public class KaptchaController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/psw", method = RequestMethod.GET)
	public String changePsw(Model model) {
		return "psw";
	}

}
