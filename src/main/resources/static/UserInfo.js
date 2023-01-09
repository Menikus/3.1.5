async function getUser() {
    let page = await fetch('/api/users/user');
    if (page.ok) {
        let user = await page.json();
        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}

function getInformationAboutUser(user) {
    let tr = document.createElement("tr");
    tr.innerHTML =
        `<tr>
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${user.roles.map(role => " " + role.name.substring(5))}</td>
</tr>`
    document.getElementById(`userInfo`).append(tr);
}

getUser();

