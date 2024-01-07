@import org.example.hexlet.dto.courses.BuildCoursePage
@import org.example.hexlet.util.NamedRoutes
@param BuildCoursePage page

@template.layout.page(
    title = @`
        <title>PlayWithJTE | New course</title>
    `,
    content = @`
    @if(page.getErrors() != null)
        <ul class="list-unstyled">
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li class="text-danger text-center">${error.getMessage()}</li>
                @endfor                
            @endfor
        </ul>
    @endif
    <form class="text-center" action="${NamedRoutes.coursesPath()}" method="post">
            <div>
                <label class="form-group m-2 w-25 text-start">
                    Course name
                    <input type="text" class="form-control rounded" required name="name" value="${page.getName()}">
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