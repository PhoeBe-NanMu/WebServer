package web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServerNet {

    public static void main(String[] args){
        new WebServerNet().start("1111");
    }
    private ServerSocket serverSocket;
    //点击“开始”按钮出现的结果
    public void start(String port) {
    //对异常进行处理
        try {
            serverSocket=new ServerSocket(Integer.parseInt(port));
            new Thread(){
                //对于线程重写run()
                public void run() {
                    try{
                        while(true) {
                            Socket socket=serverSocket.accept();//允许与服务器连接
                            new HandlerThread(socket).start();//开启线程
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop() {
        try{
            if(serverSocket!=null)
                serverSocket.close();
        }catch(Exception e) {
        }
    }
    //增加新的线程
    class HandlerThread extends Thread {
        private Socket socket;
        private String hostName;

        public HandlerThread(Socket socket) {
            this.socket = socket;
            this.hostName = socket.getInetAddress().getHostAddress();
        }

        //重写 run()
        public void run() {
                    InputStream input = null;
                    OutputStream output = null;
                    try {

                        input = socket.getInputStream();
                        output = socket.getOutputStream();



                        // 创建Request对象并解析
                        Request request = new Request(input);
                        request.parse();


                        // 创建 web.Response 对象
                        Response response = new Response(output);
                        response.setRequest(request);



                            if (request.getUri().startsWith("/servlet/")) {
                                //请求uri以/servlet/开头，表示servlet请求
                                ServletProcessor processor = new ServletProcessor();
                                processor.process(request, response);
                            } else {
                                //静态资源请求
                                StaticProcessor processor = new StaticProcessor();
                                processor.process(request, response);
                            }




                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            input.close();
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

        }
    }
}

