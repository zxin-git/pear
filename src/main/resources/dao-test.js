/* 
 * 本配置文件声明了整个应用的数据库连接部分。 
 */  
var ioc = {  
    /* 
     * 数据库连接池 
     */  
    dataSource : {  
        type : "com.alibaba.druid.pool.DruidDataSource",  
        fields : {  
            driverClass : "com.mysql.jdbc.Driver",  
            jdbcUrl : "jdbc:mysql://localhost:8066/TESTDB?useUnicode=true&characterEncoding=UTF-8",  
            username : "root",  
            password : "root",
            
            filters:"stat",
            maxActive:"30",
            initialSize:"10",
            maxWait:"60000",
            minIdle:"1",
            timeBetweenEvictionRunsMillis:"60000",
            minEvictableIdleTimeMillis:"300000",
            testWhileIdle:"true",
            testOnBorrow:"false",
            testOnReturn:"false",
            poolPreparedStatements:"true",
            maxOpenPreparedStatements:"20",
        }   
    },  
    /* 
     * 这个配置很好理解， args 表示这个对象构造函数的参数。显然，下面的注入方式将调用 new NutDao(dataSource) 
     */  
    dao : {  
        type : "org.nutz.dao.impl.NutDao",
        args : [ {  
            refer : "dataSource"  
        } ]     
    }  
};  