'use strict';
let ws = new WebSocket('ws://localhost:80/ws');

ws.addEventListener('message', (message) => {
  const p = document.createElement('p');
  p.textContent = message.data;
  response.insertBefore(p, response.firstElementChild);
});

ws.addEventListener('error', (event) => {
  console.log('onerror', event);
});

form.addEventListener('submit', (event) => {
  event.preventDefault();
  if (form.message.value !== '') {
    ws.send(form.message.value);
    form.message.value = '';
  }
});
