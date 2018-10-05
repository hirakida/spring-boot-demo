'use strict';
let ws = null;

const onOpen = () => {
  openBtn.disabled = true;
  closeBtn.disabled = false;
  console.log('onopen');
};

const onClose = () => {
  openBtn.disabled = false;
  closeBtn.disabled = true;
  console.log('onclose');
};

const onMessage = message => {
  const p = document.createElement('p');
  p.textContent = message.data;
  response.insertBefore(p, response.firstElementChild);
  console.log('onmessage message=' + message.data);
};

const onError = event => {
  console.log('onerror', event)
};

openBtn.onclick = () => {
  if (ws === null) {
    // ws = new WebSocket('ws://localhost:8080/ws');
    ws = new WebSocket('ws://localhost:80/ws');
    ws.onopen = onOpen;
    ws.onmessage = onMessage;
    ws.onclose = onClose;
    ws.onerror = onError;
  }
};

closeBtn.onclick = () => {
  if (ws !== null) {
    ws.close();
    ws = null;
  }
};

form.onsubmit = event => {
  event.preventDefault();
  if (ws !== null) {
    if (message.value !== '') {
      ws.send(message.value);
      message.value = '';
    }
  }
};
