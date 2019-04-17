##### 借助STOMP库，通过JavaScript发送消息
```
var url = "http://" + windos.location.host + "/stomp/marcopolo";
var sock = new SockJS(url);// 创建SockJS连接
var stomp = Stomp.pver(sock);// 创建一个STOMP客户端实例，已经封装SockJS
var payload = JSON.stringify({ 'message': 'Marco!' })
stomp.connect('guest', 'guest', function(frame) {// 连接STOMP端点
    stomp.send("/marco", {}, payload);// 发送消息
}
```