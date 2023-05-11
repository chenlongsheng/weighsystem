/**
 * 
 */
package com.jeeplus.modules.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author admin
 *
 */

@Configuration
@ComponentScan("com.jeeplus.modules.service")
public class ThreadStartService {
	
	
	 
	    @Bean(initMethod = "init", destroyMethod = "destroy")
	    ThreadInitService test1() {
	       return new ThreadInitService();
	    }
	
	
	
	

}
