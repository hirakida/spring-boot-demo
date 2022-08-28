'use strict';
// const URL = 'ws://localhost:80/ws';
const URL = 'ws://localhost:8080/ws';
let ws = null;

openBtn.onclick = () => {
  if (ws === null) {
    ws = new WebSocket(URL);
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
    if (form.message.value !== '') {
      ws.send(form.message.value);
      form.message.value = '';
    }
  }
};

function onOpen() {
  openBtn.disabled = true;
  closeBtn.disabled = false;
  console.log('onopen');
}

function onMessage(message) {
  const p = document.createElement('p');
  p.textContent = message.data;
  response.insertBefore(p, response.firstElementChild);
  console.log('onmessage message=' + message.data);
}

function onClose() {
  openBtn.disabled = false;
  closeBtn.disabled = true;
  console.log('onclose');
}

function onError(event) {
  console.log('onerror', event);
}
