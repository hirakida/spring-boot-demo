window.addEventListener('load', () => {
  const ws = new WebSocket('ws://localhost:8080/ws/greetings');
  ws.addEventListener('open', () => {
    const id = Math.random().toString(36).substring(2);
    ws.send(id)
  });
  ws.addEventListener('message', (msg) => {
    const p = document.createElement('p');
    p.textContent = msg.data;
    main.insertBefore(p, main.firstElementChild);
    console.log(msg.data)
  })
});
