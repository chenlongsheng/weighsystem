/**
 *
 */
package com.jeeplus.modules.threadPool.task;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author admin
 */

@Controller
public class TaskControl {

    @Autowired
    private AsyncServiceImpl asyncService;
    @Autowired
    Executor asyncServiceExecutor;

    /**
     * 跳转到注册页面
     *
     * @throws IOException
     */

    @RequestMapping("/async")
    public CompletableFuture<String> asyncMethod() {
        return CompletableFuture.supplyAsync(() -> {
            // 执行长时间运行的任务






            return null;
        }, asyncServiceExecutor);
    }

    @RequestMapping("/task")
    public void task() throws IOException {

        System.out.println("haha");


        // 调用service层的任务
//		asyncService.executeAsync();

    }

}
