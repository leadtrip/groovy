package mikew.meta.compiletime

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import java.lang.reflect.Modifier

/**
 * Here we're using the DSL to build the method to add to the annotated class
 */
@GroovyASTTransformation
class ChemicalSpillageTransformation implements ASTTransformation {

    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        AnnotationNode annoNode = astNodes[0]
        ClassNode classNode = astNodes[1]

        def nodes = new AstBuilder().buildFromSpec {
            method('getCasualtiesExpected', Modifier.PUBLIC, boolean ) {
                parameters {}
                exceptions {}
                block {
                    returnStatement {
                        constant isDeadly( annoNode )
                    }
                }
            }
        }
        classNode.addMethod( nodes[0] )
    }

    static boolean isDeadly(AnnotationNode annoNode ) {
        def isDeadly = annoNode.getMember( 'isDeadly' )
        isDeadly instanceof ConstantExpression && ((ConstantExpression)isDeadly).getValue()
    }
}
