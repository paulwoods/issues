<html>
<head>
	<meta name="layout" content="main"/>
	<title>${user.username}</title>
</head>
<body>

	<h2>&ldquo;${user.username}&rdquo;</h2>

	<div id="column1">

		<ul>
			<li><g:link controller="user" action="list">List</g:link> all users</li>
			<li><g:link controller="user" action="create">Create</g:link> a user</li>
			<li><g:link controller="user" action="show" id="${user.id}">Show</g:link> this user</li>
		</ul>

	</div>

	<div id="column2">

		<g:hasErrors bean="${user}">
		<div class="error">
			<ul>
				<g:eachError bean="${user}">
				<li><g:message error="${it}"/></li>
				</g:eachError>
			</ul>
		</div>
		</g:hasErrors>

		<h3>Select Projects</h3>

		<g:form action="attach" id="${user.id}">

		<table class="dialog">

		<tbody>
		<thead>
		<tr>
			<th colspan="2">Name</th>
		</tr>
		</tbody>
		<tbody>
		<g:each in="${org.mrpaulwoods.issues.Project.list()}" var="project" status="n">
		<tr>
			<td><g:checkBox name="selected" value="${project.id}" checked="${org.mrpaulwoods.issues.UserProject.findByUserAndProject(user, project)}"/></td>
			<td>${project.name}</td>
		</tr>
		</g:each>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Join Selected Projects"/></td>
		</tr>
		</tbody>
		</table>

		</g:form>

	</div>

</body>
</html>
