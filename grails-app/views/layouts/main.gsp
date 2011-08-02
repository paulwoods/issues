<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Issues - <g:layoutTitle/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
	<link href="http://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet" type="text/css">
	<link href='http://fonts.googleapis.com/css?family=Cabin+Sketch:700' rel='stylesheet' type='text/css'>
	<r:require modules="core"/>
	<r:layoutResources/>
	<g:layoutHead/>
</head>

<body>

	<div class="topbar">

		<div class="authentication menu">

			<ul>

				<sec:ifLoggedIn>
				<li>Welcome <g:link controller="user" action="profile" id="${grailsApplication.mainContext.springSecurityService.currentUser?.id}"><sec:loggedInUserInfo field="username"/></g:link></li>
				<li><g:link controller="logout" action="index">Logout</g:link></li>
				</sec:ifLoggedIn>

				<sec:ifNotLoggedIn>
				<li>Please <g:link controller="login" action="index">Login</g:link></li>
				</sec:ifNotLoggedIn>

			</ul>

		</div>

		<div class="search menu">
			<g:form controller="search" action="lookup">
			<ul>
				<li><input type="search" placeholder="search for..."/></li>
				<li><input type="submit" value="Search"/></li>
			</ul>
			</g:form>
		</div>

		<br class="clear"/>

	</div>


	<div>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
	</div>

	<div class="title"><h1>Issues</h1></div>

	<div class="subtitle">We keep track of yours so you don't have to.</div>

	<div class="main menu">
		<ul>
			<li><g:link controller="home" action="index">Home</g:link></li>
			<li><g:link controller="dashboard" action="index">Dashboard</g:link></li>
			<li><g:link controller="project" action="index">Projects</g:link></li>
			<li><g:link controller="issue" action="index">Issues</g:link></li>
			<li><g:link controller="user" action="index">Users</g:link></li>
			<li><g:link controller="help" action="index">Help</g:link></li>
			<li><g:link controller="about" action="index">About</g:link></li>
		</ul>
	</div>

	<g:layoutBody/>

	<br class="clear"/>

	<div class="footer" role="contentinfo"></div>

	<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

	<r:layoutResources/>

</body>
</html>