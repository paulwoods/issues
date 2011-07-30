package org.mrpaulwoods.issues



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(IssueController)
class IssueControllerTests {

    @Test
    public void index() {
    	controller.index()
    }
}
