@import gg.jte.Content

@param Content title
@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    ${title}
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <p>
        <a href="/">Home</a>
        <a href="/users">Users</a>
        <a href="/courses">Courses</a>
    </p>
    <div>
        ${content}
    </div>
    @if(footer != null)
        <div class="border-top border-2 footer">
            ${footer}
        </div>
    @endif
</body>
</html>
