/**
 *
 */
package com.jeeplus.modules.threadPool.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.jeeplus.modules.service.ThreadInitService;

/**
 * @author admin
 *
 */
@Service
public class AsyncServiceImpl {


    @Async("asyncServiceExecutor")
    public void executeAsync(Thread thread) {
        System.out.println("888888");
        ThreadInitService.thread = thread;

        thread.start();


    }


}
 
