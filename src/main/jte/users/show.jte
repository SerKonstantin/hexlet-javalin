@import org.example.hexlet.dto.users.UserPage
@import org.example.hexlet.util.NamedRoutes
@param UserPage page

@template.layout.page(
    title = @`
        <title>PlayWithJTE | ${page.getUser().getFirstName()} ${page.getUser().getSecondName()}</title>
    `,
    content =@`
        <h2>${page.getUser().getFirstName()} ${page.getUser().getSecondName()}</h2>
        <p>Email: ${page.getUser().getEmail()}</p>
    `,
        footer = @`
        <p>Concrete user page footer</p>
        <p>Visit my <a href="${NamedRoutes.footerGithubPath()}">GitHub</a></p>
    `
)
