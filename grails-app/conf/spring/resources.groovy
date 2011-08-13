// Place your Spring DSL code here

beans = {

	pagination(org.mrpaulwoods.issues.Pagination) { bean ->
		bean.scope = "prototype"
	}

	controllerHelper(org.mrpaulwoods.issues.ControllerHelper) { bean ->
		bean.scope = "prototype"
	}

}

