package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

//import ex02.pyrmont.web.Constants;web.Constants
//import ex02.pyrmont.web.Request;
//import ex02.pyrmont.web.Response;

public class ServletProcessor {

    public void process(Request request, Response response) {

        String uri = request.getUri();
//        if (!uri.startsWith("/servlet/")){
//            uri = "/servlet/" + uri;
//        }
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        // 类加载器，用于从指定JAR文件或目录加载类
        URLClassLoader loader = null;
        try {
            URLStreamHandler streamHandler = null;
            // 创建类加载器
            loader = new URLClassLoader(new URL[]{new URL(null, "file:"
                    + Constants.SERVLET_ROOT, streamHandler)});
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class<?> myClass = null;
        try {
            // 加载对应的servlet类
            myClass = loader.loadClass(Constants.SERVLET_ROOT_CLASS+servletName);

            System.out.println(myClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        HttpServlet servlet;
        System.out.println("123456");
        try {
            servlet = (HttpServlet) myClass.newInstance();
            System.out.println("789"+servlet.toString());
            servlet.service(request,response);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}