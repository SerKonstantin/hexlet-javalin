@import org.example.hexlet.dto.CoursesPage
@param CoursesPage page

@template.layout.page(
    title = @`
        <title>Courses | PlayWithJTE</title>
    `,
    content = @`
        @for(var course : page.getCourses())
            <div>${course.getName()}</div>
        @endfor
    `,
    footer = @`
        <p>Courses footer</p>
        <p>Visit my <a href="https://github.com/SerKonstantin">GitHub</a></p>
    `
)

