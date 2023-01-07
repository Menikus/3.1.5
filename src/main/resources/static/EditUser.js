const form_edit = document.getElementById('formEdit');
const id_edit = document.getElementById('editId');
const firstName_edit = document.getElementById('editFirstName');
const lastName_edit = document.getElementById('editLastName');
const age_edit = document.getElementById('editAge');
const email_edit = document.getElementById('editEmail');
const pass_edit = document.getElementById('editPassword');


async function editModalData(id) {
    $('#modalEdit').modal('show');
    const urlEdit = '/api/users/' + id;
    let usersPageEdit = await fetch(urlEdit);
    if (usersPageEdit.ok) {
        let userData =
            await usersPageEdit.json().then(user => {
                id_edit.value = `${user.id}`;
                firstName_edit.value = `${user.firstName}`;
                lastName_edit.value = `${user.lastName}`;
                age_edit.value = `${user.age}`;
                email_edit.value = `${user.email}`;
                pass_edit.value = `${user.password}`;
            })
    } else {
        alert(`Error, ${usersPageEdit.status}`)
    }
}

async function editUser() {
    let urlEdit = '/api/users/' + id_edit.value;
    let listOfRole = [];
    for (let i = 0; i < form_edit.roles.options.length; i++) {
        if (form_edit.roles.options[i].selected)
            listOfRole.push({
                id: form_edit.roles.options[i].value,
                roles: "ROLE_" + form_edit.roles.options[i].text
            })
    }

    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            firstName: form_edit.firstName.value,
            lastName: form_edit.lastName.value,
            age: form_edit.age.value,
            email: form_edit.email.value,
            password: form_edit.password.value,
            roles: listOfRole
        })
    }

    await fetch(urlEdit, method).then(() => {
        $('#editCloseBtn').click();
        findAll();
    })
}