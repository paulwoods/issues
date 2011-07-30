package org.mrpaulwoods.issues

class Project {
	
	String name
	
    static mapping = {
    	sort "name"
    }
    
    static constraints = {
    	name nullable:false, blank:false, unique:true, maxSize:100
    }
    
    def getIssues() {
    	Issue.findByProject this
    }
    
    
}
