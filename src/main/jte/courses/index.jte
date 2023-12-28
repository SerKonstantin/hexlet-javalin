@import org.example.hexlet.dto.CoursesPage
@param CoursesPage page

@template.layout.page(
    content = @`
        @for(var course : page.getCourses())
            <div>${course.getName()}</div>
        @endfor
    `
)

