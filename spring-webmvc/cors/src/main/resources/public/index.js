window.onload = () => {
  const request = new XMLHttpRequest();
  request.open('GET', 'http://localhost:8080/hello');
  request.responseType = 'json';
  request.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
  request.send();

  request.onreadystatechange = () => {
    if (request.readyState === XMLHttpRequest.DONE) {
      const response = request.response;
      console.log(response);

      const div = document.getElementById('content');
      div.textContent = response.content;
    }
  };
};
