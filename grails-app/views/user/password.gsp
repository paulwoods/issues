<html>
<head>
	<meta name="layout" content="main"/>
	<title>Change Password</title>
</head>
<body>

	<h2>Change Password</h2>

	<div id="column1">
		<ul>
			<li><g:link controller="user" action="list">List</g:link> all users</li>
			<li><g:link controller="user" action="create">Create</g:link> a user</li>
			<li><g:link controller="user" action="edit" id="${cmd.user.id}">Edit</g:link> this user</li>
			<li><g:link controller="user" action="reset" id="${cmd.user.id}">Reset</g:link> the password</li>
			<li><g:link controller="user" action="password" id="${cmd.user.id}">Change</g:link> the password</li>
		</ul>
	</div>

	<div id="column2">

		<g:hasErrors bean="${cmd}">
		<div class="error">
			<ul>
				<g:eachError bean="${cmd}">
				<li><g:message error="${it}"/></li>
				</g:eachError>
			</ul>
		</div>
		</g:hasErrors>

		<g:form action="change">

		<table class="dialog">

		<tbody>
		<tr>
			<td class="field">User Name</td>
			<td class="value">${cmd.user.username}<g:hiddenField name="user.id" value="${cmd.user.id}"/></td>
		</tr>

		<tr>
			<td class="field">Current Password</td>
			<td class="value"><g:passwordField name="password" value="" class="${hasErrors(bean:cmd, field:'password', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">New Password 1</td>
			<td class="value"><g:passwordField name="password1" value="" class="${hasErrors(bean:cmd, field:'password1', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">New Password 2</td>
			<td class="value"><g:passwordField name="password2" value="" class="${hasErrors(bean:cmd, field:'password2', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">&nbsp;</td>
			<td class="value"><input type="submit" value="Change"/></td>
		</tr>
		</tbody>

		</table>

		</g:form>

	</div>

</body>
</html>
