package com.dmz.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dmz.basic.Response;
import com.dmz.basic.model.Login;
import com.dmz.service.implement.A;
import com.dmz.service.implement.AService;
import com.dmz.service.implement.CService;
import com.dmz.service.utils.CreateImageCode;
import com.dmz.web.advisor.TracingRquestHandlerInterceptor;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmz on 2016/3/28.
 * 当然，@ResponseBody注解也可以定义在类上，这样所有的方法都继承了该特性。
 * 由于经常会使用到@ResponseBody注解，所以Spring提供了一个名为@RestController的注解来取代以上的@Controller注解，
 * 这样我们就可以省略返回值前面的@ResponseBody注解了，但参数前面的@RequestBody注解是无法省略的
 */

@Controller
@RequestMapping("/LoginFront")
@Validated // 配合 call方法中的直接validate ,  MethodValidationPostProcessor
public class LoginFront {

    private static Logger logger = LoggerFactory.getLogger(TracingRquestHandlerInterceptor.class);


    @Resource
    private AService aService;

    @Resource
    private A bService;

    @Resource
    private CService cService;

    //@Resource
    //private ILoginService loginService;

//    @Resource
//    private IUserService userService;

    @RequestMapping(value = "/captcha", consumes = "application/json")
    @ResponseBody
    public void Captcha(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());

        String resStr = "{}";

        //自定义userid
        String userid = "test";

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(userid);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        resStr = gtSdk.getResponseStr();

        PrintWriter out = response.getWriter();
        out.println(resStr);
//        return "HelloWorld";
    }

    //@RequestMapping(value = "/details/{id}", method = RequestMethod.POST)
    //public String LoginDetails(@PathVariable("id") long userId, ModelMap model) {
    //    Login loginDetail = null;
    //    try {
    //        loginDetail = loginService.getLoginInfoById(userId);
    //    } catch (DBException.DBServerException e) {
    //        e.printStackTrace();
    //    } catch (DBException.EmptyData emptyData) {
    //        emptyData.printStackTrace();
    //    } catch (DBException.MultipleData multipleData) {
    //        multipleData.printStackTrace();
    //    }
    //    model.addAttribute("login", loginDetail);
    //    return "loginDetail";
    //}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void Login(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //从session中获取userid
        String userid = (String) request.getSession().getAttribute("userid");

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证

            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }


        if (gtResult == 1) {
            // 验证成功
            PrintWriter out = response.getWriter();
            out.println("success:" + gtSdk.getVersionInfo());
        } else {
            // 验证失败
            PrintWriter out = response.getWriter();
            out.println("fail:" + gtSdk.getVersionInfo());
        }
    }

    //@LoginDetail(login = true) // @Validate @Valid ALL FINE
    @RequestMapping(value = "/loginName", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Response LoginName(@Valid @RequestBody Login login, HttpServletRequest hrequest, HttpServletResponse hresponse) throws Exception {
        //logger.info("BindResult {}", result);
        //Response response = new Response();
        //System.out.println(result.getAllErrors());
        //String authType = hrequest.getAuthType();
        //if (loginService.checkUserLogin(login)) {
        //    return response.success();
        //} else {
        //    return response.failure("Name or Password is Wrong");
        //}
        System.out.println(login);
        return null;
    }

    @RequestMapping(value = "/dispatch")
    public String dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Dispatch", "DISPATCH");
        request.getRequestDispatcher("http://192.168.100.154:9999/hello").forward(request, response);
        return "index";

    }

    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("Dispatch", "DISPATCH");
//        response.sendRedirect("/LoginFront/commentLogin");
        response.sendRedirect("http://192.168.23.159:9999/hello");
        return "index";
    }

    @RequestMapping(value = "/commentLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public String CommetsLogin(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

//    @RequestMapping(value = "/submitcomments",method = {RequestMethod.POST})
//    @ResponseBody
//    public JSON SubmitComments(@RequestParam(value = "author") String author,@RequestParam(value = "text") String text, HttpServletResponse response){
//        response.setHeader("Access-Control-Allow-Origin", "*"); //跨域
//        return null;
//    }

    @RequestMapping(value = "/submitcomments", method = {RequestMethod.POST})
    @ResponseBody
    public JSON SubmitComments(@RequestBody MultiValueMap<String, String> json, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); //跨域
        return null;
    }

    //@RequestMapping(value = "/loginPhoto", method = {RequestMethod.POST})
    //@ResponseBody
    //public Response ShowLoginPhoto(@RequestBody Login login) {
    //    User user = loginService.showUserDetailByLoginName(login);
    //    Response response = new Response();
    //    if (user == null || StringUtils.isEmpty(user.getAvatar())) {
    //        return response.failure();
    //    }
    //    response.success(user.getAvatar());
    //    return response;
    //}

    @RequestMapping(value = "/captcha/{codeCount}/{lineCount}/{width}/{heigth}/{id}", method = {RequestMethod.GET})
    public void CreateImageCode(@PathVariable("codeCount") int codeCount, @PathVariable("id") long Id,
                                @PathVariable("lineCount") int lineCount,
                                @PathVariable("width") int width,
                                @PathVariable("heigth") int heigth,
                                HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println(codeCount + "   " + Id + "   " + lineCount + "  " + width + "  " + heigth);
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        CreateImageCode vCode = new CreateImageCode(width, heigth, codeCount, lineCount);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
    }

    @RequestMapping(value = "/indexWelcome", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "index";
    }


//    @RequestMapping(value = "/comments", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public JSONArray Comments2(HttpServletRequest request,
//                              HttpServletResponse response) {
//        Map<String, String> comment_one = new HashMap();
//        comment_one.put("1", "1");
//        comment_one.put("author", "Pete Hunt");
//        comment_one.put("text", "This is one comment");
//        JSONArray array = new JSONArray();
//        array.add(comment_one);
//        String origin = request.getHeader("Origin");
//        String method = request.getHeader("Access-Control-Request-Method");
//        String headers = request.getHeader("Access-Control-Request-Headers");
////        response.setHeader("Access-Control-Allow-Origin", "http://192.168.23.159:9999"); //跨域
//        return array;
//    }

    @RequestMapping(value = "/comments", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONArray Comments(HttpServletRequest request,
                              HttpServletResponse response) {
        Map<String, String> comment_one = new HashMap();
        comment_one.put("1", "1");
        comment_one.put("author", "Pete Hunt");
        comment_one.put("text", "This is one comment");
        Map<String, String> comment_two = new HashMap<>();
        comment_two.put("2", "2");
        comment_two.put("author", "Jordan Walke");
        comment_two.put("text", "This is *another* comment");
//        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
//        list.add(comment_one);
//        list.add(comment_two);
        JSONArray array = new JSONArray();
        array.add(comment_one);
        array.add(comment_two);
        String origin = request.getHeader("Origin");
        String method = request.getHeader("Access-Control-Request-Method");
        String headers = request.getHeader("Access-Control-Request-Headers");
//        response.setHeader("Access-Control-Allow-Origin", "http://192.168.23.159:9999"); //跨域
        return array;
    }

    @RequestMapping(value = "/resolve", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewsResolve(ModelMap model) {
        Login login = new Login();
        login.setLoginName("DMZ");
        model.put("login", login);
        return "login";
    }

    @RequestMapping(value = "/call0")
    public String callChain0(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("Dispatch", "DISPATCH");
        Login login = new Login();
        bService.b(login);

        bService.c(null);
//        response.sendRedirect("/LoginFront/commentLogin");
//        response.sendRedirect("http://192.168.100.154:9988/call1");
        return "index";
    }

    @RequestMapping(value = "/call1")
    public String callChain1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("Dispatch", "DISPATCH");
        logger.error("Start CallChain1 {}", request);
//        response.sendRedirect("/LoginFront/commentLogin");
//        response.sendRedirect("http://192.168.100.154:9988/call2");
//        bService.b();
        return "redirect:/LoginFront/call2";
    }


    @RequestMapping(value = "/call2")
    public String callChain2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("Dispatch", "DISPATCH");
        logger.error("Start CallChain3 {}", request);
//        response.sendRedirect("/LoginFront/commentLogin");
//        response.sendRedirect("http://192.168.100.154:9988/call1");
        cService.c();
        Login login = new Login();
        aService.b(login);
        return "index";
    }

    @RequestMapping(value = "/call")
    @ResponseBody
    public String callChain(@NotBlank String string) throws IOException {
//        response.sendRedirect("/LoginFront/commentLogin");
//        response.sendRedirect("http://192.168.100.154:9988/call1");
//        aService.a();

        Login login = new Login();
        bService.b(login);

        bService.c(null);
        return string;
    }

    //@InitBinder
    //protected void initBinder(WebDataBinder binder) {
    //    binder.setValidator(validator);
    //}
    //
    //@Autowired
    //private LocalValidatorFactoryBean validator;
}
