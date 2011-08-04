<html>
<head>
	<meta name="layout" content="main"/>
	<title>Reset Password</title>
</head>
<body>

	<h2>Reset Password</h2>

	<div id="column1">
		<ul>
			<li>Reset Password</li>
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

		<g:form action="reset_password" id="${user.id}">

		<table class="dialog">

		<tbody>
		<tr>
			<td class="field">User Name</td>
			<td class="value">${user.username}</td>
		</tr>

		<tr>
			<td class="field">&nbsp;</td>
			<td class="value">Are you sure you want to reset the password?</td>
		</tr>

		<tr>
			<td class="field">&nbsp;</td>
			<td class="value"><input type="submit" value="Yes, reset the password"/></td>
		</tr>
		</tbody>

		</table>

		</g:form>

	</div>

</body>
</html>
