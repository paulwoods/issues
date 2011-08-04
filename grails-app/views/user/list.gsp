<html>
<head>
	<meta name="layout" content="main"/>
	<title>Users</title>
</head>
<body>

	<h2>Users</h2>

	<div id="column1">

		<ul>
			<li><g:link controller="user" action="list">List</g:link> the users</li>
			<li><g:link controller="user" action="create">Create</g:link> a new user</li>
		</ul>

	</div>

	<div id="column2">

		<table>

		<caption>There are ${users.totalCount} users.</caption>

		<thead>
		<tr>
			<g:sortableColumn property="username" title="User Name" params="${params}" />
			<g:sortableColumn property="enabled" title="Enabled" params="${params}" />
			<g:sortableColumn property="accountExpired" title="Account Expired" params="${params}" />
			<g:sortableColumn property="accountLocked" title="Account Locked" params="${params}" />
			<g:sortableColumn property="passwordExpired" title="Password Expired" params="${params}" />
		</tr>
		</thead>

		<tbody class="hover">
		<g:each in="${users}" var="user" status="n">
		<tr>
			<td><g:link controller="user" action="show" id="${user.id}">${user.username}</g:link></td>
			<td><g:formatBoolean boolean="${user.enabled}" true="Yes" false="No"/></td>
			<td><g:formatBoolean boolean="${user.accountExpired}" true="Yes" false="No"/></td>
			<td><g:formatBoolean boolean="${user.accountLocked}" true="Yes" false="No"/></td>
			<td><g:formatBoolean boolean="${user.passwordExpired}" true="Yes" false="No"/></td>
		</tr>
		</g:each>
		</tbody>

		</table>

		<div class="paginate">
		<g:paginate controller="user" action="list" total="${users.totalCount}" />
		</div>

	</div>


</body>
</html>
