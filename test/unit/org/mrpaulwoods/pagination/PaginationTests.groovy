package org.mrpaulwoods.pagination

import grails.test.mixin.*
import org.junit.*

// @TestFor(Pagination)
class PaginationTests {
	
	def pagination
	
	@Before
	public void setup() {
		pagination = new Pagination()
	}
	
	@Test
	public void fix_noParams() {
		
		def params = [:]
		pagination.fix params, "alpha", "desc"
		
		assert 10 == params.max
		assert 0 == params.offset
		assert "alpha" == params.sort
		assert "desc" == params.order		
	}
	
	@Test
	public void fix_sortAndOrder() {
		
		def params = [max:30, offset:15, sort:"col1", order:"asc"]
		pagination.fix params, "alpha", "desc"
		
		assert 30 == params.max
		assert 15 == params.offset
		assert "col1" == params.sort
		assert "asc" == params.order		
	}
	
	@Test
	public void maximum() {
		
		pagination.grailsApplication = [ config : [ grails : [ pagination : null ] ] ]
		assert 1000 == pagination.maximum

		pagination.grailsApplication = [ config : [ grails : null ] ]
		assert 1000 == pagination.maximum
	
		pagination.grailsApplication = [ config : null ]
		assert 1000 == pagination.maximum
	
		pagination.grailsApplication = null
		assert 1000 == pagination.maximum
	
	}
	
	@Test
	public void normal() {
		
		pagination.grailsApplication = [ config : [ grails : [ pagination : null ] ] ]
		assert 10 == pagination.normal

		pagination.grailsApplication = [ config : [ grails : null ] ]
		assert 10 == pagination.normal
	
		pagination.grailsApplication = [ config : null ]
		assert 10 == pagination.normal
	
		pagination.grailsApplication = null
		assert 10 == pagination.normal
	
	}
	
}
	



/*
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
*/
	
/*	
    def "can construct"() {
   		expect:
   		new Pagination() != null
    }
    
    def "fix the max to the default"() {
    	
    	setup:
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	def params = [:]
    	
    	when:
    	pagination.fix params, COLUMN, ASC
    	
    	then:
    	NORMAL == params.max
    }
    
    def "if max is out of range, lower it to the maximum value"() {
   		
   		setup:
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	def params = [max:9000]
    	
    	when:
    	pagination.fix params, COLUMN, ASC
    	
    	then:
    	MAXIMUM == params.max
    }
	
	def "default offset is 0"() {
   		setup:
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	def params = [:]
    	
    	when:
    	pagination.fix params, COLUMN, ASC
    	
    	then:
    	0 == params.offset
	}
	
	def "offset is not changed"() {
	
   		setup:
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	def offset = 123
    	def params = [offset:offset]
    	
    	when:
    	pagination.fix params, COLUMN, ASC
    	
    	then:
    	offset == params.offset
	}
    
    def "no sort in params uses the sort parameter"() {
   		setup:
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	def params = [:]
    	
    	when:
    	pagination.fix params, COLUMN, ASC
    	
    	then:
    	COLUMN == params.sort
    }
    
    def "sort in params is unchanged"() {
   		setup:
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	def alpha = "alpha"
    	def params = [sort:alpha]
    	
    	when:
    	pagination.fix params, COLUMN, ASC
    	
    	then:
    	alpha == params.sort
    }
    
    def "no order in params defaults to order parameter"() {
   		setup:
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	def params = [:]
    	
    	when:
    	pagination.fix params, COLUMN, ASC
    	
    	then:
    	ASC == params.order
    }
     
    def "order in params is unchanged"() {
   		setup:
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	def desc = "desc"
    	def params = [order:desc]
    	
    	when:
    	pagination.fix params, COLUMN, ASC
    	
    	then:
    	desc == params.order
    }
    
    def "not set or invalid config are gracefully handeled - 1"() {

    	given:
		grailsApplication = [ config : [ grails : [ pagination : null ] ] ]
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	
    	expect:
    	REAL_MAXIMUM == pagination.maximum
    	REAL_NORMAL == pagination.normal
    }
    
    def "not set or invalid config are gracefully handeled - 2"() {

    	given:
		grailsApplication = [ config : [ grails : null ] ]
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	
    	expect:
    	REAL_MAXIMUM == pagination.maximum
    	REAL_NORMAL == pagination.normal
    }
     
    def "not set or invalid config are gracefully handeled - 3"() {

    	given:
		grailsApplication = [ config : null ]
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	
    	expect:
    	REAL_MAXIMUM == pagination.maximum
    	REAL_NORMAL == pagination.normal
    }
     
    def "not set or invalid config are gracefully handeled - 4"() {

    	given:
		grailsApplication = null
    	def pagination = new Pagination(grailsApplication:grailsApplication)
    	
    	expect:
    	REAL_MAXIMUM == pagination.maximum
    	REAL_NORMAL == pagination.normal
    }
*/

