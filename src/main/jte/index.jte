@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
    title = @`
        <title>PlayWithJTE</title>
    `,

    content = @`
    <h1>Welcome to PlayWithJTE site</h1>

    <div class="m-5">
        @if(page.getCurrentUserName() != null)
            <p>Greetings, ${page.getCurrentUserName()}.</p>
            <p>Remove cookies to log out.</p>
        @else
            <p><a href="${NamedRoutes.buildSessionsPath()}">Register</a> to test javalin session.</p>
        @endif
    </div>

    <p><i>Use menu at the top to navigate.</i></p>

    @if(!page.isVisited())
        <p class="mt-5"><b>This message appear just once, to see it again remove cookies.</b></p>
    @else
        <p class="mt-5">Hidden temporary message on this line, remove cookies to see.</p>
    @endif

    `,

    footer = @`
        <p>Homepage footer</p>
        <p>Visit my <a href="${NamedRoutes.footerGithubPath()}">GitHub</a></p>
    `
)

