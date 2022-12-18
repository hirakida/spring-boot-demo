window.onload = () => {
  const csrfToken = document.querySelector("meta[name='_csrf'][content]").content
  const csrfHeader = document.querySelector("meta[name='_csrf_header'][content]").content

  form.onsubmit = event => {
    event.preventDefault()

    const headers = {
      'Content-Type': 'application/json',
      [csrfHeader]: csrfToken
    }
    const request = {'lastName': lastName.value, 'firstName': firstName.value}

    fetch('/users',
      {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(request)
      })
      .then(response => {
        lastNameError.innerText = ''
        firstNameError.innerText = ''

        if (!response.ok) {
          return response.json()
        }

        lastName.value = ''
        firstName.value = ''
      })
      .then(response => {
        if (response !== undefined) {
          if (Array.isArray(response)) {
            response.forEach(data => {
              if (data['field'] === 'lastName') {
                lastNameError.innerText = data['message']
              } else if (data['field'] === 'firstName') {
                firstNameError.innerText = data['message']
              }
            })
          } else {
            console.error(response)
          }
        }
      })
      .catch(error => {
        console.error(error)
      })
  }
}
