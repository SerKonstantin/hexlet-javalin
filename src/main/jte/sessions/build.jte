@import org.example.hexlet.util.NamedRoutes

@template.layout.page(
    title = @`
        <title>PlayWithJTE | New session</title>
    `,

    content = @`
    <form action="${NamedRoutes.sessionsPath()}" method="post">
        <label class="form-group m-2">
            Nickname
            <input type="text" class="form-control rounded" placeholder="Nickname" name="username">
        </label>
        <label class="form-group m-2">
            Password
            <input type="password" class="form-control rounded" placeholder="Password" name="password">
        </label>
        <label class="form-group m-2">
            Confirm password
            <input type="password" class="form-control rounded" placeholder="Confirm password" name="password confirmation">
        </label>
        <input type="submit" class="btn btn-outline-primary m-2" value="Register">
    </form>
    `
)