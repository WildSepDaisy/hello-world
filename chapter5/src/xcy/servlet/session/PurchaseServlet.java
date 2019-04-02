package xcy.servlet.session;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PurchaseServlet
 */
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		if(id==null) {
			String url = "/chapter5/ListBookServlet";
			response.sendRedirect(url);//如果id为null，重定向到ListBookServlet页面
			return;
		}
		Book book = BookDB.getBook(id);
		HttpSession session = request.getSession();
		List<Book>cart = (List)session.getAttribute("cart");
		if(cart==null) {//首次购买，为用户创建一个购物车（List集合模拟购物车）
			cart = new ArrayList<Book>();
			session.setAttribute("cart", cart);
			
		}
		cart.add(book);
		Cookie cookie = new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(60*30);
		cookie.setPath("/chapter5");
		String url = "/chapter5/CartServlet";
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
