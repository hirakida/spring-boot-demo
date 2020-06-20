window.addEventListener('load', () => {
  const ws = new WebSocket('ws://localhost:8080/ws/greetings');
  ws.addEventListener('open', () => {
    ws.send('hello!')
  });
  ws.addEventListener('message', (msg) => {
    const p = document.createElement('p');
    p.textContent = msg.data;
    main.insertBefore(p, main.firstElementChild);
    console.log(msg.data)
  })
});
