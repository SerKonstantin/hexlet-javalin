@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
    title = @`
        <title>PlayWithJTE</title>
    `,

    content = @`
    <h1>Welcome to PlayWithJTE site</h1>

    <div class="mt-5 mb-5">
        @if(page.getCurrentUserName() != null)
            <p>Greetings, you are now logged as <b>${page.getCurrentUserName()}</b>.</p>

            <form action="${NamedRoutes.logoutPath()}" method="post">
                <input class="btn btn-outline-primary" type="submit" value="Log out">
            </form>
        @else
            <p>Greetings, you are now logged as <b>Guest</b>.</p>
            <p>Register to test sessions. Or log in with username 'admin' and password 'admin'.</p>
            <button type="button" class="btn btn-outline-primary" onclick="window.location.href='${NamedRoutes.registerPath()}'">
                Register
            </button>
            <button type="button" class="btn btn-outline-primary" onclick="window.location.href='${NamedRoutes.loginPath()}'">
                Log in
            </button>
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

