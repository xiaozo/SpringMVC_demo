package controller;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.sun.net.httpserver.Authenticator;
import exception.BaseException;
import model.Param;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import service.UserService;

//放入sesson   mav.addObject("user", user); 同时会放到session中
// types = {String.class}：只要类型是String的session的值就会放到session中
@SessionAttributes(value={"user"},types = {String.class})
@Controller
@RequestMapping(value = "",produces = "application/json; charset=utf-8")
public class HelloController {

    @Autowired
    private UserService userService;

    public void getUser(@RequestParam(value="id",required = false) Integer id,Map<Object,Object>map) {
        User user = new User();
        user.setUserName("ccc");
        //放到请求域中
        map.put("user",user);
    }
    @RequestMapping("/hello")
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("hello");
        User user = new User();
        user.setUserName("abc");

        mav.addObject("message", "Hello Spring MVC");
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/form")
    public ModelAndView form(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("form");
        return mav;
    }

//    @RequestMapping("/param")
//    public ModelAndView getParam(HttpServletRequest request,
//                                 HttpServletResponse response) {
//
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//
//        System.out.println(userName);
//        System.out.println(password);
//        return null;
//    }

    @ResponseBody
    @RequestMapping(value="/param")
    public String getParam(@Validated Param user, BindingResult result, HttpServletRequest httpServletRequest) {

        if (result.hasErrors()) {
            List <ObjectError> allErros = result.getAllErrors();
            for (ObjectError objectError : allErros) {
                System.out.println(objectError.getDefaultMessage());
                throw new BaseException("-1",objectError.getDefaultMessage());
            }
        }


        String jsonString = JSON.toJSONString(user);
        return jsonString;



    }

    @RequestMapping(value="/exception" ,method = RequestMethod.GET)
    public ModelAndView exception() {

//        ModelAndView mv = new ModelAndView();
//        /*  使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */
//        FastJsonJsonView view = new FastJsonJsonView();
//        Map<String, Object> attributes = new HashMap<String, Object>();
//        attributes.put("code", "1000001");
//        attributes.put("msg", "msg");
//        view.setAttributesMap(attributes);
//        mv.setView(view);
//        return mv;
        BaseException exception = new BaseException("-1","测试异常");
        throw exception;
//        return null;


    }

    @ResponseBody
    @RequestMapping(value="/user",method = RequestMethod.GET)
    public String QueryUser(){
        List<User> username = userService.getUserService(1);
        System.out.println(username);
        String jsonString = JSON.toJSONString(username);
        return jsonString;
    }

    @RequestMapping(value="/maps",method = RequestMethod.GET)
    public String maps(Map<Object,Object>map){
        User user = new User();
        user.setUserName("at");
        //放到请求域中
        map.put("user",user);
        return "success";
    }


}
