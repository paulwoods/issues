package org.mrpaulwoods.pagination

/**
 * Pagination sets the max, offset, sort and order values of a map
 * It is used to fill any missing data in the map so that the
 * domain class's findBy* or criteria queries can be executed with valid inputs.
 *
 *
 * Usage 1 - use this a a object in a controller
 *
 * def p = new PaginationHelper(grailsApplication:org.codehaus.groovy.grails.commons.ConfigurationHolder)
 * p.fix(params, "column", "order")
 *
 * where column is the name of the column that will be sorted if a column is not selected
 * and order is the direction of the sort. either "asc" or "desc"
 *
 * 
 * Usage 2 - use as a spring bean
 *
 * in grails-app/conf/spring/resources.groovy
 *
 *	beans { 
 *      ....
 *		pagination(com.ti.specteam.pagination.Pagination) { bean ->
 *			grailsApplication = ref("grailsApplication")
 *		}
 *		...
 *	}
 *
 * class YourController {
 * 		def pagination
 *  ...
 *      def list= {
 *			pagination.fix params, "column", "order"
 *			...
 *		}
 *	...
 *  }
 *
 *
 **/
 
class Pagination {

	def grailsApplication
	
	void fix(Map params, String sort, String order) {
		fixMax params
		fixOffset params
		fixSort params, sort
		fixOrder params, order
	}
	
	private void fixMax(Map params) {
		params.max = Math.min(maximum, new Long(params.max ?: normal))
	}
	
	private void fixOffset(Map params) {
		params.offset = params.offset ?: 0
	}
	
	private void fixSort(Map params, sort) {
		params.sort = params.sort ?: sort
	}
	
	private void fixOrder(Map params, order) {
		params.order = params.order ?: order
	}
	
	private Long getMaximum() {
		grailsApplication?.config?.grails?.pagination?.maximum ?: 1000
	}
	
	private Long getNormal() {
		grailsApplication?.config?.grails?.pagination?.normal ?: 10
	}
	

}
