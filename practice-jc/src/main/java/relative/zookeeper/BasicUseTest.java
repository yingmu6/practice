package relative.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * Zookeeper客服端基本使用
 * https://www.cnblogs.com/LiZhiW/p/4923693.html 
 * @author : chensy
 * Date : 2020-02-05 00:06
 */
public class BasicUseTest {
    public static void main(String[] args)  throws Exception {
       use();
       System.in.read();
    }

    public static void use() throws Exception {
       ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 3000, new Watcher() {
           @Override
           public void process(WatchedEvent event) {
               System.out.println("事件变更通知：state="
                       + event.getState().name() + ",path=" + event.getPath()
                       + ",type=" + event.getType());
           }
       });
       List<String> paths = zookeeper.getChildren("/dubbo", false);
        System.out.println("路径=" + paths);
    }
}

/**
 * https://www.jianshu.com/p/ff0e5bcba698  Zookeeper的常用命令
 * https://juejin.im/post/5d14cdf7f265da1bb96feff2
 *
 * create [-s] [-e] path data acl  : -s 是顺序节点（持久的），会在节点名称上附加序号 -e是临时节点，quit以后会删除
 * delete只能删除不包含子节点的节点，如果要删除的节点包含子节点，使用rmr命令
 * ls path 显示路径下内容
 * get path 查看路径的内容
 *
 * https://www.cnblogs.com/yjmyzz/p/zookeeper-acl-demo.html   ACL(Access Control List)访问控制列表
 * ZK的节点有5种操作权限：
 * CREATE、READ、WRITE、DELETE、ADMIN 也就是 增、删、改、查、管理权限，这5种权限简写为crwda(即：每个单词的首字符缩写)
 * 身份的认证有4种方式：
 * auth的授权方式、digest授权方式
 *
 * http://www.yoonper.com/post.php?id=47 ZooKeeper ACL权限控制（语法讲解很好）
 * 'world,'anyone  // 身份认证模式（默认World方案）
 * : cdrwa         // 操作权限，增删查改的首字母
 *
 * 例子：
 * 设备Acl后 setAcl /pomelo digest:pomelo:zOL5mkauOP5kV9xnATsWSNNqEdw=:cdrwa
 * 退出登录后，要先为添加认证用户 addauth digest pomelo:pomelo
 *
 * 问题：为啥添加认证用户时 addauth digist csy:123， 会报WatchedEvent state:AuthFailed type:None path:null
 * 解：拼写错了，不是digist，而是digest
 */



/**
 * zookeeper分布式协调服务  https://www.w3cschool.cn/zookeeper/zookeeper_overview.html
 * 分布式应用正在运行的一组系统称为集群，而在集群中运行的每台机器被称为节点。分布式应用有两部分， Server（服务器） 和 Client（客户端） 应用程序。
 *ZooKeeper提供的常见服务如下 :
 * 命名服务 - 按名称标识集群中的节点。它类似于DNS，但仅对于节点。
 * 配置管理 - 加入节点的最近的和最新的系统配置信息。
 * 集群管理 - 实时地在集群和节点状态中加入/离开节点。
 * 选举算法 - 选举一个节点作为协调目的的leader。
 * 锁定和同步服务 - 在修改数据的同时锁定数据。此机制可帮助你在连接其他分布式应用程序（如Apache HBase）时进行自动故障恢复。
 * 高度可靠的数据注册表 - 即使在一个或几个节点关闭时也可以获得数据。
 */

/**
 * ZooKeeper文件系统的树结构。ZooKeeper节点称为 znode 。每个znode由一个名称标识，并用路径(/)序列分隔。
 * ZooKeeper数据模型中的每个znode都维护着一个 stat 结构。一个stat仅提供一个znode的元数据。它由版本号，操作控制列表(ACL)，时间戳和数据长度组成。
 * ACL基本上是访问znode的认证机制。它管理所有znode读取和写入操作。znode相关联的数据发生变化时，其对应的版本号也会增加
 * Znode类型被分为持久（persistent）节点，顺序（sequential）节点和临时（ephemeral）节点。
 *
 * Sessions（会话）：会话对于ZooKeeper的操作非常重要。会话中的请求按FIFO顺序执行。一旦客户端连接到服务器，将建立会话并向客户端分配会话ID 。客户端以特定的时间间隔发送心跳以保持会话有效。
 * Watches（监视）：监视是一种简单的机制，ZooKeeper集合中的出现更改时会通知客户端
 *
 * Zookeeper 工作流：
 * 一旦ZooKeeper集合启动，它将等待客户端连接。客户端将连接到ZooKeeper集合中的一个节点。它可以是leader或follower节点。一旦客户端被连接，节点将向特定客户端分配会话ID并向该客户端发送确认。
 * 如果客户端没有收到确认，它将尝试连接ZooKeeper集合中的另一个节点。 一旦连接到节点，客户端将以有规律的间隔向节点发送心跳，以确保连接不会丢失。
 */

/**
 * 分布式应用的优点
 * 可靠性 - 单个或几个系统的故障不会使整个系统出现故障。
 * 可扩展性 - 可以在需要时增加性能，通过添加更多机器，在应用程序配置中进行微小的更改，而不会有停机时间。
 * 透明性 - 隐藏系统的复杂性，并将其显示为单个实体/应用程序。
 *
 * 分布式应用的挑战
 * 竞争条件 - 两个或多个机器尝试执行特定任务，实际上只需在任意给定时间由单个机器完成。例如，共享资源只能在任意给定时间由单个机器修改。
 * 死锁 - 两个或多个操作等待彼此无限期完成。
 * 不一致 - 数据的部分失败。
 */