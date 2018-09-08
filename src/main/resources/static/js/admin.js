function addUser(form) {
    fetch("/admin/addUser", {
        method: "POST",
        body: new FormData(form)
    })
    .then(result => {
        result.text().then(text => {
            if(result.status !== 200) {
                alert(result.status + " : " + text);
            } else {
                location.reload();
            }
        });
    })
    .catch(error => console.error('Error:', error));;
}

function deleteUser(id) {
    fetch("/admin/deleteUser", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({id:id})
    })
    .then(result => {
        result.text().then(text => {
            if(result.status !== 200) {
                alert(result.status + " : " + result.statusText + "\n" + text);
            } else {
                location.reload();
            }
        });
    })
    .catch(error => console.error('Error:', error));;
}