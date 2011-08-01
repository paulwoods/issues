package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(RoleController)
class RoleControllerTests {

    @Test
    public void index() {
		controller.index()
    }
}
