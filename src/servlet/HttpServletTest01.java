package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/HttpServletTest01")
public class HttpServletTest01 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HttpServletTest01() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out =response.getWriter();
		String title = "使用 GET 方法读取表单数据";
		// 处理中文
		String name =new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
		String docType = "<!DOCTYPE html> \n";
		String tts = docType +
				"<html>\n" +
				"<head>\n<meta charset=\"UTF-8\">"+"<title>" + title + "</title></head>\n" +
				"<body bgcolor=\"#f0f0f0\">\n" +
				"<h1 align=\"center\">" + title + "</h1>\n" +
				"<ul>\n" +
				"  <li><b>站点名</b>："
				+ name + "\n" +
				"  <li><b>网址</b>："
				+ request.getParameter("url") + "\n" +
				"</ul>\n" +
				"</body></html>";
		out.println(tts);
		out.flush();

	}
	
	// 处理 POST 方法请求的方法
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}