<html>
<head>
	<meta name="layout" content="main"/>
	<title>Edit ${project.name}</title>
</head>
<body>

	<h2>Edit &ldquo;${project.name}&rdquo;</h2>

	<div id="column1">

		<ul>
			<li><g:link controller="project" action="list">List</g:link> Projects</li>
			<li><g:link controller="project" action="create">Create</g:link> Project</li>
			<li><g:link controller="project" action="show" id="${project.id}">Show</g:link> this project.</li>
		</ul>

	</div>

	<div id="column2">

		<g:hasErrors bean="${project}">
		<div class="error">
			<ul>
				<g:eachError bean="${project}">
				<li><g:message error="${it}"/></li>
				</g:eachError>
			</ul>
		</div>
		</g:hasErrors>

		<g:form id="${project.id}">

		<table class="edit">

		<tbody>
		<tr>
			<th>Name</th>
			<td><g:textField name="name" value="${project.name}" class="${hasErrors(bean:project, field:'name', 'error')}" /></td>
		</tr>

		<tr>
			<th>&nbsp;</th>
			<td>
				<g:actionSubmit action="update" value="Update"/>
				<g:actionSubmit action="delete" value="Delete" onclick="return confirm('Are you sure?');"/>
			</td>
		</tr>
		</tbody>

		</table>

		</g:form>

	</div>

</body>
</html>
