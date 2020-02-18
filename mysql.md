一、MySQL基础知识

    1.数据库常见概念
        DB(Database):数据库,存储数据的容器
        DBMS(Database Management System):数据库管理系统,用于创建和管理DB
        SQL(Structured Query Language):结构化查询语言,用于和数据库通信的语言
    2.SQL的语言分类
        DQL(Data Query Language)：数据查询语言, select相关的语句
        DML(Data Manipulate Language)：数据操作语言,增删改相关的语句
        DDL(Data Define Languge)：数据定义语言,修改表结构的语句
        TCL(Transaction Control Language)：事务控制语言,事务控制语言
    3.MySQL语法规范
        表名,列名,别名建议都小写,因为linux下区分,而windows下不区分,我们统一小写
    4.数据库连接
        mysql -h ip -P 端口 -u 用户名 -p
        -h: 指定连接的主机(host),默认localhost,本地连接时可以不指定该属性
        -P: 指定连接的端口(port),默认3306,本地连接时可以不指定该属性
        -u: 指定连接时的用户名(username)
        -p: 指定连接时的密码(password)
    5.数据库常用命令
        select version();                      登录情况下查看数据库版本
        show databases;                        显示所有数据库
        show databases();                      显示当前数据库
        use  数据库名字;                        进入指定的库
        show tables;                           显示当前数据库所有的表
        show tables from  数据库名字;           显示指定数据库所有的表
        show create table 表名;                查看表的创建语句
        desc 表名;                             查看表的结构     

二、MySQL数据结构()

    统一说明：
       默认都为有符号,无符号标志为unsigned
       当使用zerofill时,会自动将有符号设置为无符号,当不设置zerofill时,设置的位数无效,比如int(5)中的5
       当不设置位数时,默认为最大值对应的十进制数的位数,int为10,bigint为20
       填充0时只能是无符号数
       cmd窗口下可以显示,navicat即使设置了,也显示不出来
        
    1.整数类型
        tinyint          1个字节
        smallint         2个字节
        mediumint        3个字节
        int              4个字节
        bigint           5个字节
        bit              位运算,只能通过sql语句添加,1位可以用来代表true护着false
    2.浮点类型(m表示总长度,d表示小数长度)
        float[(m,d)]     4个字节
        double[(m,d)]    8个字节
        decimal[(m,d)]   由m和d确定,默认
        说明：float和double不指定精度时,会按照实际显示,decimal不指定时,默认整数显示10位,小数0位
    3.日期类型
         类型               格式                        范围
         date            YYYY-MM-DD             1000-01-01/9999-12-31    
         time            HH:MM:SS               -838:59:59/838:59:59
         year            YYYY                   1901/2155
         datetime        YYYY-MM-DD HH:MM:SS    1000-01-01 00:00:00/9999-12-31 23:59:59
         timestamp       时间戳
    4.字符串(UTF-8一个汉字=3个字节,GBK 一个汉字=2个字节)
        类型                大小(字节)                        用途
        char               0-255                        定长
        varchar            0-65535                      变长
        text               0-65535                    长文本数据
        blob               0-65535                    二进制形式的长文本数据

三、MySQL权限
    
    工作原理：通过IP和用户名来确定身份,mysql数据库中user表维护的是连接用户的信息，mysql启动时会将这些信息加载到内存中，
    修改这些表的时候需要重启服务或者执行flush privileges命令，用户登录后会把用户的信息保存到一个连接中，此时修改用户的权限信息，需要重新登录才会生效
    
    1.创建用户
        create user username;       创建无密码所有主机可登录用户
        create user username@IP;    创建无密码指定主机登录用户
        create user username@IP identified by 'zmj227@lf';    创建需要密码指定主机登录用户