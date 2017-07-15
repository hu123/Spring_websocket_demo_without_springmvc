package org.huluo.websocket;
  
import java.util.Map;  
  
import javax.servlet.http.HttpServletRequest;  
  
import org.springframework.http.server.ServerHttpRequest;  
import org.springframework.http.server.ServerHttpResponse;  
import org.springframework.http.server.ServletServerHttpRequest;  
import org.springframework.web.socket.WebSocketHandler;  
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;  
  
public class MyWebSocketHandshakeInterceptor extends  
        HttpSessionHandshakeInterceptor {  
  
    @Override  
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest,  
            ServerHttpResponse serverHttpResponse, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
  
        System.out.println("hi get request.");  
  
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;  
        HttpServletRequest request = servletRequest.getServletRequest();  
  
        String userName = request.getParameter("userName");  
        attributes.put("userName", userName);  
  
        System.out.println("a client userName=" + userName);  
  
        super.beforeHandshake(serverHttpRequest, serverHttpResponse, wsHandler,  
                attributes);  
  
        return true;  
    }  
  
    @Override  
    public void afterHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
        super.afterHandshake(request, response, wsHandler, ex);  
    }  
  
}  