window.onload = () => {
    fetch('http://localhost:18080/hello', {
        headers: {
            'X-Requested-With': 'fetch'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            const div = document.getElementById('content');
            div.textContent = data.content;
        }).catch(e => console.log(e.message))
};
