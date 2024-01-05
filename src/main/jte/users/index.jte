@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    title = @`
        <title>Users | PlayWithJTE</title>
    `,
    content = @`
            <a href="/users/build" class="btn btn-outline-primary">Create user</a>
        @if (page.getUsers().isEmpty())
            <p>Users not found</p>
        @else
            <table class="table">
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Full name</td>
                        <td>Email</td>
                    </tr>
                </thead>
                @for(var user : page.getUsers())
                    <tr>
                        <td>${user.getId()}</td>
                        <td>
                            <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getSecondName()}</a>
                        </td>
                        <td>${user.getEmail()}</td>
                    </tr>
                @endfor
            </table>
        @endif
    `,
    footer = @`
        <p>Users footer</p>
        <p>Visit my <a href="https://github.com/SerKonstantin">GitHub</a></p>
    `
)
