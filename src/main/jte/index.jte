@import org.example.hexlet.util.NamedRoutes

@template.layout.page(
    title = @`
        <title>PlayWithJTE</title>
    `,
    content = @`
        <h1>Welcome to PlayWithJTE site</h1>
    <p><i>Use menu at the top to navigate</i></p>
    `,
    footer = @`
        <p>Homepage footer</p>
        <p>Visit my <a href="${NamedRoutes.footerGithubPath()}">GitHub</a></p>
    `
)

