@import org.example.hexlet.dto.courses.CoursesPage
@import org.example.hexlet.util.NamedRoutes
@param CoursesPage page

@template.layout.page(
    title = @`
        <title>PlayWithJTE | Courses</title>
    `,
    content = @`
    <div class="w-50 mb-3">
        <form class="input-group" action="${NamedRoutes.coursesPath()}" method="get">
            <input type="search" class="form-control rounded" placeholder="search" aria-label="Search" aria-describedby="search-addon" required name="term" value="${page.getTerm()}">
            <input type="submit" value="Find" class="btn btn-outline-primary">
            <a href="${NamedRoutes.coursesPath()}" class="btn btn-outline-primary">Show all</a>
            <a href="${NamedRoutes.buildCoursesPath()}" class="btn btn-outline-primary">Add course</a>
        </form>
    </div>
    @if (page.getCourses().isEmpty())
        <p>Courses not found</p>
    @else
        <h3>Courses</h3>
        <div class="list-group w-50">
            @for(var course : page.getCourses())
            <button type="button" class="list-group-item list-group-item-action" onclick="window.location.href='/courses/${course.getId()}'">
                ${course.getName()}
            </button>
            @endfor
        </div>
    @endif
    `,
    footer = @`
        <p>Courses footer</p>
        <p>Visit my <a href="${NamedRoutes.footerGithubPath()}">GitHub</a></p>
    `
)

