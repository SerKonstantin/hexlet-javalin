@template.layout.page(
    title = @`
    <title>PlayWithJTE | New user</title>
    `,
    content = @`
    <div class="d-flex justify-content-center">
        <form action="/users" method="post">
            <div class="form-group m-2">
                <label>
                    First name
                    <input type="text" class="form-control rounded" required name="firstName">
                </label>
            </div>
            <div class="form-group m-2">
                <label>
                    Second name
                    <input type="text" class="form-control rounded" required name="secondName">
                </label>
            </div>
            <div class="form-group m-2">
                <label>
                    Email
                    <input type="email" class="form-control rounded" required name="email">
                </label>
            </div>
            <input type="submit" class="btn btn-outline-primary m-2" value="Register">
        </form>
    </div>
    `
)