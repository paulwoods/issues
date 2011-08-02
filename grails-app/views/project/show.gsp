<html>
<head>
	<meta name="layout" content="main"/>
	<title>Project $project.name</title>
</head>
<body>

	<h2>Project &ldquo;${project.name}&rdquo;</h2>

	<div id="column1">

		<ul>
			<li><g:link controller="project" action="list">List</g:link> my projects.</li>
			<li><g:link controller="project" action="create">Create</g:link> a new project.</li>
			<li><g:link controller="project" action="edit" id="${project.id}">Edit</g:link> this project.</li>
		</ul>

	</div>

	<div id="column2">

		<h3>Name</h3>

		${project.name}

		<h3>Users</h3>

		<ul>
		<g:each in="${project.userProjects}" var="userProject">
			<li>${userProject.user.username} [${userProject.access}]</li>
		</g:each>
		</ul>

	</div>

</body>
</html>
