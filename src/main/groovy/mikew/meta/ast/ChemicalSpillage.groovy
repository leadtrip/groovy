package mikew.meta.ast

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@GroovyASTTransformationClass( 'mikew.meta.ast.ChemicalSpillageTransformation' )
@interface ChemicalSpillage {

    boolean isDeadly() default false;
}