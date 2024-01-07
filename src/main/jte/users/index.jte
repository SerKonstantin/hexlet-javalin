@import org.example.hexlet.dto.users.UsersPage
@import org.example.hexlet.util.NamedRoutes
@param UsersPage page

@template.layout.page(
    title = @`
        <title>PlayWithJTE | Users</title>
    `,
    content = @`
            <a href="${NamedRoutes.buildUsersPath()}" class="btn btn-outline-primary mb-3">Create user</a>
        @if (page.getUsers().isEmpty())
            <p>Users not found</p>
        @else
            <h3 class="mb-2">Users</h3>
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
                            <a href="${NamedRoutes.userPath(user.getId())}">${user.getFirstName()} ${user.getSecondName()}</a>
                        </td>
                        <td>${user.getEmail()}</td>
                    </tr>
                @endfor
            </table>
        @endif
    `,
    footer = @`
        <p>Users footer</p>
        <p>Visit my <a href="${NamedRoutes.footerGithubPath()}">GitHub</a></p>
    `
)
