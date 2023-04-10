## 数据库（MySQL）

### 数据库架构

#### SQL执行过程 

![](http://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/DE5B0357E4124930A9A7476389E1FC5C/11545)

#### B+树

B树和B+树的区别：

1. 在B树中，可以将键和值存放在内部节点和叶子节点，但在B+树中，内部节点都是键，没有值。叶子节点同时存放键和值
2. B+树的叶子节点有一条链相连，而B树的叶子节点各自独立。

在查询数据时：

1. 由于B+树的内部节点只存放键，不存放值，因此，一次读取，可以在内存页中获取更多的键，有利于更快地缩小查找范围。
2. B+树的叶节点由一条链相连，因此，当需要进行一次全数据遍历的时候，B+树只需要使用O(logN)时间找到最小的一个节点，然后通过链进行O(N)的顺序遍历即可。而B树则需要对树的每一层进行遍历，这会需要更多的内存置换次数，因此也就需要花费更多的时间

B树在提高了IO性能的同时并没有解决元素遍历的效率低下的问题，正是为了解决这个问题，B+树应用而生。B+树只需要去遍历叶子节点就可以实现整棵树的遍历

### SQL优化及索引

#### SQL优化思路

一个 SQL 执行的很慢，要分两种情况讨论：

1. 大多数情况下很正常，偶尔很慢，则有如下原因

   (1) 数据库在刷新脏页，例如 redo log 写满了需要同步到磁盘。**解决方法：根据主机的IO能力设置innodb_io_capacity（默认为0）参数**

   (2) 执行的时候，遇到锁，如表锁、行锁。**用`show processlist` 命令查看是否在等待锁，等待锁释放**

2. 这条 SQL 语句一直执行的很慢，则有如下原因。

   (1) 没有用上索引：例如该字段没有索引；由于对字段进行运算、函数操作导致无法用索引。

   (2) 数据库选错了索引。索引系统是通过遍历部分数据，也就是通过**采样**的方式，来预测索引的基数的。采样有可能出现**失误**的情况。解决的方法可以通过强制走索引的方式来查询。也可以通过

   ```sql
   show index from t;
   ```

   来查询索引的基数和实际是否符合，如果和实际很不符合的话，可以重新来统计索引的基数，可以用下面这条命令来重新统计分析

   ```sql
   analyze table t;
   ```

   <img src="http://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/47958803FD094550A1C5FB9DF671FD11/11683" style="zoom:50%;" />

#### limit优化

1. 避免请求不必要的数据：避免 `select *` 的写法

2. 结合order by对检索结果进行排序，目的是为了使用索引。使用order by后，数据库引擎先对检索列的索引进行从小到大排序并限定了偏移量与索引节点的数量。偏移量大时不适用

3. 将 `limit` 替换为已知位置的查询：使用`where`

   ```sql
   select id, name from t where position between 10 and 20;
   ```

4. 记录上次查询结果的位置，避免使用`offset`：（高效率实现上下翻页）

#### SQL性能差异的原因

SQL逻辑相同，性能差异较大的，大概有以下几类:

一、字段发生了转换，导致本该使用索引而没有用到索引

1. 条件字段函数操作
2. 隐式类型转换
3. 隐式字符编码转换
   (如果驱动表的字符集比被驱动表得字符集小，关联列就能用到索引，如果更大，需要发生隐式编码转换，则不能用到索引，latin<gbk<utf8<utf8mb4)

二、嵌套循环，驱动表与被驱动表选择错误

1. 连接列上没有索引，导致大表驱动小表，或者小表驱动大表(但是大表走的是全表扫描) --连接列上建立索引
2. 连接列上虽然有索引，但是驱动表任然选择错误。--通过straight_join强制选择关联表顺序
3. 子查询导致先执行外表在执行子查询，也是驱动表与被驱动表选择错误。
   --可以考虑把子查询改写为内连接，或者改写内联视图(子查询放在from后组成一个临时表，在于其他表进行关联)
4. 只需要内连接的语句，但是写成了左连接或者右连接。比如select * from t left join b on t.id=b.id where b.name='abc'驱动表被固定，大概率会扫描更多的行，导致效率降低.
   --根据业务情况或sql情况，把左连接或者右连接改写为内连接

三、索引选择不同，造成性能差异较大

1. select * from t where aid= and create_name>'' order by id limit 1;
   选择走id索引或者选择走(aid，create_time)索引，性能差异较大.结果集都有可能不一致
   --这个可以通过where条件过滤的值多少来大概判断，该走哪个索引

四、其它一些因素

1. 比如之前学习到的是否有MDL X锁
2. innodb_buffer_pool设置得太小，innodb_io_capacity设置得太小，刷脏速度跟不上
3. 是否是对表做了DML语句之后，马上做select，导致change buffer收益不高
4. 是否有数据空洞
5. select选取的数据是否在buffer_pool中
6. 硬件原因，资源抢占

### 索引

Hash索引和B+ Tree索引：Hash索引不能做范围查询

聚簇索引（主键索引）和非聚簇索引（非主键索引） → 回表 → 覆盖索引 → 联合索引 → 最左匹配原则

![](http://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/4E04B38ED784486286A182B50BDDB3CA/11554)

#### 唯一索引和普通索引的选择（change buffer）

[MySQL:change buffer](https://www.cnblogs.com/virgosnail/p/10454150.html)

1. 唯一索引的更新不能使用change buffer，也只有普通索引可以使用
2. 如果所有的更新后面，都马上伴随着对这个记录的查询，那么应该关闭change buffer
3. **redo log 主要节省的是随机写磁盘的IO消耗（转成顺序写），而change buffer主要节省的则是随机读磁盘的IO消耗。**
4. 如果业务不能保证，或者业务就是要求数据库来做约束，那么没得选，必须创建唯一索引。
5. 在一些“归档库”的场景，可以考虑使用普通索引（配合change buffer）。比如，线上数据只需要保留半年，然后历史数据保存在归档库。这时候，归档数据已经是确保没有唯一键冲突了。要提高归档效率，可以考虑把表里面的唯一索引改成普通索引。

#### 索引选择异常和处理

优化器会结合扫描行数、是否使用临时表、是否排序等因素综合选择执行方案。其中，扫描行数是根据统计信息来估算的。这个统计信息就是索引的“区分度”。一个索引上不同的值（基数）越多，索引的区分度就越好。

基数的获取是使用采样统计法。采样统计的时候，InnoDB默认会选择N个数据页，统计这些页面上的不同值，得到一个平均值，然后乘以这个索引的页面数，就得到了这个索引的基数。

如果发现explain的结果预估的rows值跟实际情况差距比较大，可以使用analyze table t 命令重新统计索引信息。对于其他优化器误判的情况，可以在应用端用force index来强行指定索引，也可以通过修改语句来引导优化器，还可以通过增加或者删除索引来绕过这个问题。

#### order by

MySQL会给每个线程分配一块内存用于排序，称为sort_buffer。

排序动作可能在内存中完成，也可能需要使用外部排序，这取决于排序所需的内存和参数sort_buffer_size。

sort_buffer_size，就是MySQL为排序开辟的内存（sort_buffer）的大小。如果要排序的数据量小于sort_buffer_size，排序就在内存中完成。但如果排序数据量太大，内存放不下，则利用磁盘临时文件辅助排序。

### 事务

[MySQL 中隔离级别 RC 与 RR 的区别](https://www.cnblogs.com/digdeep/p/4968453.html)

#### 事务特性

**一致性，原子性，隔离性，永久性**

begin/start transaction 命令并不是一个事务的起点，在执行到它们之后的第一个操作InnoDB表的语句（第一个快照读语句），事务才真正启动。如果想要马上启动一个事务，可以使用start transaction with consistent snapshot 命令

#### 隔离级别

| 隔离级别\问题                | 脏读     | 不可重复读 | 幻读     |
| ---------------------------- | -------- | ---------- | -------- |
| 读未提交（read uncommitted） | √ 可能   | √ 可能     | √ 可能   |
| 读已提交（read commited）    | × 不可能 | √ 可能     | √ 可能   |
| 可重复读（repeatable read）  | × 不可能 | × 不可能   | √ 可能   |
| 串行化                       | × 不可能 | × 不可能   | × 不可能 |

**脏读**：可以读取未提交的数据。RC 要求解决脏读；

**不可重复读**：同一个事务中多次执行同一个select，读取到的数据发生了改变(被其它事务update并且提交)；

**可重复读**：同一个事务中多次执行同一个select， 读取到的数据没有发生改变(一般使用MVCC实现)；RR隔离级别要求达到可重复读的标准；

**幻读**：同一个事务中多次执行同一个select，读取到的数据行发生改变。也就是行数减少或者增加了(被其它事务delete/insert并且提交)。SERIALIZABLE要求解决幻读问题。在可重复读隔离级别下，普通的查询是快照读，是不会看到别的事务插入的数据的。因此，幻读在”当前读”（加for update读写锁）下才会出现。

**这里一定要区分 不可重复读 和 幻读：**

不可重复读的重点是**修改**:
同样的条件的select，你读取过的数据, 再次读取出来发现值不一样了

幻读的重点在于**新增或者删除**:
同样的条件的select，第1次和第2次读出来的记录数不一样

在MySQL里，有两个“视图”的概念：

- 一个是view。它是一个用查询语句定义的虚拟表，在调用的时候执行查询语句并生成结果。创建视图的语法是create view ... ，它的查询方法与表一样。
- 另一个是InnoDB在实现MVCC时用到的**一致性读视图**，即consistent read view，用于支持RC（Read Committed，读提交）和RR（Repeatable Read，可重复读）隔离级别的实现。

> **事务的可重复读的能力是怎么实现的？**
>
> 1. 可重复读的核心就是一致性读（consistent read）；
> 2. 而事务更新数据只能用当前读（更新数据都是先读后写，这个读，只能读当前的值，称为“当前读”（current read））。
> 3. 如果当前的记录的行锁被其他事务占用的话，就需要进入锁等待。

> **读已提交和可重复读的区别**
>
> 1. 在可重复读隔离级别下，只需要在事务开始的时候创建一致性视图，之后事务里的其他查询都共用这个一致性视图；（查询只承认在事务启动前就已经提交完成的数据）
> 2. 在读提交隔离级别下，每一个语句执行前都会重新算出一个新的视图（查询只承认在语句启动前就已经提交完成的数据）

#### MVCC

1. 所谓的MVCC（Multi-Version Concurrency Control， 多版本并发控制） 指的就是在使用read commited、 repeatable read这两种隔离级别的事务在执行普通的select操作时访问记录的版本链的过程， 这样子可以使不同事务的读-写、 写-读操作并发执行， 从而提升系统性能 
2. read commited、 repeatable read这两个隔离级别的一个很大不同就是： **生成ReadView的时机不同**， read commited在每一次进行普通select操作前都会生成一个ReadView， 而repeatable read只在第一次进行普通select操作前生成一个ReadView， 之后的查询操作都重复使用这个ReadView 
3. 通过undo log多版本链条，加上开启事务时候生产的一个ReadView，然后再有一个查询的时候，根据ReadView进行判断的机制，就知道应该读取哪个版本的数据。 
4. 每条数据都有两个隐藏字段，一个是trx_id，一个是roll_pointer，trx_id是最近一次更新这条数据的事务id，roll_pointer指向更新这个事务之前生成的undo log
5. 执行一个事务的时候，会生成一个ReadView
   - 若自己的事务id < ReadView中的最大事务id值，去undolog版本链中寻找最近一次更新这条数据的事务id
   - 若自己的事务id > ReadView中的最大事务id值，则使用自己事务id

> **事务如何实现的MVCC？**
>
> 1. 每个事务都有一个事务ID，叫做transaction id（严格递增）
> 2. 事务在启动时，找到已提交的最大事务ID记为up_limit_id。
> 3. 事务在更新一条语句时，比如id=1改为了id=2，会把id=1和该行之前的row trx_id写到undo log里，
>    并且在数据页上把id的值改为2，并且把修改这条语句的transaction id记在该行行头
> 4. 一个事务要查看一条数据时，必须先用该事务的up_limit_id与该行的transaction id做比对，如果up_limit_id>=transaction id，那么可以看；如果up_limit_id<transaction id，则只能去undo log里去取。去undo log查找数据的时候，也需要做比对，必须up_limit_id>transaction id，才返回数据


### 锁

#### 全局锁（对整个数据库实例加锁）

MySQL加全局读锁的命令是 Flush tables with read lock (FTWRL)。加全局锁后，数据更新语句（数据的增删改）、数据定义语句（包括建表、修改表结构等）和更新类事务的提交语句会被阻塞

**全局锁的典型使用场景是，做全库逻辑备份**。MySQL逻辑备份工具是mysqldump。当mysqldump使用参数–single-transaction的时候，导数据之前就会启动一个事务，来确保拿到一致性视图。由于MVCC的支持，这个过程中数据可以正常更新

> **single-transaction方法只适用于所有的表使用事务引擎（比如InnoDB）的库**
>
> 不支持事务的引擎（MyISAM），备份只能通过FTWRL方法
>
> **不推荐使用set global readonly=true**方法。
>
> - 在有些系统中，readonly的值会被用来做其他逻辑，比如用来判断一个库是主库还是备库
> - 如果执行FTWRL命令之后由于客户端发生异常断开，那么MySQL会自动释放这个全局锁。将整个库设置为readonly之后，如果客户端发生异常，则数据库就会一直保持readonly状态

#### 表级别锁

有两种表级别锁：表锁，元数据锁（meta data lock，MDL)

表锁：

- **意向共享锁**（事务想要获得一张表中某几行的共享锁）

- **意向排它锁**（事务想要获得一张表中某几行的排他锁）

- **语法是 lock tables … read/write。**可以用unlock tables主动释放锁，也可以在客户端断开的时候自动释放

**MDL**：（MySQL 5.5版本中引入）

- MDL不需要显式使用，在访问一个表的时候会被自动加上。MDL的作用是，保证读写的正确性。
- 对一个表做增删改查操作的时候，加MDL读锁；
- 当要对表做结构变更操作的时候，加MDL写锁。

> 事务中的MDL锁，在语句执行开始时申请，但是语句结束后并不会马上释放，而会等到整个事务提交后再释放。在做表结构变更的时候，一定要小心不要导致锁住线上查询和更新。
>
> **如何安全地给小表加字段**
>
> - 要做DDL变更的表刚好有长事务在执行，要考虑先暂停DDL，或者kill掉这个长事务
> - 要变更的表是一个请求频繁的热点表，在alter table语句里面设定等待时间，如果在这个指定的等待时间里面能够拿到MDL写锁最好，拿不到也不要阻塞后面的业务语句，先放弃。之后开发人员或者DBA再通过重试命令重复这个过程。

#### 行锁

读锁（S锁，共享锁，允许事务读一行数据）

写锁（X锁，排它锁，允许事务更新或删除一行数据）

在InnoDB事务中，行锁是在需要的时候才加上的，但并不是不需要了就立刻释放，而是要等到事务结束时才释放。这个就是**两阶段锁协议**。

**InnoDB有三种行锁的算法：**

- Record Lock：单个行记录上的锁。
- Gap Lock：间隙锁，锁定一个范围，但不包括记录本身。GAP锁的目的，是为了防止同一事务的两次当前读，出现幻读的情况，GAP锁在可重复读隔离级别下才会生效。
- Next-Key Lock：1+2（间隙锁和行锁合称next-key lock），锁定一个范围，并且锁定记录本身。每个next-key lock是前开后闭区间，下限-∞，上限suprenum（InnoDB给每个索引加的一个不存在的最大值）。对于行的查询，都是采用该方法，主要目的是解决幻读的问题

#### 加锁规则

**两个“原则”、两个“优化”和一个“bug”：**

- 原则1：加锁的基本单位是next-key lock。next-key lock是前开后闭区间。
- 原则2：查找过程中访问到的对象才会加锁。
- 优化1：索引上的等值查询，给唯一索引加锁的时候，next-key lock退化为行锁。
- 优化2：索引上的等值查询，向右遍历时且最后一个值不满足等值条件的时候，next-key lock退化为间隙锁。
- 一个bug：唯一索引上的范围查询会访问到不满足条件的第一个值为止。

#### 死锁

避免死锁：

> 通过表级锁来减少死锁产生的概率；
>
> 多个程序尽量约定以相同的顺序访问表（这也是解决并发理论中哲学家就餐问题的一种思路）；
>
> 同一个事务尽可能做到一次锁定所需要的所有资源。
>
> 控制并发度：数据库服务端做并发控制。可以考虑将一行改成逻辑上的多行来减少锁冲突，需要根据业务逻辑做详细设计。

解决死锁：超时机制，InnoDB使用等待图（包括锁的信息链表，事务等待链表）机制进行死锁检测

> 超时机制：直接进入等待，直到超时。这个超时时间可以通过参数innodb_lock_wait_timeout（默认50s）来设置。
>
> 死锁检测（主要使用的方法）：发现死锁后，主动回滚死锁链条中的某一个事务，让其他事务得以继续执行。将参数innodb_deadlock_detect设置为on，表示开启这个逻辑。

### 日志

#### binlog（归档日志）

- binlog是MySQL的Server层的日志，所有引擎都可以使用。
- redo log是物理日志，记录的是“在某个数据页上做了什么修改”；binlog是逻辑日志，记录的是这个语句的原始逻辑，比如“给ID=2这一行的c字段加1 ”。
- binlog可以追加写入。“追加写”是指binlog文件写到一定大小后会切换到下一个，并不会覆盖以前的日志。

**binlog的写入逻辑**：

- 事务执行过程中，先把日志写到binlog cache；事务提交的时候，再把binlog cache写到binlog文件中。
- 一个事务的binlog不能被拆开，因此不论这个事务多大，也要确保一次性写入。
- 系统给binlog cache分配了一片内存，每个线程一个，参数 binlog_cache_size用于控制单个线程内binlog cache所占内存的大小。如果超过了这个参数规定的大小，就要暂存到磁盘。
- 事务提交的时候，执行器把binlog cache里的完整事务写入到binlog中，并清空binlog cache。

<img src="http://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/4AEA829BC456441CAC428CAAB88A1180/12391" alt="image" style="zoom:50%;" />

每个线程有自己的binlog cache，但是共用同一份binlog文件。

- 图中的write，指的就是指把日志写入到文件系统的page cache，并没有把数据持久化到磁盘，所以速度比较快。
- 图中的fsync，才是将数据持久化到磁盘的操作。一般情况下，我们认为fsync才占磁盘的IOPS。 

> 为什么 binlog cache 是每个线程自己维护的，而 redo log buffer 是全局共用的
>
> 1. binlog是不能“被打断的”。一个事务的binlog必须连续写，因此要整个事务完成后，再一起写到文件里。
>
>    而redo log并没有这个要求，中间有生成的日志可以写到redo log buffer中。
>
> 2. binlog存储是以statement或者row格式存储的，而redo log是以page页格式存储的。page格式，天生就是共有的，而row格式，只跟当前事务相关

write 和fsync的时机，是由参数sync_binlog控制的：

1. sync_binlog=0的时候，表示每次提交事务都只write，不fsync；
2. sync_binlog=1的时候，表示每次提交事务都会执行fsync；
3. sync_binlog=N(N>1)的时候，表示每次提交事务都write，但累积N个事务后才fsync。

参数设置建议：

- 在出现IO瓶颈的场景里，将sync_binlog设置成一个比较大的值，可以提升性能。
- 实际的业务场景中，考虑到丢失日志量的可控性，一般不建议将这个参数设成0，比较常见的是将其设置为100~1000中的某个数值。
- 将sync_binlog设置为N，对应的风险是：如果主机发生异常重启，会丢失最近N个事务的binlog日志。

#### redolog（重做日志）

MySQL中的redolog使用了WAL（Write-Ahead Logging）技术，**关键点就是先写日志，再写磁盘**。**redo log是InnoDB引擎特有的日志**。

InnoDB依赖redo log实现**crash-safe**，即保证即使数据库发生异常重启，之前提交的记录都不会丢失。

当有一条记录需要更新的时候，InnoDB引擎就会先把记录写到redo log里面，并更新内存，这个时候更新就算完成了。同时，InnoDB引擎会在适当的时候，将这个操作记录更新到磁盘里面，而这个更新往往是在系统比较空闲的时候做。

InnoDB的redo log是固定大小的。比如可以配置为一组4个文件，每个文件的大小是1GB，那么redolog总共就可以记录4GB的操作。从头开始写，写到末尾就又回到开头循环写。

<img src="https://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/A1A4A3EF177F463EBAE0A503AAED32CA/9447" alt="image" style="zoom:50%;" />

- write pos是当前记录的位置，一边写一边后移，写到第3号文件末尾后就回到0号文件开头。


   - checkpoint是当前要擦除的位置，也是往后推移并且循环的，擦除记录前要把记录更新到数据文件。


   - 如果write pos追上checkpoint，这时候不能再执行新的更新，得停下来先擦掉一些记录，把checkpoint推进一下。

redo log可能存在的三种状态：

1. 存在redo log buffer中，物理上是在MySQL进程内存中，就是图中的红色部分；
2. 写到磁盘(write)，但是没有持久化（fsync)，物理上是在文件系统的page cache里面，也就是图中的黄色部分；
3. 持久化到磁盘，对应的是hard disk，也就是图中的绿色部分。

<img src="http://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/F595DF7072514B24988B41FA2FAAB2AA/12394" style="zoom:67%;" />

为了控制redo log的写入策略，InnoDB提供了innodb_flush_log_at_trx_commit参数，它有三种可能取值：

1. 设置为0的时候，表示每次事务提交时都只是把redo log留在redo log buffer中;
2. 设置为1的时候，表示每次事务提交时都将redo log直接持久化到磁盘；
3. 设置为2的时候，表示每次事务提交时都只是把redo log写到page cache。

InnoDB有一个后台线程，每隔1秒，就会把redo log buffer中的日志，调用write写到文件系统的page cache，然后调用fsync持久化到磁盘。

**注意**，事务执行中间过程的redo log也是直接写在redo log buffer中的，这些redo log也会被后台线程一起持久化到磁盘。也就是说，**一个没有提交的事务的redo log，也是可能已经持久化到磁盘的**。

一个没有提交的事务的redo log写入到磁盘中的三种情况：

1. 后台线程每秒一次的轮询操作，如上文所述
2. **redo log buffer占用的空间即将达到 innodb_log_buffer_size一半的时候，后台线程会主动写盘。**此时，由于这个事务并没有提交，所以这个写盘动作只是write，而没有调用fsync，也就是只留在了文件系统的page cache。
3. **并行的事务提交的时候，顺带将这个事务的redo log buffer持久化到磁盘。**假设一个事务A执行到一半，已经写了一些redo log到buffer中，这时候有另外一个线程的事务B提交，如果innodb_flush_log_at_trx_commit设置的是1，那么按照这个参数的逻辑，事务B要把redo log buffer里的日志全部持久化到磁盘。这时候，就会带上事务A在redo log buffer里的日志一起持久化到磁盘。

> 通常我们说MySQL的“双1”配置，指的就是sync_binlog和innodb_flush_log_at_trx_commit都设置成 1。也就是说，一个事务完整提交前，需要等待两次刷盘，一次是redo log（prepare 阶段），一次是binlog

redo log组提交（group commit）机制：

redo log中有日志逻辑序列号（log sequence number，LSN）的概念。LSN是单调递增的，用来对应redo log的一个个写入点。每次写入长度为length的redo log， LSN的值就会加上length。LSN也会写到InnoDB的数据页中，来确保数据页不会被多次执行重复的redo log。

三个并发事务(trx1, trx2, trx3)在prepare 阶段，都写完redo log buffer，持久化到磁盘的过程，对应的LSN分别是50、120 和160。

<img src="http://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/F0AC7FBFFD1A4AEBAA526EF1DE9C1A30/12402" style="zoom: 33%;" />

在上图中：

1. trx1是第一个到达的，会被选为这组的 leader；
2. 等trx1要开始写盘的时候，这个组里面已经有了三个事务，这时候LSN也变成了160；
3. trx1去写盘的时候，带的就是LSN=160，因此等trx1返回时，所有LSN小于等于160的redo log，都已经被持久化到磁盘；
4. 这时候trx2和trx3就可以直接返回了。

一次组提交里面，组员越多，节约磁盘IOPS的效果越好。在并发更新场景下，第一个事务写完redo log buffer以后，接下来这个fsync越晚调用，组员可能越多，节约IOPS的效果就越好。为了让一次fsync带的组员更多，MySQL做了如下优化：

左边图中的“写binlog”被当成一个动作。但实际上，写binlog是分成两步的：

1. 先把binlog从binlog cache中写到磁盘上的binlog文件；
2. 调用fsync持久化。

MySQL为了让组提交的效果更好，把redo log做fsync的时间拖到了步骤1之后，如右图：

<img src="http://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/52AAFDFE3B4A41488014D3FD08A584CA/12398" style="zoom: 33%;" />

做如上优化之后，binlog也可以组提交了。在执行上图中第4步把binlog fsync到磁盘时，如果有多个事务的binlog已经写完了，也是一起持久化的，这样也可以减少IOPS的消耗。

#### undolog（回滚日志）

为了实现事务的原子性， InnoDB存储引擎在实际进行增、 删、 改一条记录时， 都需要先把对应的undo日志记下来。 一般每对一条记录做一次改动， 就对应着一条undo日志 。undolog日志形成一条版本链

### 高可用与读写分离

1. MySQL 读写分离的实现

   基于主从复制架构，一个主库，挂多个从库，单单只是写主库，然后主库会自动把数据给同步到从库上去。

2. MySQL 主从复制原理

   主库将变更写入 binlog 日志，然后从库连接到主库之后，从库有一个 IO 线程，将主库的 binlog 日志拷贝到自己本地，写入一个 relay 中继日志中。

   接着从库中有一个 SQL 线程会从中继日志读取 binlog，然后执行 binlog 日志中的内容，也就是在自己本地再次执行一遍 SQL，这样就可以保证自己跟主库的数据是一样的。

   ![](http://note.youdao.com/yws/public/resource/aba0f08fcb448be8bda00fbd1ddd049d/xmlnote/WEBRESOURCEf08d784a6646fa3378b31cacc6b1d3d3/9838)

3. MySQL 主从同步的延时问题

   以前线上确实处理过因为主从同步延时问题而导致的线上的 bug，属于小型的生产事故。

   是这个么场景。有个同学是这样写代码逻辑的。先插入一条数据，再把它查出来，然后更新这条数据。在生产环境高峰期，写并发达到了 2000/s，这个时候，主从复制延时大概是在小几十毫秒。线上会发现，每天总有那么一些数据，我们期望更新一些重要的数据状态，但在高峰期时候却没更新。用户跟客服反馈，而客服就会反馈给我们。

   我们通过 MySQL 命令：

   ```
   show status
   ```

   查看 `Seconds_Behind_Master` ，可以看到从库复制主库的数据落后了几 ms。

   一般来说，如果主从延迟较为严重，有以下解决方案：

   - 分库，将一个主库拆分为多个主库，每个主库的写并发就减少了几倍，此时主从延迟可以忽略不计。
   - 打开 MySQL 支持的并行复制，多个库并行复制。如果说某个库的写入并发就是特别高，单库写并发达到了 2000/s，并行复制还是没意义。
   - 重写代码，写代码的同学，要慎重，插入数据时立马查询可能查不到。
   - 如果确实是存在必须先插入，立马要求就查询到，然后立马就要反过来执行一些操作，对这个查询**设置直连主库**。**不推荐**这种方法，你要是这么搞，读写分离的意义就丧失了。