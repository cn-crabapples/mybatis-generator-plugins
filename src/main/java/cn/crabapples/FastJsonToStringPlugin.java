package cn.crabapples;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.He
 * 2022/11/26 3:54
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public class FastJsonToStringPlugin extends PluginAdapter {
    private TopLevelClass topLevelClass;

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.topLevelClass = topLevelClass;
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> list = new ArrayList<>();
        list.add(generatedJavaFile(introspectedTable));
        return list;
    }


    private GeneratedJavaFile generatedJavaFile(IntrospectedTable introspectedTable) {
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        String targetProject = configuration.getTargetProject();
        JavaFormatter javaFormatter = context.getJavaFormatter();
        GeneratedJavaFile javaFile = new GeneratedJavaFile(topLevelClass, targetProject, javaFormatter);
        Method method = createToStringMethod();
        topLevelClass.addMethod(method);
        return javaFile;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    private Method createToStringMethod() {
        topLevelClass.addImportedType(
                new FullyQualifiedJavaType("com.alibaba.fastjson.JSONObject"));
        Method method = new Method("toString");
        method.addBodyLine("return JSONObject.toJSONString(this);");
        method.setName("toString");
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.addAnnotation("@Override");
        method.setVisibility(JavaVisibility.PUBLIC);
        return method;
    }

    @Override
    public boolean clientBasicInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return super.clientBasicInsertMethodGenerated(method, interfaze, introspectedTable);
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }


}
