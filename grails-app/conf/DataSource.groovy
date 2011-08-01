dataSource {
	dialect = org.hibernate.dialect.MySQLInnoDBDialect
	username = "issues"
	password = "issues"
	driverClassName = "com.mysql.jdbc.Driver"
}

hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.provider_class = "net.sf.ehcache.hibernate.EhCacheProvider"
}

environments {
	development {
		dataSource {
			dbCreate = "create-drop"
			url = "jdbc:mysql://localhost/issues_dev?useUnicode=true&characterEncoding=utf8"
		}
	}

	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost/issues_test?useUnicode=true&characterEncoding=utf8"
		}
	}

	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost/issues?useUnicode=true&characterEncoding=utf8"
		}
		
	}

}
