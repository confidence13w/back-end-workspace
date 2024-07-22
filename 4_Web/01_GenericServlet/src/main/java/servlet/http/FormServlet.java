package servlet.http;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩! -> 한글 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userId = request.getParameter("userId");
		System.out.println(userId);
		
		String userPwd = request.getParameter("userPwd");
		System.out.println(userPwd);
		
		String gender = request.getParameter("gender");
		System.out.println(gender);
		
		String[] menuList = request.getParameterValues("menu");
		System.out.print(Arrays.toString(menuList));
		
		// 응답!
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
        out.println("<h1>정보를 출력합니다..</h1>");
        out.println("<p>당신의 아이디는 " + userId + "</p>");
        out.println("<p>당신의 비밀번호는 " + userPwd + "</p>");
        String genderResult = "여자";
        if(gender.equals("M")) genderResult = "남자";
        out.println("<p>당신의 성별은 " + genderResult + "</p>");
        if(menuList != null) {
            out.println("<ul>");
        	for (String	menu : menuList) {
        		out.println("<li>" + menu + "</li>");
        	}
        	out.println("</ul>");
        }
        out.println("</body></html>");
        out.close();
	}

}
