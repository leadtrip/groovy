package mikew.meta.compiletime

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@GroovyASTTransformationClass( 'mikew.meta.ast.compiletime.ChemicalSpillageTransformation' )
@interface ChemicalSpillage {

    boolean isDeadly() default false;
}