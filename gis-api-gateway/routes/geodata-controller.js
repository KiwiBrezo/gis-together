const sockjs = require('sockjs');
const SockJS  = require('sockjs-client');
const stompjs = require('stompjs');

const sockjsServer = sockjs.createServer();

sockjsServer.on('connection', (conn) => {
    console.log('SockJS connection established');

    const sock = new SockJS('http://localhost:8082/geojson-connector');
    const stompClient = stompjs.over(sock);

    stompClient.connect({}, () => {
        stompClient.subscribe('/topic/feature-collections', (message) => {
            console.log(`Received message: ${message.body}`);
            conn.emit("/topic/feature-collections", message.body);
        });

        stompClient.subscribe('/topic/push/feature-collections/new', (message) => {
            console.log(`Received message: ${message.body}`);
            conn.emit("/topic/push/feature-collections/new", message.body);
        });

        stompClient.subscribe('/topic/push/feature-collections/all', (message) => {
            console.log(`Received message: ${message.body}`);
            conn.emit("/topic/push/feature-collections/all", message.body);
        });
    });

    conn.on("/app/get-all", (msg) => {
        console.log(msg);
        stompClient.send('/app/get-all', {}, msg);
    });

    conn.on('close', () => {
        console.log('SockJS connection closed');
        stompClient.disconnect();
    });
});

module.exports = sockjsServer;