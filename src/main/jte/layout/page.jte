@import gg.jte.Content
@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.BasePage

@param Content title
@param Content content
@param Content footer = null
@param BasePage page = null

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    ${title}
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body class="m-4">
    <header>
        <ul class="nav nav-pills border-bottom mb-4">
            <li class="nav-item">
                <a class="nav-link" href="${NamedRoutes.rootPath()}" >Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${NamedRoutes.usersPath()}">Users</a>
            </li>
            <li>
                <a class="nav-link" href="${NamedRoutes.coursesPath()}">Courses</a>
            </li>
        </ul>
    </header>

    <main>
        @if(page != null && page.getFlash() != null)
            <div class="alert alert-${page.getFlashType()}" role="alert">
                ${page.getFlash()}
            </div>
        @endif

        <div>
            ${content}
        </div>
    </main>
    @if(footer != null)
        <div class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
            ${footer}
        </div>
    @endif
</body>
</html>
