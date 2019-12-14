'use strict';
const roomId = room.dataset.roomId;
let stompClient = null;

connect.onclick = () => {
  if (stompClient === null) {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, frame => {
      console.log('connected: ' + frame);
      connect.disabled = true;
      disconnect.disabled = false;

      stompClient.subscribe('/topic/room/' + roomId, message => {
        let p = document.createElement('p');
        p.textContent = message.body;
        response.prepend(p);
        errorMessage.innerText = '';
      });

      stompClient.subscribe('/user/queue/errors', error => {
        errorMessage.innerText = JSON.parse(error.body).message;
      });
    });
  }
};

form.onsubmit = event => {
  event.preventDefault();
  if (stompClient !== null && form.message.value !== '') {
    const body = {'userName': userName.value, 'message': form.message.value};
    form.message.value = '';
    stompClient.send('/app/message/room/' + roomId, {}, JSON.stringify(body));
  }
};

disconnect.onclick = () => {
  if (stompClient !== null) {
    stompClient.disconnect();
    stompClient = null;
  }
  connect.disabled = false;
  disconnect.disabled = true;
  console.log('disconnected:');
};
