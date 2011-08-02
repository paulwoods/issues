package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(UserProject)
class UserProjectTests {
	
	@Test
    public void canAccess() {
		assert true == new UserProject(access:UserProject.Access.Admin).canAccess()
		assert true == new UserProject(access:UserProject.Access.Edit).canAccess()
		assert true == new UserProject(access:UserProject.Access.Read).canAccess()
		assert false == new UserProject(access:UserProject.Access.None).canAccess()
    }

}
