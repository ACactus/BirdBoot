package com.webserver.Controller;

import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/21 16:25
 */
@Controller
public class ArticleController {
    @RequestMapping("/writeArticle")
    public void writerArticle(HttpServletRequest request, HttpServletResponse response){
        System.out.println("正在发表文章.....");
    }
}
