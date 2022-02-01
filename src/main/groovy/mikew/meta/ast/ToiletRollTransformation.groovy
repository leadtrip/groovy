package mikew.meta.ast

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.stmt.ReturnStatement
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import java.lang.reflect.Modifier

/**
 * The Abstract Syntax Tree (AST) is a model built up in memory that describes the source code
 * Groovy offers a mechanism, AST transformation, that provides ability to modify the model
 *
 * This all happens at compile time, whatever class is annotated with @ToiletRoll will have a getStatus method baked
 * into it so can be called by Java as well as Groovy unlike the runtime methods of adding functionality
 */
@GroovyASTTransformation
class ToiletRollTransformation implements ASTTransformation{

    /**
     * All source objects have an ASTNode structure, annotations, methods, classes...
     *
     * @param astNodes
     * @param sourceUnit
     */
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        AnnotationNode annotationNode = astNodes[0]     // first element is AnnotationNode, not doing anything with that here

        ClassNode classNode = astNodes[1]   // second element in ClassNode

        def code = new ReturnStatement( new ConstantExpression( 'spinning' ) )  // simply return string 'spinning'
        def methodNode = new MethodNode( 'getStatus',   // method name
                                         Modifier.PUBLIC,
                                         ClassHelper.make(String),
                                         new Parameter[0],
                                         null,
                                         code )

        classNode.addMethod( methodNode )   // add method to class
    }
}


