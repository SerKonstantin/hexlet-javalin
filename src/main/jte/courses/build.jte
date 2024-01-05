@template.layout.page(
    title = @`
        <title>PlayWithJTE | New course</title>
    `,
    content = @`
        <form class="text-center" action="/courses" method="post">
            <div>
                <label class="form-group m-2 w-25 text-start">
                    Course name
                    <input type="text" class="form-control rounded" required name="name">
                </label>
            </div>
            <div>
                <label class="form-group m-2 w-25 text-start">
                    Description
                    <textarea class="form-control rounded" required name="description" rows="5"></textarea>
                </label>
            </div>
            <input type="submit" class="btn btn-outline-primary m-2" value="Add course">
        </form>
    `
)