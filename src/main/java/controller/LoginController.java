package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :luo wei
 * @description: 拦截器测试controller
 * @date 2023/2/16/016 15:05
 */
@RestController
@RequestMapping("/test")
public class LoginController {

    @GetMapping("/login")
    public void login() {
        System.out.println(Thread.currentThread().getName() + " 登录了");
    }

}
