package org.mrpaulwoods.issues



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SearchController)
class SearchControllerTests {

    @Test
    public void index() {
    	controller.index()
    }
    
    @Test
    public void search() {
    	controller.search()
    }
}
