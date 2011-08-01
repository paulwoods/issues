<html>
<head>
	<meta name="layout" content="main"/>
	<title>Projects</title>
</head>
<body>

	<h2>Projects</h2>

	<div id="column1">

		<ul>
			<li><g:link controller="project" action="list">List</g:link> Projects</li>
			<li><g:link controller="project" action="create">Create</g:link> Project</li>
		</ul>

	</div>

	<div id="column2">

		<table>

		<caption>There are ${projects.totalCount} projects.</caption>

		<thead>
		<tr>
			<g:sortableColumn property="name" title="Name" params="${params}" />
		</tr>
		</thead>

		<tbody class="hover">
		<g:each in="${projects}" var="project" status="n">
		<tr>
			<td><g:link controller="project" action="show" id="${project.id}">${project.name}</g:link></td>
		</tr>
		</g:each>
		</tbody>

		</table>

		<div class="paginate">
		<g:paginate controller="project" action="list" total="${projects.totalCount}" />
		</div>

	</div>


</body>
</html>
