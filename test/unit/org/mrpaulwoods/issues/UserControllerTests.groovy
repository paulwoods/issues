package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(UserController)
class UserControllerTests {

    @Test
    public void index() {
    	controller.index()
    }
    
    @Test
    public void profile() {
    	controller.profile()
    }
    
}
