package web;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Locale;


public class Response implements HttpServletResponse {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
    BufferedInputStream fin;
    File srcFile;

    public Response(OutputStream output) {
            this.output = output;
          }


    public void setRequest(Request request) {
        this.request = request;
    }

    //将web文件写入到OutputStream字节流中
    public void sendStaticResource() throws IOException {

        String src = request.getUri();
        System.out.println(src);
        if ("/".equals(src)) src = "/index.html";
        srcFile = new File(Constants.WEB_ROOT + src);
        //如果浏览器访问的页面不存在,则返回 404.html 页面内容
        if (!srcFile.exists() || !srcFile.canRead()) {
            srcFile = new File(Constants.WEB_ROOT+"/404.html");
        }
//        //输出响应头部信息
//        out.write("HTTP/1.1 200 OK".getBytes());
//        out.write("Server:First Server".getBytes());
//        out.write(("The least change time:" + new Date(srcFile.lastModified())).getBytes());
//        out.write(("Content-type:" + getContentType(srcFile)).getBytes());
//        out.write(("Content-length:" + srcFile.length()).getBytes());
//        out.write(("Time:" + new Date()).getBytes());
//        System.out.println("---------");
//        /**
//         * 为了告知 Web浏览器传送内容的类型，Web服务器首先传送一些HTTP头信息，
//         * 然后传送具体内容（即HTTP体信息），HTTP头信息和HTTP体信息之间用一个空行分开。
//         */
//        out.write("\r\n".getBytes());  //空行分开
//        //输出响应体部信息
        fin = new BufferedInputStream(new FileInputStream(srcFile));
        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = fin.read(buffer)) != -1) {
            output.write(buffer, 0, i);
        }
    }

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String s) {
        return false;
    }

    @Override
    public String encodeURL(String s) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String s) {
        return null;
    }

    @Override
    public String encodeUrl(String s) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String s) {
        return null;
    }

    @Override
    public void sendError(int i, String s) throws IOException {

    }

    @Override
    public void sendError(int i) throws IOException {

    }

    @Override
    public void sendRedirect(String s) throws IOException {

    }

    @Override
    public void setDateHeader(String s, long l) {

    }

    @Override
    public void addDateHeader(String s, long l) {

    }

    @Override
    public void setHeader(String s, String s1) {

    }

    @Override
    public void addHeader(String s, String s1) {

    }

    @Override
    public void setIntHeader(String s, int i) {

    }

    @Override
    public void addIntHeader(String s, int i) {

    }

    @Override
    public void setStatus(int i) {

    }

    @Override
    public void setStatus(int i, String s) {

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getHeader(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {

            return new PrintWriter(output,false);

    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }



}