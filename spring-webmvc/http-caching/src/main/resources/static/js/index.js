window.onload = () => {
  fetch('/hello')
      .then(response => response.json())
      .then(data => {
            console.log(data);
            const div = document.getElementById('message');
            div.textContent = data.message;
      }).catch(e => console.log(e.message))
};
