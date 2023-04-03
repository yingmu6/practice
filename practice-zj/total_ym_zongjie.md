## 总结笔记
> 记录源码相关联学习的总结

### db相关
* 执行telnet时，在window终端拷贝，按住ctrl+鼠标键双击（2023/03/13）

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