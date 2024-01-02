@import org.example.hexlet.dto.UsersPage
@param UsersPage page

@template.layout.page(
    title = @`
        <title>Users | PlayWithJTE</title>
    `,
    content = @`
        <a href="/users/build">New User</a>
        @for(var user : page.getUsers())
            <div>${user.getFirstName()}</div>
        @endfor
    `,
    footer = @`
        <p>Users footer</p>
        <p>Visit my <a href="https://github.com/SerKonstantin">GitHub</a></p>
    `
)
