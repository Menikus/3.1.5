const form_new = document.getElementById('newUsersForm');
const role_new = document.querySelector('#createUserRoles').selectedOptions;

async function newUser() {
    form_new.addEventListener('submit', addNewUser);

    async function addNewUser(event) {
        event.preventDefault();
        const urlNew = 'api/users/';
        let listOfRole = [];
        for (let i = 0; i < role_new.length; i++) {
            listOfRole.push({
                id: role_new[i].value,
                roles: "ROLE_" + role_new[i].text
            })
        }

        let method = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                firstName: form_new.firstName.value,
                lastName: form_new.lastName.value,
                age: form_new.age.value,
                email: form_new.email.value,
                password: form_new.password.value,
                roles: listOfRole
            })
        }
        await fetch(urlNew, method).then(() => {
            form_new.reset();
            findAll();
        });
    }
}