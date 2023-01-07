const form_del = document.getElementById('formDelete');
const id_del = document.getElementById('deleteId');
const name_del = document.getElementById('deleteFirstName');
const lastname_del = document.getElementById('deleteLastName');
const age_del = document.getElementById('deleteAge');
const email_del = document.getElementById('deleteEmail');

async function deleteModalData(id) {
    $('#modalDelete').modal('show');
    const urlForDel = 'http://localhost:8080/api/users/' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                name_del.value = `${user.firstName}`;
                lastname_del.value = `${user.lastName}`;
                age_del.value = `${user.age}`;
                email_del.value = `${user.email}`;
            })
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}

async function deleteUser() {
    form_del.addEventListener('submit', deletingUser);

    function deletingUser(event) {
        event.preventDefault();
        let urlDel = 'http://localhost:8080/api/users/' + id_del.value;
        let method = {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json"
            }
        }
        fetch(urlDel, method).then(() => {
            $('#delCloseBtn').click();
            findAll();
        })
    }
}