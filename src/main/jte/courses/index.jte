@import org.example.hexlet.dto.CoursesPage
@param CoursesPage page

@template.layout.page(
    title = @`
        <title>Courses | PlayWithJTE</title>
    `,
    content = @`
    <form action="/courses" method="get">
        <p>Search course:</p>
        <input type="search" required name="term">
        <input type="submit" value="Find" class="btn btn-light">
        <a href="/courses" class="btn btn-light">Show all</a>
    </form>

    <hr>
    @if (page.getCourses().isEmpty())
        <p>Courses not found</p>
    @else
        <h3>Courses</h3>
        <ul class="list">
            @for(var course : page.getCourses())
            <li>
                <a href="/courses/${course.getId()}"}>${course.getName()}</a>
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

