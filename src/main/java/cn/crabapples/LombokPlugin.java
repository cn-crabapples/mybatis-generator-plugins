package cn.crabapples;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mr.He
 * 2022/11/26 3:54
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public class LombokPlugin extends PluginAdapter {
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
        addLombokAnnotation();
        return new GeneratedJavaFile(topLevelClass, targetProject, javaFormatter);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    private void addLombokAnnotation() {
        String values = properties.getProperty("annotation");
        if (Objects.isNull(values)) {
            return;
        }
        String[] annotations = values.split(",");
        for (String annotation : annotations) {
            String trim = annotation.trim();
            if (trim.length() > 0) {
                String packageName = MessageFormat.format("lombok.{0}", trim);
                try {
                    Class<?> aClass = Class.forName(packageName);
                } catch (ClassNotFoundException e) {
                    String exceptionMessage = MessageFormat.format(
                            "当前lombok版本中没有发现:[{0}]注解\n" +
                                    "this lombok version not found [{0}] annotation", trim);
                    throw new RuntimeException(exceptionMessage);
                }
                String annotationName = MessageFormat.format("@{0}", trim);
                topLevelClass.addImportedType(new FullyQualifiedJavaType(packageName));
                topLevelClass.addAnnotation(annotationName);
            }
        }
    }

    @Override
    public boolean validate(List<String> list) {
        System.err.println(list);
        return true;
    }
}
