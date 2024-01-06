@import org.example.hexlet.dto.users.BuildUserPage
@param BuildUserPage page

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
        <form action="/users" method="post">
            <div>
                <label class="form-group m-2">
                    First name
                    <input type="text" class="form-control rounded" required name="firstName" value="${page.getFirstName()}">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    Second name
                    <input type="text" class="form-control rounded" required name="secondName" value="${page.getSecondName()}">
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