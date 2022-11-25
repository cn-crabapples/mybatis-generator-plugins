package cn.crabapples;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;

/**
 * <b><code>SelectCountPlugin</code></b>
 * <p/>
 * Description: 查询记录总条数
 * <p/>
 * <b>Creation Time:</b> 2020/06/08.
 */
public class TestPlugin extends PluginAdapter {
    private TopLevelClass topLevelClass;

    class CustomCompilationUnit implements CompilationUnit {

        @Override
        public Set<FullyQualifiedJavaType> getImportedTypes() {
            return null;
        }

        @Override
        public Set<String> getStaticImports() {
            return null;
        }

        @Override
        public FullyQualifiedJavaType getType() {
            return null;
        }

        @Override
        public void addImportedType(FullyQualifiedJavaType fullyQualifiedJavaType) {

        }

        @Override
        public void addImportedTypes(Set<FullyQualifiedJavaType> set) {

        }

        @Override
        public void addStaticImport(String s) {

        }

        @Override
        public void addStaticImports(Set<String> set) {

        }

        @Override
        public void addFileCommentLine(String s) {

        }

        @Override
        public List<String> getFileCommentLines() {
            return null;
        }

        @Override
        public <R> R accept(CompilationUnitVisitor<R> compilationUnitVisitor) {
            return null;
        }
    }

    private final static String SELECT_COUNT = "selectCount";

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        this.topLevelClass = topLevelClass;
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> list = new ArrayList<>();
        list.add(generatedJavaFile(introspectedTable));

//        return super.contextGenerateAdditionalJavaFiles(introspectedTable);
        return list;
    }


    private GeneratedJavaFile generatedJavaFile(IntrospectedTable introspectedTable) {
        createGetterSetterOfLombok();
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

    private void createGetterSetterOfLombok() {
        topLevelClass.addImportedType(
                new FullyQualifiedJavaType("lombok.Getter"));
        topLevelClass.addImportedType(
                new FullyQualifiedJavaType("lombok.Setter"));
        topLevelClass.addAnnotation("@Getter");
        topLevelClass.addAnnotation("@Setter");
    }


    private Method createToStringMethod() {
        Method method = new Method("toString");
        method.addBodyLine("//hello word");
        method.addBodyLine("return \"xxx\";");
        method.setName("toString");
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.addAnnotation("@Override");
        method.addJavaDocLine("/** hello function */");
        method.setVisibility(JavaVisibility.PUBLIC);
        return method;
    }

    @Override
    public boolean clientBasicInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        System.err.println(1);
        return super.clientBasicInsertMethodGenerated(method, interfaze, introspectedTable);
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }


}
