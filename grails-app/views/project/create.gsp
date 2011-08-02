<html>
<head>
	<meta name="layout" content="main"/>
	<title>Create Project</title>
</head>
<body>

	<h2>Create Project</h2>

	<div id="column1">

		<ul>
			<li><g:link controller="project" action="list">List</g:link> Projects</li>
			<li><g:link controller="project" action="create">Create</g:link> Project</li>
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

		<g:form action="save">

		<table>

		<tbody>
		<tr>
			<th>Name</th>
			<td><g:textField name="name" value="${project.name}" class="${hasErrors(bean:project, field:'name', 'error')}" /></td>
		</tr>

		<tr>
			<th>&nbsp;</th>
			<td><input type="submit" value="Save"/></td>
		</tr>
		</tbody>

		</table>

		</g:form>

	</div>

</body>
</html>
