// Place your Spring DSL code here

beans = {

	pagination(org.mrpaulwoods.pagination.Pagination) { bean ->
		bean.scope = "prototype"
	}

}
