package com.example.demo.filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class BaseFilter extends HttpFilter {
	/*
	 	傳統 JSP Filter 無法使用 Spring Bean Autowired Injection
	 	
	 	藉由使用底下配置，讓需要使用 Autowired Injection 的 Class 放入 'getClass()' 中  
	 		
	 		public void init() throws ServletException{
				SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(getClass(), getServletContext());
			}
	*/
	@Override
	public void init() throws ServletException{
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}
	
}
