> # mybatis-generator-plugins(JDK 1.8)

### Mybatis逆向工程插件

- 插件提供lombok注解
- 提供基于FastJson的ToString方法

> 使用方式

- 在mybatis逆向工程引入maven坐标
  ```xml
       <plugin>
         <groupId>org.mybatis.generator</groupId>
         <artifactId>mybatis-generator-maven-plugin</artifactId>
         <version>1.4.1</version>
         <dependencies>
             <dependency>
                 <groupId>cn.crabapples</groupId>
                 <artifactId>mybatis-generator-plugins-ext</artifactId>
                 <version>1.0-SNAPSHOT</version>
             </dependency>
         </dependencies>
     </plugin>
  ```

- mybatis配置文件中配置插件
  ```xml
       <plugin>
         <groupId>org.mybatis.generator</groupId>
         <artifactId>mybatis-generator-maven-plugin</artifactId>
         <version>1.4.1</version>
         <dependencies>
             <dependency>
                 <groupId>cn.crabapples</groupId>
                 <artifactId>mybatis-generator-plugins-ext</artifactId>
                 <version>1.0-SNAPSHOT</version>
             </dependency>
         </dependencies>
     </plugin>
  ```
