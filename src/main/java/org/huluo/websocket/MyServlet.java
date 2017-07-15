package org.huluo.websocket;
  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  
  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.context.ApplicationContext;  
import org.springframework.web.context.support.WebApplicationContextUtils;  
import org.springframework.web.socket.server.HandshakeInterceptor;  
import org.springframework.web.socket.server.support.WebSocketHttpRequestHandler;  

@WebServlet(value = "/websocketJoinPoint",loadOnStartup = 1)
public class MyServlet extends HttpServlet {  
  
    private static final long serialVersionUID = 1954401864181530442L;  
  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
  
        this.doPost(req, resp);  
    }  
  
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
  
        ApplicationContext context = WebApplicationContextUtils  
                .getWebApplicationContext(req.getServletContext());

  
        WebSocketHttpRequestHandler handler = new WebSocketHttpRequestHandler(  
                context.getBean("myHandler", MyHandler.class));  
  
        List<HandshakeInterceptor> interceptors = new ArrayList<HandshakeInterceptor>();  
        interceptors.add(context.getBean("myInterceptor",  
                MyWebSocketHandshakeInterceptor.class));  
  
        handler.setHandshakeInterceptors(interceptors);  
  
        handler.handleRequest(req, resp);  
    }  
}  