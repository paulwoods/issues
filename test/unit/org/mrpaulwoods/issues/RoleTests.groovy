package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(Role)
@Mock(UserRole)
class RoleTests {

    @Test
    public void addRole_new() {
		assert 0 == Role.count()
		Role.addRole("ROLE_A")
		assert 1 == Role.count()
    }

    @Test
    public void addRole_exists() {
		def r1 = Role.addRole("ROLE_A")
		def r2 = Role.addRole("ROLE_A")
		assert 1 == Role.count()
		assert r2 == r1
    }
    
}
