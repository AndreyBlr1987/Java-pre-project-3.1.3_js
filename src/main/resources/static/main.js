let userInfo = $('#tableAllUsers')
let getAllUser = []

getUsers()

function getUsers() {
    fetch("/api/users").then((response) => {
        response.json().then((users) => {
            users.forEach((user) => {
                addUserForTable(user)
                getAllUser.push(user)
            });
        });
    });
}

function addUserForTable(user) {
    userInfo.append(
        '<tr>' +
        '<td>' + user.id + '</td>' +
        '<td>' + user.name + '</td>' +
        '<td>' + user.lastname + '</td>' +
        '<td>' + user.age + '</td>' +
        '<td>' + user.email + '</td>' +
        '<td>' + user.roles.map(roleUser => roleUser.role) + '</td>' +
        '<td>' +
        '<button onclick="editUserById(' + user.id + ')" class="btn btn-info edit-btn" data-toggle="modal" data-target="#edit"' +
        '>Edit</button></td>' +
        '<td>' +
        '<button onclick="deleteUserById(' + user.id + ')" class="btn btn-danger" data-toggle="modal" data-target="#delete"' +
        '>Delete</button></td>' +
        '</tr>'
    )
}


const addUserForm = document.querySelector('.add-user-form')
addUserForm.addEventListener('submit', (e) => {
        e.preventDefault();

        let name = document.getElementById('newName').value;
        let lastname = document.getElementById('newLastname').value;
        let age = document.getElementById('newAge').value;
        let email = document.getElementById('newEmail').value;
        let password = document.getElementById('newPassword').value;
        let roles = document.getElementById('newRole').value;

        const newUser = {
            id: parseInt($('#newId').val()),
            name: name,
            lastname: lastname,
            age: age,
            email: email,
            password: password,
            roles: addRoles(roles)
        }

        fetch("/api/users/", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newUser)
        })
            .then(() => {
                getUsers()
                document.getElementById("newUserForm").reset();
                document.location.replace(`/admin`)
            })
    }
)

function editUserById(id) {
    fetch("/api/users/" + id, {method: 'GET', dataType: 'json',})
        .then(res => {
            res.json().then(user => {
                $('#editId').val(user.id)
                $('#editName').val(user.name)
                $('#editLastname').val(user.lastname)
                $('#editAge').val(user.age)
                $('#editEmail').val(user.email)
                user.roles.map(role => {
                    $('#editRole').append('<option id="' + role.id + '" name="' + role.role + '">' +
                        role.role + '</option>')
                })
            })
        })
}


const editUserForm = document.querySelector('.edit-user-form')
editUserForm.addEventListener('submit', (e) => {
        e.preventDefault();
        let id = document.getElementById('editId').value;
        let name = document.getElementById('editName').value;
        let lastname = document.getElementById('editLastname').value;
        let age = document.getElementById('editAge').value;
        let email = document.getElementById('editEmail').value;
        let password = document.getElementById('editPassword').value;
        let roles = document.getElementById('editRole').value;

        const editUser = {
            id: parseInt($('#editId').val()),
            name: name,
            lastname: lastname,
            age: age,
            email: email,
            password: password,
            roles: addRoles(roles)
        }

        console.log(editUser)
        fetch("/api/users/" + ($('#editId').val()), {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editUser)
        })
            .then(() => {
                userInfo.empty();
                getUsers();
                closeForm();
            })
    }
)

function deleteUserById(id) {
    fetch("/api/users/" + id, {method: 'GET', dataType: 'json',})
        .then(res => {
            res.json().then(user => {
                $('#deleteId').val(user.id)
                $('#deleteName').val(user.name)
                $('#deleteLastname').val(user.lastname)
                $('#deleteAge').val(user.age)
                $('#deleteEmail').val(user.email)
                user.roles.map(role => {
                    $('#deleteRole').append('<option id="' + role.id + '" name="' + role.role + '">' +
                        role.role + '</option>')
                })
            })
        })
}

function deleteUser() {
    fetch("/api/users/" + ($('#deleteId').val()), {method: "DELETE"})
        .then(() => {
            userInfo.empty();
            getUsers();
            closeForm();
        })
}

function closeForm() {
    $("#edit .close").click();
    document.getElementById("editUserForm").reset();
    $("#delete .close").click();
    document.getElementById("deleteUserForm").reset();
    $('#deleteRole > option').remove();
}

function addRoles(role) {
    let roles = [];
    if (role === "USER" && role === "ADMIN") {
        roles.push({id: 1, role: 'USER', authority: 'USER'},
            {id: 2, role: 'ADMIN', authority: 'ADMIN'})
        return roles
    } else if (role === "USER") {
        roles.push({id: 1, role: 'USER', authority: 'USER'});
        return roles
    } else if (role === "ADMIN") {
        roles.push({id: 2, role: 'ADMIN', authority: 'ADMIN'});
        return roles
    }
}


