@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.sessions.LoginPage
@param LoginPage page

@template.layout.page(
    title = @`
        <title>PlayWithJTE | New session</title>
    `,

    content = @`
    @if(page.getErrorMessage() != null)
        <p class="text-danger text-center">${page.getErrorMessage()}</p>
    @endif

    <div class="d-flex justify-content-center">
        <form action="${NamedRoutes.loginPath()}" method="post">
            <div>
                <label class="form-group m-2">
                    Nickname
                    <input type="text" class="form-control rounded" required placeholder="Nickname" name="nickname" value="${page.getNickname()}">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    Password
                    <input type="password" class="form-control rounded" required placeholder="Password" name="password">
                </label>
            </div>
            <input type="submit" class="btn btn-outline-primary m-2" value="Log in">
        </form>
    </div>
    `
)