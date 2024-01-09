@import org.example.hexlet.dto.courses.EditCoursePage
@import org.example.hexlet.util.NamedRoutes
@param EditCoursePage page

@template.layout.page(
    title = @`
    <title>PlayWithJTE | Edit course</title>
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

    <form class="text-center" action="${NamedRoutes.coursePath(page.getId())}" method="post">
        <div>
            <label class="form-group m-2 w-25 text-start">
                Course name
                <input type="text" class="form-control rounded" required name="name" value="${page.getName()}">
            </label>
        </div>
        <div>
            <label class="form-group m-2 w-25 text-start">
                Description
                <textarea class="form-control rounded" required name="description" rows="5">${page.getDescription()}</textarea>
            </label>
        </div>
        <input type="submit" class="btn btn-outline-primary m-2" value="Save changes">
    </form>
    `
)
