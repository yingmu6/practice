## 总结笔记
> 记录源码相关联学习的总结

### gz相关
* MapStruct是否生效，可看字节码目录有没有产生实现类，可能是没有完成引入maven，也可能与lombok包冲突（2023/03/23）
* Mybatis相关文档（2023/03/31）
  * https://mybatis.org/mybatis-3/sqlmap-xml.html 官方文档
  * https://www.tutorialspoint.com/mybatis/mybatis_quick_guide.htm 讲解较好文档 
* 雪花算法相关介绍（2023/04/03）
  * https://juejin.cn/post/6965510420387856398 雪花算法介绍（既解决主键重复，也保证有序性）
  * https://betterprogramming.pub/implementing-snowflake-algorithm-in-golang-c1098fdc73d0 实现雪花算法
  * https://en.wikipedia.org/wiki/Snowflake_ID 维基百科
  * https://github.com/xitu/gold-miner/blob/master/TODO/uuid-or-guid-as-primary-keys-be-careful.md  主键id暴露的问题
* Mockito介绍 https://www.tutorialspoint.com/mockito/mockito_quick_guide.htm
* Javassit介绍 https://www.javassist.org/tutorial/tutorial.html
  * https://www.baeldung.com/javassist 
  * https://jse.readthedocs.io/en/latest/jdk8/javassistTutorial.html   Javassist Tutorial
* 编程式事务 https://www.baeldung.com/spring-programmatic-transaction-management
  * https://spring.io/guides/gs/managing-transactions/ 

### 工具相关
* Arthas is a Java Diagnostic tool open sourced by Alibaba.（可以不用修改代码、重启服务就能解决线上问题） 
  * https://github.com/alibaba/arthas
  * 启动方式：1）使用java -jar arthas-boot.jar，2）as.bat <pid>
  * 常用命令：1）jad反编译 jad org.apache.dubbo.common.bytecode.Wrapper0 查看封装类
* junit4与5的差异点比较 https://medium.com/@wenqiglantz/junit-migration-guide-from-junit-4-to-junit-5-95b5700431a6
  * junit5的@RunWith 
     * https://www.cnblogs.com/qingmuchuanqi48/p/11886618.html 
     * https://www.baeldung.com/junit-5-runwith
* Logstash日志采集：https://www.elastic.co/cn/logstash
* java序列化 https://www.digitalocean.com/community/tutorials/serialization-in-java
* markdown语法 https://www.markdownguide.org/extended-syntax/
* Cmder使用（类似Mac item2）https://github.com/cmderdev/cmder

### 零散相关
* 比较好的英语学习网址 https://learnenglish.britishcouncil.org

### 问题解决相关
* Mybatis不能执行批量添加和修改问题
  * 问题描述：使用<foreach>执行批量添加、修改异常
  * 解决方案：
    * 方案一：先写个另外的SQL语句，看下<foreach>的使用方法
    * 方案二：对比之前BaseDAO的封装，也看下官方文档是怎么处理批量的
    * 方案三：对比之前gz的写法，看下怎么执行批量处理