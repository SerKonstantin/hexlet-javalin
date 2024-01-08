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

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link"href="${NamedRoutes.coursesPath()}?page=${page.getPage() < 2 ? 1 : page.getPage() - 1}">Previous</a>
            </li>

            @if(page.getPagesCount() <= 5)
                @for(var i = 1; i <= page.getPagesCount(); i++)
                    <li class="page-item @if(page.getPage() == i) active @endif">
                        <a class="page-link" href="${NamedRoutes.coursesPath()}?page=${i}">${i}</a>
                    </li>
                @endfor
            @else
                @if(page.getPage() > 2)
                    <li class="page-item">
                        <a class="page-link" href="${NamedRoutes.coursesPath()}?page=1">1</a>
                    </li>
                    @if(page.getPage() > 3)
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                    @endif
                @endif

                @for(var i = page.getPage() - 2; i <= page.getPage() + 2; i++)
                    @if(i > 0 && i <= page.getPagesCount())
                        <li class="page-item @if(page.getPage() == i) active @endif">
                            <a class="page-link" href="${NamedRoutes.coursesPath()}?page=${i}">${i}</a>
                        </li>
                    @endif
                @endfor

                @if(page.getPage() < page.getPagesCount() - 2)
                    @if(page.getPage() < page.getPagesCount() - 3)
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                    @endif
                    <li class="page-item">
                        <a class="page-link" href="${NamedRoutes.coursesPath()}?page=${page.getPagesCount()}">${page.getPagesCount()}</a>
                    </li>
                @endif
            @endif

            <li class="page-item">
                <a class="page-link" href="${NamedRoutes.coursesPath()}?page=${page.getPage() >= page.getPagesCount() ? page.getPagesCount() : page.getPage() + 1}">Next</a>
            </li>
        </ul>
    </nav>
    `,
    footer = @`
        <p>Courses footer</p>
        <p>Visit my <a href="${NamedRoutes.footerGithubPath()}">GitHub</a></p>
    `
)

