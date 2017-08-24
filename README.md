# spring_shiro_mybatis
1、本项目由Spring、Spring MVC、Shiro、Mybatis、Velocity等框架搭建
2、eclipse导入时，请先行安装lombok，具体安装请百度，导入时，请用导入maven项目方式，导入整个spring_shiro_mybatis目录即可
3、先导入ssm-script\src\main\resources\db.sql脚本
4、修改ssm-web\src\main\resources\env\env-dev.properties中的数据库连接配置；
   注意：maven默认打包环境配置取的是dev，所以默认只需要修改env-dev.properties配置文件
5、maven打包：
  1、idea打包，请添加run/debug config中的maven配置，命令行可以配置clean package -DMaven.test.skip=true，如果打包生产环境可以加
     clean package -DMaven.test.skip=true -P=pro
  2、eclipse打包，请自行了解
