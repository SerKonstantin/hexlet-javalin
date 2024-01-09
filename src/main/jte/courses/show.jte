@import org.example.hexlet.dto.courses.CoursePage
@import org.example.hexlet.util.NamedRoutes
@param CoursePage page

@template.layout.page(
    title = @`
        <title>PlayWithJTE | ${page.getCourse().getName()}</title>
    `,

    content = @`
        <a class="btn btn-outline-primary" href="${NamedRoutes.editCoursePath(page.getCourse().getId())}">Edit course</a>
        <h2 class="mt-3">${page.getCourse().getName()}</h2>
        <p>${page.getCourse().getDescription()}</p>
    `,

    footer = @`
        <p>Course page footer</p>
        <p>Visit my <a href="${NamedRoutes.footerGithubPath()}">GitHub</a></p>
    `
)
