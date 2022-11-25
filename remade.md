### mybatis-generator-plugins(JDK 1.8)
### Mybatis逆向工程插件(JDK 1.8)

> 提供lombok注解`cn.crabapples.LombokPlugin`
> add lombok annotation `cn.crabapples.LombokPlugin`

> 提供基于FastJson的ToString方法`cn.crabapples.FastJsonToStringPlugin`
> add use fastjson use fastjson override toString()

> 使用方式 Use Description 

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
      <context>
        <!--lombok插件-->
        <!--lombok plugins -->
        <plugin type="cn.crabapples.LombokPlugin">
            <!--配置需要添加到生成的实体类上的lombok注解-->
            <!--config need add to entity annotation-->
            <property name="annotation" value="Getter,Setter,Data,ToString,"/>
        </plugin>
        <!--使用FastJson重写ToString 方法的插件-->
        <!--use fastjson override toString()-->
        <plugin type="cn.crabapples.FastJsonToStringPlugin"/>
    </context>
  ```
