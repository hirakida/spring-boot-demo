window.onload = () => {
  const request = new XMLHttpRequest();
  request.open('GET', '/datetime');
  request.responseType = 'json';
  request.send();

  request.onreadystatechange = () => {
    if (request.readyState === XMLHttpRequest.DONE) {
      const response = request.response;
      console.log(response);

      const div = document.getElementById('datetime');
      div.textContent = response.datetime;
    }
  };
};
