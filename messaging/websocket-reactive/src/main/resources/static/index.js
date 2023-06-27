const ws = new WebSocket('ws://localhost:8080/ws/echo');
const id = Math.random().toString(36).substring(2);

ws.addEventListener('open', () => {
  setInterval(sendMessage, 1000);
});

ws.addEventListener('message', (msg) => {
  const p = document.createElement('p');
  p.textContent = msg.data;
  main.insertBefore(p, main.firstElementChild);
});

function sendMessage() {
  ws.send(id);
}
