@template.layout.page(
    title = @`
    <title>PlayWithJTE | New user</title>
    `,
    content = @`
    <div class="d-flex justify-content-center">
        <form action="/users" method="post">
            <div>
                <label class="form-group m-2">
                    First name
                    <input type="text" class="form-control rounded" required name="firstName">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    Second name
                    <input type="text" class="form-control rounded" required name="secondName">
                </label>
            </div>
            <div>
                <label class="form-group m-2">
                    Email
                    <input type="email" class="form-control rounded" required name="email">
                </label>
            </div>
            <input type="submit" class="btn btn-outline-primary m-2" value="Register">
        </form>
    </div>
    `
)