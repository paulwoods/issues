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
			<li><g:link controller="user" action="edit" id="${user.id}">Edit</g:link> this user</li>
			<li><g:link controller="password" action="reset" id="${user.id}">Reset</g:link> the password</li>
			<li><g:link controller="password" action="password" id="${user.id}">Change</g:link> the password</li>
			<li><g:link controller="user" action="join" id="${user.id}">Join</g:link> projects</li>
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

		<h3>User Information</h3>

		<table class="dialog">

		<tbody>
		<tr>
			<td class="field">User Name</td>
			<td class="value">${user.username}</td>
		</tr>

		<tr>
			<td class="field">Enabled</td>
			<td class="value"><g:formatBoolean boolean="${user.enabled}" true="Yes" false="No" /></td>
		</tr>

		<tr>
			<td class="field">Account Expired</td>
			<td class="value"><g:formatBoolean boolean="${user.accountExpired}" true="Yes" false="No" /></td>
		</tr>

		<tr>
			<td class="field">Account Locked</td>
			<td class="value"><g:formatBoolean boolean="${user.accountLocked}" true="Yes" false="No" /></td>
		</tr>

		<tr>
			<td class="field">Password Expired</td>
			<td class="value"><g:formatBoolean boolean="${user.passwordExpired}" true="Yes" false="No" /></td>
		</tr>

		<tr>
			<td class="field">Role</td>
			<td class="value">${user.userRoles.role.authority.sort().join(',')}</td>
		</tr>

		</tbody>

		</table>

		<h3>Projects</h3>

		<table>
		<thead>
			<tr>
				<th>Project</th>
				<th>Access</th>
			</tr>
		</thead>
		<tbody class="hover">
			<g:each in="${user.userProjects}" var="up" status="n">
			<tr>
				<td>${up.project.name}</td>
				<td>${up.access}</td>
			</td>
			</g:each>
		</tbody>
		</table>

	</div>

</body>
</html>
