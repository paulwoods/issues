package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

class RegisterCommandTests {
	
    @Test
    public void matchingPasswordsPassValidation() {
		def cmp = new grails.util.ClosureToMapPopulator()
		def map = cmp.populate(RegisterCommand.constraints)
		assert true  == map.password1.validator.call("x", [password1:"111", password2:"111"])
    }
	
    @Test
    public void mismatchedPasswordsFailValidation() {
		def cmp = new grails.util.ClosureToMapPopulator()
		def map = cmp.populate(RegisterCommand.constraints)
		assert false == map.password1.validator.call("x", [password1:"aaa", password2:"bbb"])
    }
}

