$(async function () {
    await findAll();
});
const table = $('#usersTable');
function findAll() {
    table.empty()
    fetch("/api/users/")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let usersTable = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>
                            <td>${user.email}</td>
                            <td>${user.roles.map(role => " " + role.name.substring(5))}</td>
                            <td>
                              <button type="button" class="btn btn-info text-light"
                                onclick="editModalData(${user.id})" data-bs-target="#modalEdit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-bs-toggle="modal" id="buttonDelete"
                              onclick="deleteModalData(${user.id})" data-bs-target="#modalDelete">Delete</button>
                            </td>
                        </tr>)`;
                table.append(usersTable);
            })
        })
}
