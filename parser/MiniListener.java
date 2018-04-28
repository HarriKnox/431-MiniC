// Generated from parser/Mini.g4 by ANTLR 4.7.1

package parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniParser}.
 */
public interface MiniListener extends ParseTreeListener {
   /**
    * Enter a parse tree produced by {@link MiniParser#program}.
    * @param ctx the parse tree
    */
   void enterProgram(MiniParser.ProgramContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#program}.
    * @param ctx the parse tree
    */
   void exitProgram(MiniParser.ProgramContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#structs}.
    * @param ctx the parse tree
    */
   void enterStructs(MiniParser.StructsContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#structs}.
    * @param ctx the parse tree
    */
   void exitStructs(MiniParser.StructsContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#struct}.
    * @param ctx the parse tree
    */
   void enterStruct(MiniParser.StructContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#struct}.
    * @param ctx the parse tree
    */
   void exitStruct(MiniParser.StructContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#fields}.
    * @param ctx the parse tree
    */
   void enterFields(MiniParser.FieldsContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#fields}.
    * @param ctx the parse tree
    */
   void exitFields(MiniParser.FieldsContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#decl}.
    * @param ctx the parse tree
    */
   void enterDecl(MiniParser.DeclContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#decl}.
    * @param ctx the parse tree
    */
   void exitDecl(MiniParser.DeclContext ctx);
   /**
    * Enter a parse tree produced by the {@code IntType}
    * labeled alternative in {@link MiniParser#type}.
    * @param ctx the parse tree
    */
   void enterIntType(MiniParser.IntTypeContext ctx);
   /**
    * Exit a parse tree produced by the {@code IntType}
    * labeled alternative in {@link MiniParser#type}.
    * @param ctx the parse tree
    */
   void exitIntType(MiniParser.IntTypeContext ctx);
   /**
    * Enter a parse tree produced by the {@code BoolType}
    * labeled alternative in {@link MiniParser#type}.
    * @param ctx the parse tree
    */
   void enterBoolType(MiniParser.BoolTypeContext ctx);
   /**
    * Exit a parse tree produced by the {@code BoolType}
    * labeled alternative in {@link MiniParser#type}.
    * @param ctx the parse tree
    */
   void exitBoolType(MiniParser.BoolTypeContext ctx);
   /**
    * Enter a parse tree produced by the {@code StructType}
    * labeled alternative in {@link MiniParser#type}.
    * @param ctx the parse tree
    */
   void enterStructType(MiniParser.StructTypeContext ctx);
   /**
    * Exit a parse tree produced by the {@code StructType}
    * labeled alternative in {@link MiniParser#type}.
    * @param ctx the parse tree
    */
   void exitStructType(MiniParser.StructTypeContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#variables}.
    * @param ctx the parse tree
    */
   void enterVariables(MiniParser.VariablesContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#variables}.
    * @param ctx the parse tree
    */
   void exitVariables(MiniParser.VariablesContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#variable}.
    * @param ctx the parse tree
    */
   void enterVariable(MiniParser.VariableContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#variable}.
    * @param ctx the parse tree
    */
   void exitVariable(MiniParser.VariableContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#functions}.
    * @param ctx the parse tree
    */
   void enterFunctions(MiniParser.FunctionsContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#functions}.
    * @param ctx the parse tree
    */
   void exitFunctions(MiniParser.FunctionsContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#function}.
    * @param ctx the parse tree
    */
   void enterFunction(MiniParser.FunctionContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#function}.
    * @param ctx the parse tree
    */
   void exitFunction(MiniParser.FunctionContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#parameters}.
    * @param ctx the parse tree
    */
   void enterParameters(MiniParser.ParametersContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#parameters}.
    * @param ctx the parse tree
    */
   void exitParameters(MiniParser.ParametersContext ctx);
   /**
    * Enter a parse tree produced by the {@code ReturnTypeReal}
    * labeled alternative in {@link MiniParser#returnType}.
    * @param ctx the parse tree
    */
   void enterReturnTypeReal(MiniParser.ReturnTypeRealContext ctx);
   /**
    * Exit a parse tree produced by the {@code ReturnTypeReal}
    * labeled alternative in {@link MiniParser#returnType}.
    * @param ctx the parse tree
    */
   void exitReturnTypeReal(MiniParser.ReturnTypeRealContext ctx);
   /**
    * Enter a parse tree produced by the {@code ReturnTypeVoid}
    * labeled alternative in {@link MiniParser#returnType}.
    * @param ctx the parse tree
    */
   void enterReturnTypeVoid(MiniParser.ReturnTypeVoidContext ctx);
   /**
    * Exit a parse tree produced by the {@code ReturnTypeVoid}
    * labeled alternative in {@link MiniParser#returnType}.
    * @param ctx the parse tree
    */
   void exitReturnTypeVoid(MiniParser.ReturnTypeVoidContext ctx);
   /**
    * Enter a parse tree produced by the {@code NestedBlock}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterNestedBlock(MiniParser.NestedBlockContext ctx);
   /**
    * Exit a parse tree produced by the {@code NestedBlock}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitNestedBlock(MiniParser.NestedBlockContext ctx);
   /**
    * Enter a parse tree produced by the {@code Assignment}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterAssignment(MiniParser.AssignmentContext ctx);
   /**
    * Exit a parse tree produced by the {@code Assignment}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitAssignment(MiniParser.AssignmentContext ctx);
   /**
    * Enter a parse tree produced by the {@code Print}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterPrint(MiniParser.PrintContext ctx);
   /**
    * Exit a parse tree produced by the {@code Print}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitPrint(MiniParser.PrintContext ctx);
   /**
    * Enter a parse tree produced by the {@code PrintLn}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterPrintLn(MiniParser.PrintLnContext ctx);
   /**
    * Exit a parse tree produced by the {@code PrintLn}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitPrintLn(MiniParser.PrintLnContext ctx);
   /**
    * Enter a parse tree produced by the {@code Conditional}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterConditional(MiniParser.ConditionalContext ctx);
   /**
    * Exit a parse tree produced by the {@code Conditional}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitConditional(MiniParser.ConditionalContext ctx);
   /**
    * Enter a parse tree produced by the {@code While}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterWhile(MiniParser.WhileContext ctx);
   /**
    * Exit a parse tree produced by the {@code While}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitWhile(MiniParser.WhileContext ctx);
   /**
    * Enter a parse tree produced by the {@code Delete}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterDelete(MiniParser.DeleteContext ctx);
   /**
    * Exit a parse tree produced by the {@code Delete}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitDelete(MiniParser.DeleteContext ctx);
   /**
    * Enter a parse tree produced by the {@code Return}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterReturn(MiniParser.ReturnContext ctx);
   /**
    * Exit a parse tree produced by the {@code Return}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitReturn(MiniParser.ReturnContext ctx);
   /**
    * Enter a parse tree produced by the {@code Invocation}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void enterInvocation(MiniParser.InvocationContext ctx);
   /**
    * Exit a parse tree produced by the {@code Invocation}
    * labeled alternative in {@link MiniParser#statement}.
    * @param ctx the parse tree
    */
   void exitInvocation(MiniParser.InvocationContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#block}.
    * @param ctx the parse tree
    */
   void enterBlock(MiniParser.BlockContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#block}.
    * @param ctx the parse tree
    */
   void exitBlock(MiniParser.BlockContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#statementList}.
    * @param ctx the parse tree
    */
   void enterStatementList(MiniParser.StatementListContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#statementList}.
    * @param ctx the parse tree
    */
   void exitStatementList(MiniParser.StatementListContext ctx);
   /**
    * Enter a parse tree produced by the {@code LvalueId}
    * labeled alternative in {@link MiniParser#lvalue}.
    * @param ctx the parse tree
    */
   void enterLvalueId(MiniParser.LvalueIdContext ctx);
   /**
    * Exit a parse tree produced by the {@code LvalueId}
    * labeled alternative in {@link MiniParser#lvalue}.
    * @param ctx the parse tree
    */
   void exitLvalueId(MiniParser.LvalueIdContext ctx);
   /**
    * Enter a parse tree produced by the {@code LvalueDot}
    * labeled alternative in {@link MiniParser#lvalue}.
    * @param ctx the parse tree
    */
   void enterLvalueDot(MiniParser.LvalueDotContext ctx);
   /**
    * Exit a parse tree produced by the {@code LvalueDot}
    * labeled alternative in {@link MiniParser#lvalue}.
    * @param ctx the parse tree
    */
   void exitLvalueDot(MiniParser.LvalueDotContext ctx);
   /**
    * Enter a parse tree produced by the {@code BoolExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterBoolExpr(MiniParser.BoolExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code BoolExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitBoolExpr(MiniParser.BoolExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code ReadExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterReadExpr(MiniParser.ReadExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code ReadExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitReadExpr(MiniParser.ReadExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code IdentifierExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterIdentifierExpr(MiniParser.IdentifierExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code IdentifierExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitIdentifierExpr(MiniParser.IdentifierExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code BinaryExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterBinaryExpr(MiniParser.BinaryExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code BinaryExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitBinaryExpr(MiniParser.BinaryExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code NewExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterNewExpr(MiniParser.NewExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code NewExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitNewExpr(MiniParser.NewExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code NestedExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterNestedExpr(MiniParser.NestedExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code NestedExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitNestedExpr(MiniParser.NestedExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code IntExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterIntExpr(MiniParser.IntExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code IntExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitIntExpr(MiniParser.IntExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code DotExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterDotExpr(MiniParser.DotExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code DotExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitDotExpr(MiniParser.DotExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code UnaryExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterUnaryExpr(MiniParser.UnaryExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code UnaryExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitUnaryExpr(MiniParser.UnaryExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code InvocationExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterInvocationExpr(MiniParser.InvocationExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code InvocationExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitInvocationExpr(MiniParser.InvocationExprContext ctx);
   /**
    * Enter a parse tree produced by the {@code NullExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void enterNullExpr(MiniParser.NullExprContext ctx);
   /**
    * Exit a parse tree produced by the {@code NullExpr}
    * labeled alternative in {@link MiniParser#expression}.
    * @param ctx the parse tree
    */
   void exitNullExpr(MiniParser.NullExprContext ctx);
   /**
    * Enter a parse tree produced by {@link MiniParser#arguments}.
    * @param ctx the parse tree
    */
   void enterArguments(MiniParser.ArgumentsContext ctx);
   /**
    * Exit a parse tree produced by {@link MiniParser#arguments}.
    * @param ctx the parse tree
    */
   void exitArguments(MiniParser.ArgumentsContext ctx);
}