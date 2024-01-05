@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(
    title = @`
        <title>Courses | PlayWithJTE</title>
    `,
    content = @`
    <div class="w-25">
        <form class="input-group" action="/courses" method="get">
            <input type="search" class="form-control rounded" placeholder="search" aria-label="Search" aria-describedby="search-addon" required name="term" value="${page.getTerm()}">
            <input type="submit" value="Find" class="btn btn-outline-primary">
            <a href="/courses" class="btn btn-outline-primary">Show all</a>
        </form>
    </div>

    <hr>
    @if (page.getCourses().isEmpty())
        <p>Courses not found</p>
    @else
        <h3>Courses</h3>
        <ul class="list">
            @for(var course : page.getCourses())
            <li>
                <a href="/courses/${course.getId()}">${course.getName()}</a>
            </li>
            @endfor
        </ul>
    @endif
    `,
    footer = @`
        <p>Courses footer</p>
        <p>Visit my <a href="https://github.com/SerKonstantin">GitHub</a></p>
    `
)

