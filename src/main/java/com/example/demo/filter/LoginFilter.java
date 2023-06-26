package com.example.demo.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Investor;
import com.example.demo.repository.InvestorRepository;

/* Must annotate @ServletComponentScan into SpringBootPortfolio20230613Application.java  */
@WebFilter("/*")
public class LoginFilter extends BaseFilter {
	
	@Autowired
	private InvestorRepository investorRepository;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		System.out.println("test=======" + request.getParameter("investor_id"));
		
		if(request.getParameter("investor_id") != null){
			// 根據 investor_id 進行登入
			int id = Integer.parseInt(request.getParameter("investor_id"));			
			Investor investor = investorRepository.findById(id).get();
			// 將 investor 物件資料存入 session
			session.setAttribute("investor", investor);
			session.setAttribute("investor_id", investor.getId());
			session.setAttribute("investor_username", investor.getUsername());
			session.setAttribute("watch_id", investor.getWatchs().iterator().next().getId());
			// 將 session 資料印出
			System.out.println("session investor = " + investor);
		}
		chain.doFilter(request, response);
	} 
	
}
