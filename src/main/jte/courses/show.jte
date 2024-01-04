@import org.example.hexlet.dto.CoursePage
@param CoursePage page

@template.layout.page(
    title = @`
        <title>${page.getCourse().getName()} | PlayWithJTE</title>
    `,
    content = @`
        <h2>${page.getCourse().getName()}</h2>
        <p>${page.getCourse().getDescription()}</p>
    `
)
## No footer here just for test
