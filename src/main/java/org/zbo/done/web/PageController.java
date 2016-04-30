package org.zbo.done.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zbo on 4/24/16.
 */
@Controller
public class PageController {
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    @RequestMapping(value = "bootstrap/index", method = RequestMethod.GET)
    public String init_index(Map<String, Object> model) {
        return "bootstrap/pages/index.jsp";
    }

    @RequestMapping(value = "bootstrap/login", method = RequestMethod.GET)
    public ModelAndView init_login(
                     @RequestParam(value = "error", required = false) String error,
                     @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
                model.addObject("error", "Invalid username and password!");
            }

        if (logout != null) {
                model.addObject("msg", "You've been logged out successfully.");
            }
        model.addObject("testobj","just for test");
        model.setViewName("bootstrap/pages/login.jsp");

        return model;
    }

}
