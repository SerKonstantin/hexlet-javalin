@template.layout.page(
    title = @`
        New user | PlayWithJTE
    `,
    content = @`
    <form action="/users" method="post">
        <div>
            <label>
                First name
                <input type="text" required name="firstName">
            </label>
        </div>
        <div>
            <label>
                Second name
                <input type="text" required name="secondName">
            </label>
        </div>
        <div>
            <label>
                Email
                <input type="email" required name="email">
            </label>
        </div>
        <input type="submit" value="Register">
    </form>
    `
)