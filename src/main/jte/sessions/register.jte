@import org.example.hexlet.dto.sessions.RegisterPage
@import org.example.hexlet.util.NamedRoutes
@param RegisterPage page

@template.layout.page(
title = @`
    <title>PlayWithJTE | New user</title>
`,
content = @`
    @if(page.getErrors() != null)
        <ul class="list-unstyled">
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li class="text-danger text-center">${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <div class="d-flex justify-content-center">
        <form action="${NamedRoutes.registerPath()}" method="post">
            <div>
                <label class="form-group m-2">
                    Nickname
                    <input type="text" class="form-control rounded" required name="nickname" value="${page.getNickname()}">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    First name
                    <input type="text" class="form-control rounded" required name="firstName" value="${page.getFirstName()}">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    Last name
                    <input type="text" class="form-control rounded" required name="lastName" value="${page.getSecondName()}">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    Email
                    <input type="email" class="form-control rounded" required name="email" value="${page.getEmail()}">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    Password
                    <input type="password" class="form-control rounded" required name="password">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    Password confirmation
                    <input type="password" class="form-control rounded" required name="passwordConfirmation">
                </label>
            </div>
            <input type="submit" class="btn btn-outline-primary m-2" value="Register">
        </form>
    </div>
`
)

<!-- No footer here just for test -->