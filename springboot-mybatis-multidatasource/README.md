## springboot-mybatis-multi-datasource
> springboot多数据源整合(mysql/sqlserver)

### 配置
在配置文件application.properties文件中，db1表示默认数据库，db2表示第二个数据源数据库。

### 数据库密码加密
数据库密码已经使用jasypt加密，需要进入到jasypt.1.9.2的jar包目录中执行,需要JDK环境。

> 加密

`java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="admin@123456" password=region algorithm=PBEWithMD5AndDES`

> 解密：

`java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="mJZExTRNWXVA1AsdvDWRCw==" password=region algorithm=PBEWithMD5AndDES`

