<html>
<head>
	<meta name="layout" content="main"/>
	<title>Edit ${user.username}</title>
</head>
<body>

	<h2>&ldquo;Edit ${user.username}&rdquo;</h2>

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

		<g:form id="${user.id}">

		<table class="dialog">

		<tbody>
		<tr>
			<td class="field">User Name</td>
			<td class="value"><g:textField name="username" value="${user.username}" class="${hasErrors(bean:user, field:'username', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">Enabled</td>
			<td class="value"><g:checkBox name="enabled" value="${true}" checked="${user.enabled}" class="${hasErrors(bean:user, field:'enabled', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">Account Expired</td>
			<td class="value"><g:checkBox name="accountExpired" value="${true}" checked="${user.accountExpired}" class="${hasErrors(bean:user, field:'accountExpired', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">Account Locked</td>
			<td class="value"><g:checkBox name="accountLocked" value="${true}" checked="${user.accountLocked}" class="${hasErrors(bean:user, field:'accountLocked', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">Password Expired</td>
			<td class="value"><g:checkBox name="passwordExpired" value="${true}" checked="${user.passwordExpired}" class="${hasErrors(bean:user, field:'passwordExpired', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">&nbsp;</td>
			<td class="value"><g:actionSubmit action="update" value="Update"/></td>
		</tr>

		<tr>
			<td class="field">&nbsp;</td>
			<td class="value"><g:actionSubmit action="delete" value="Delete" onclick="return confirm('Are you sure?');"/></td>
		</tr>

		</tbody>

		</table>

		</g:form>

	</div>

</body>
</html>
