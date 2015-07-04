package com.savaskoc.wstest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by savaskoc on 04/07/2015.
 */
@RestController
public class WebSocket {
    @MessageMapping("/hello")
    @SendTo("/hello")
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getClient() {
        return "<script src=\"//cdn.jsdelivr.net/sockjs/1.0.0/sockjs.min.js\"></script>" +
                "<script src=\"//cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js\"></script>" +
                "<script type=\"text/javascript\">\n" +
                "  var socket = new SockJS('/hello');\n" +
                "  stompClient = Stomp.over(socket);\n" +
                "  stompClient.connect({}, function (frame) {\n" +
                "    stompClient.subscribe('/hello', function (msg) {\n" +
                "      console.log(msg.body);\n" +
                "      stompClient.disconnect();\n" +
                "    });\n" +
                "    stompClient.send('/hello', {}, 'World');\n" +
                "  });\n" +
                "</script>";
    }
}
