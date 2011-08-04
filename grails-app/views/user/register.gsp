<html>
<head>
	<meta name="layout" content="main"/>
	<title>Register User</title>
</head>
<body>

	<h2>Register User</h2>

	<div id="column1">
		<ul>
			<li>Register</li>
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

		<g:form action="install">

		<table class="dialog">

		<tbody>
		<tr>
			<td class="field">User Name</td>
			<td class="value"><g:textField name="username" value="${cmd.username}" class="${hasErrors(bean:cmd, field:'username', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">Password 1</td>
			<td class="value"><g:passwordField name="password1" value="" class="${hasErrors(bean:cmd, field:'password1', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">Password 2</td>
			<td class="value"><g:passwordField name="password2" value="" class="${hasErrors(bean:cmd, field:'password2', 'error')}" /></td>
		</tr>

		<tr>
			<td class="field">&nbsp;</td>
			<td class="value"><input type="submit" value="Regsiter"/></td>
		</tr>
		</tbody>

		</table>

		</g:form>

	</div>

</body>
</html>
