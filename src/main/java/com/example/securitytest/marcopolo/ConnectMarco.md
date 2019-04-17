##### 连接到"marco"WebSocket的JavaScript客户端
```
var url = 'wa://' + window.location.host + '/webscoket/marco';
var sock = new WebSocket(url); // 打开WebSocket
sock.onopen = function() { // 处理连接开启事件
    console.log('Opening');
    sayMarco();
};
sock.onmessage = function(e) {// 处理信息
    console.log('Received message ', e.data);
    setTimeout(function(){sayMarco()},2000);
};
sock.onclose = function() {// 处理连接关闭事件
    console.log('Closing');
};
function sayMarco() {
    console.log('Sending Marco!');
    sock.send('Marco!');// 发送消息
}
```