// Generated from parser/Mini.g4 by ANTLR 4.7.1

package parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniParser extends Parser {
   static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

   protected static final DFA[] _decisionToDFA;
   protected static final PredictionContextCache _sharedContextCache =
      new PredictionContextCache();
   public static final int
      T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
      T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
      T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
      T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
      T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
      ID=39, INTEGER=40, WS=41, COMMENT=42;
   public static final int
      RULE_program = 0, RULE_structs = 1, RULE_struct = 2, RULE_nestedDecl = 3, 
      RULE_decl = 4, RULE_type = 5, RULE_variables = 6, RULE_variable = 7, RULE_functions = 8, 
      RULE_function = 9, RULE_parameters = 10, RULE_returnType = 11, RULE_statement = 12, 
      RULE_block = 13, RULE_statementList = 14, RULE_lvalue = 15, RULE_expression = 16, 
      RULE_arguments = 17;
   public static final String[] ruleNames = {
      "program", "structs", "struct", "nestedDecl", "decl", "type", "variables", 
      "variable", "functions", "function", "parameters", "returnType", "statement", 
      "block", "statementList", "lvalue", "expression", "arguments"
   };

   private static final String[] _LITERAL_NAMES = {
      null, "'struct'", "'{'", "'}'", "';'", "'int'", "'bool'", "','", "'fun'", 
      "'('", "')'", "'void'", "'='", "'print'", "'endl'", "'if'", "'else'", 
      "'while'", "'delete'", "'return'", "'.'", "'-'", "'!'", "'*'", "'/'", 
      "'+'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'&&'", "'||'", "'true'", 
      "'false'", "'new'", "'null'", "'read'"
   };
   private static final String[] _SYMBOLIC_NAMES = {
      null, null, null, null, null, null, null, null, null, null, null, null, 
      null, null, null, null, null, null, null, null, null, null, null, null, 
      null, null, null, null, null, null, null, null, null, null, null, null, 
      null, null, null, "ID", "INTEGER", "WS", "COMMENT"
   };
   public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

   /**
    * @deprecated Use {@link #VOCABULARY} instead.
    */
   @Deprecated
   public static final String[] tokenNames;
   static {
      tokenNames = new String[_SYMBOLIC_NAMES.length];
      for (int i = 0; i < tokenNames.length; i++) {
         tokenNames[i] = VOCABULARY.getLiteralName(i);
         if (tokenNames[i] == null) {
            tokenNames[i] = VOCABULARY.getSymbolicName(i);
         }

         if (tokenNames[i] == null) {
            tokenNames[i] = "<INVALID>";
         }
      }
   }

   @Override
   @Deprecated
   public String[] getTokenNames() {
      return tokenNames;
   }

   @Override

   public Vocabulary getVocabulary() {
      return VOCABULARY;
   }

   @Override
   public String getGrammarFileName() { return "Mini.g4"; }

   @Override
   public String[] getRuleNames() { return ruleNames; }

   @Override
   public String getSerializedATN() { return _serializedATN; }

   @Override
   public ATN getATN() { return _ATN; }

   public MiniParser(TokenStream input) {
      super(input);
      _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
   }
   public static class ProgramContext extends ParserRuleContext {
      public StructsContext structs() {
         return getRuleContext(StructsContext.class,0);
      }
      public VariablesContext variables() {
         return getRuleContext(VariablesContext.class,0);
      }
      public FunctionsContext functions() {
         return getRuleContext(FunctionsContext.class,0);
      }
      public TerminalNode EOF() { return getToken(MiniParser.EOF, 0); }
      public ProgramContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_program; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterProgram(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitProgram(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitProgram(this);
         else return visitor.visitChildren(this);
      }
   }

   public final ProgramContext program() throws RecognitionException {
      ProgramContext _localctx = new ProgramContext(_ctx, getState());
      enterRule(_localctx, 0, RULE_program);
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(36);
         structs();
         setState(37);
         variables();
         setState(38);
         functions();
         setState(39);
         match(EOF);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class StructsContext extends ParserRuleContext {
      public List<StructContext> struct() {
         return getRuleContexts(StructContext.class);
      }
      public StructContext struct(int i) {
         return getRuleContext(StructContext.class,i);
      }
      public StructsContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_structs; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterStructs(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitStructs(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitStructs(this);
         else return visitor.visitChildren(this);
      }
   }

   public final StructsContext structs() throws RecognitionException {
      StructsContext _localctx = new StructsContext(_ctx, getState());
      enterRule(_localctx, 2, RULE_structs);
      try {
         int _alt;
         enterOuterAlt(_localctx, 1);
         {
         setState(44);
         _errHandler.sync(this);
         _alt = getInterpreter().adaptivePredict(_input,0,_ctx);
         while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
            if ( _alt==1 ) {
               {
               {
               setState(41);
               struct();
               }
               } 
            }
            setState(46);
            _errHandler.sync(this);
            _alt = getInterpreter().adaptivePredict(_input,0,_ctx);
         }
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class StructContext extends ParserRuleContext {
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public NestedDeclContext nestedDecl() {
         return getRuleContext(NestedDeclContext.class,0);
      }
      public StructContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_struct; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterStruct(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitStruct(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitStruct(this);
         else return visitor.visitChildren(this);
      }
   }

   public final StructContext struct() throws RecognitionException {
      StructContext _localctx = new StructContext(_ctx, getState());
      enterRule(_localctx, 4, RULE_struct);
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(47);
         match(T__0);
         setState(48);
         match(ID);
         setState(49);
         match(T__1);
         setState(50);
         nestedDecl();
         setState(51);
         match(T__2);
         setState(52);
         match(T__3);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class NestedDeclContext extends ParserRuleContext {
      public List<DeclContext> decl() {
         return getRuleContexts(DeclContext.class);
      }
      public DeclContext decl(int i) {
         return getRuleContext(DeclContext.class,i);
      }
      public NestedDeclContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_nestedDecl; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterNestedDecl(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitNestedDecl(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitNestedDecl(this);
         else return visitor.visitChildren(this);
      }
   }

   public final NestedDeclContext nestedDecl() throws RecognitionException {
      NestedDeclContext _localctx = new NestedDeclContext(_ctx, getState());
      enterRule(_localctx, 6, RULE_nestedDecl);
      int _la;
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(57); 
         _errHandler.sync(this);
         _la = _input.LA(1);
         do {
            {
            {
            setState(54);
            decl();
            setState(55);
            match(T__3);
            }
            }
            setState(59); 
            _errHandler.sync(this);
            _la = _input.LA(1);
         } while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__4) | (1L << T__5))) != 0) );
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class DeclContext extends ParserRuleContext {
      public TypeContext type() {
         return getRuleContext(TypeContext.class,0);
      }
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public DeclContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_decl; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterDecl(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitDecl(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitDecl(this);
         else return visitor.visitChildren(this);
      }
   }

   public final DeclContext decl() throws RecognitionException {
      DeclContext _localctx = new DeclContext(_ctx, getState());
      enterRule(_localctx, 8, RULE_decl);
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(61);
         type();
         setState(62);
         match(ID);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class TypeContext extends ParserRuleContext {
      public TypeContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_type; }
    
      public TypeContext() { }
      public void copyFrom(TypeContext ctx) {
         super.copyFrom(ctx);
      }
   }
   public static class BoolTypeContext extends TypeContext {
      public BoolTypeContext(TypeContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterBoolType(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitBoolType(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitBoolType(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class StructTypeContext extends TypeContext {
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public StructTypeContext(TypeContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterStructType(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitStructType(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitStructType(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class IntTypeContext extends TypeContext {
      public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterIntType(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitIntType(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitIntType(this);
         else return visitor.visitChildren(this);
      }
   }

   public final TypeContext type() throws RecognitionException {
      TypeContext _localctx = new TypeContext(_ctx, getState());
      enterRule(_localctx, 10, RULE_type);
      try {
         setState(68);
         _errHandler.sync(this);
         switch (_input.LA(1)) {
         case T__4:
            _localctx = new IntTypeContext(_localctx);
            enterOuterAlt(_localctx, 1);
            {
            setState(64);
            match(T__4);
            }
            break;
         case T__5:
            _localctx = new BoolTypeContext(_localctx);
            enterOuterAlt(_localctx, 2);
            {
            setState(65);
            match(T__5);
            }
            break;
         case T__0:
            _localctx = new StructTypeContext(_localctx);
            enterOuterAlt(_localctx, 3);
            {
            setState(66);
            match(T__0);
            setState(67);
            match(ID);
            }
            break;
         default:
            throw new NoViableAltException(this);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class VariablesContext extends ParserRuleContext {
      public List<VariableContext> variable() {
         return getRuleContexts(VariableContext.class);
      }
      public VariableContext variable(int i) {
         return getRuleContext(VariableContext.class,i);
      }
      public VariablesContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_variables; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterVariables(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitVariables(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitVariables(this);
         else return visitor.visitChildren(this);
      }
   }

   public final VariablesContext variables() throws RecognitionException {
      VariablesContext _localctx = new VariablesContext(_ctx, getState());
      enterRule(_localctx, 12, RULE_variables);
      int _la;
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(73);
         _errHandler.sync(this);
         _la = _input.LA(1);
         while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__4) | (1L << T__5))) != 0)) {
            {
            {
            setState(70);
            variable();
            }
            }
            setState(75);
            _errHandler.sync(this);
            _la = _input.LA(1);
         }
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class VariableContext extends ParserRuleContext {
      public TypeContext type() {
         return getRuleContext(TypeContext.class,0);
      }
      public List<TerminalNode> ID() { return getTokens(MiniParser.ID); }
      public TerminalNode ID(int i) {
         return getToken(MiniParser.ID, i);
      }
      public VariableContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_variable; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterVariable(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitVariable(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitVariable(this);
         else return visitor.visitChildren(this);
      }
   }

   public final VariableContext variable() throws RecognitionException {
      VariableContext _localctx = new VariableContext(_ctx, getState());
      enterRule(_localctx, 14, RULE_variable);
      int _la;
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(76);
         type();
         setState(77);
         match(ID);
         setState(82);
         _errHandler.sync(this);
         _la = _input.LA(1);
         while (_la==T__6) {
            {
            {
            setState(78);
            match(T__6);
            setState(79);
            match(ID);
            }
            }
            setState(84);
            _errHandler.sync(this);
            _la = _input.LA(1);
         }
         setState(85);
         match(T__3);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class FunctionsContext extends ParserRuleContext {
      public List<FunctionContext> function() {
         return getRuleContexts(FunctionContext.class);
      }
      public FunctionContext function(int i) {
         return getRuleContext(FunctionContext.class,i);
      }
      public FunctionsContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_functions; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterFunctions(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitFunctions(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitFunctions(this);
         else return visitor.visitChildren(this);
      }
   }

   public final FunctionsContext functions() throws RecognitionException {
      FunctionsContext _localctx = new FunctionsContext(_ctx, getState());
      enterRule(_localctx, 16, RULE_functions);
      int _la;
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(90);
         _errHandler.sync(this);
         _la = _input.LA(1);
         while (_la==T__7) {
            {
            {
            setState(87);
            function();
            }
            }
            setState(92);
            _errHandler.sync(this);
            _la = _input.LA(1);
         }
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class FunctionContext extends ParserRuleContext {
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public ParametersContext parameters() {
         return getRuleContext(ParametersContext.class,0);
      }
      public ReturnTypeContext returnType() {
         return getRuleContext(ReturnTypeContext.class,0);
      }
      public VariablesContext variables() {
         return getRuleContext(VariablesContext.class,0);
      }
      public StatementListContext statementList() {
         return getRuleContext(StatementListContext.class,0);
      }
      public FunctionContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_function; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterFunction(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitFunction(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitFunction(this);
         else return visitor.visitChildren(this);
      }
   }

   public final FunctionContext function() throws RecognitionException {
      FunctionContext _localctx = new FunctionContext(_ctx, getState());
      enterRule(_localctx, 18, RULE_function);
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(93);
         match(T__7);
         setState(94);
         match(ID);
         setState(95);
         parameters();
         setState(96);
         returnType();
         setState(97);
         match(T__1);
         setState(98);
         variables();
         setState(99);
         statementList();
         setState(100);
         match(T__2);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class ParametersContext extends ParserRuleContext {
      public List<DeclContext> decl() {
         return getRuleContexts(DeclContext.class);
      }
      public DeclContext decl(int i) {
         return getRuleContext(DeclContext.class,i);
      }
      public ParametersContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_parameters; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterParameters(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitParameters(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitParameters(this);
         else return visitor.visitChildren(this);
      }
   }

   public final ParametersContext parameters() throws RecognitionException {
      ParametersContext _localctx = new ParametersContext(_ctx, getState());
      enterRule(_localctx, 20, RULE_parameters);
      int _la;
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(102);
         match(T__8);
         setState(111);
         _errHandler.sync(this);
         _la = _input.LA(1);
         if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__4) | (1L << T__5))) != 0)) {
            {
            setState(103);
            decl();
            setState(108);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la==T__6) {
               {
               {
               setState(104);
               match(T__6);
               setState(105);
               decl();
               }
               }
               setState(110);
               _errHandler.sync(this);
               _la = _input.LA(1);
            }
            }
         }

         setState(113);
         match(T__9);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class ReturnTypeContext extends ParserRuleContext {
      public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_returnType; }
    
      public ReturnTypeContext() { }
      public void copyFrom(ReturnTypeContext ctx) {
         super.copyFrom(ctx);
      }
   }
   public static class ReturnTypeVoidContext extends ReturnTypeContext {
      public ReturnTypeVoidContext(ReturnTypeContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterReturnTypeVoid(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitReturnTypeVoid(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitReturnTypeVoid(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class ReturnTypeRealContext extends ReturnTypeContext {
      public TypeContext type() {
         return getRuleContext(TypeContext.class,0);
      }
      public ReturnTypeRealContext(ReturnTypeContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterReturnTypeReal(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitReturnTypeReal(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitReturnTypeReal(this);
         else return visitor.visitChildren(this);
      }
   }

   public final ReturnTypeContext returnType() throws RecognitionException {
      ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
      enterRule(_localctx, 22, RULE_returnType);
      try {
         setState(117);
         _errHandler.sync(this);
         switch (_input.LA(1)) {
         case T__0:
         case T__4:
         case T__5:
            _localctx = new ReturnTypeRealContext(_localctx);
            enterOuterAlt(_localctx, 1);
            {
            setState(115);
            type();
            }
            break;
         case T__10:
            _localctx = new ReturnTypeVoidContext(_localctx);
            enterOuterAlt(_localctx, 2);
            {
            setState(116);
            match(T__10);
            }
            break;
         default:
            throw new NoViableAltException(this);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class StatementContext extends ParserRuleContext {
      public StatementContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_statement; }
    
      public StatementContext() { }
      public void copyFrom(StatementContext ctx) {
         super.copyFrom(ctx);
      }
   }
   public static class AssignmentContext extends StatementContext {
      public LvalueContext lvalue() {
         return getRuleContext(LvalueContext.class,0);
      }
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public AssignmentContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterAssignment(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitAssignment(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitAssignment(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class DeleteContext extends StatementContext {
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public DeleteContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterDelete(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitDelete(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitDelete(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class PrintContext extends StatementContext {
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public PrintContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterPrint(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitPrint(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitPrint(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class ReturnContext extends StatementContext {
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public ReturnContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterReturn(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitReturn(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitReturn(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class InvocationContext extends StatementContext {
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public ArgumentsContext arguments() {
         return getRuleContext(ArgumentsContext.class,0);
      }
      public InvocationContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterInvocation(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitInvocation(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitInvocation(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class PrintLnContext extends StatementContext {
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public PrintLnContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterPrintLn(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitPrintLn(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitPrintLn(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class ConditionalContext extends StatementContext {
      public StatementContext thenBlock;
      public StatementContext elseBlock;
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public List<StatementContext> statement() {
         return getRuleContexts(StatementContext.class);
      }
      public StatementContext statement(int i) {
         return getRuleContext(StatementContext.class,i);
      }
      public ConditionalContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterConditional(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitConditional(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitConditional(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class NestedBlockContext extends StatementContext {
      public BlockContext block() {
         return getRuleContext(BlockContext.class,0);
      }
      public NestedBlockContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterNestedBlock(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitNestedBlock(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitNestedBlock(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class WhileContext extends StatementContext {
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public StatementContext statement() {
         return getRuleContext(StatementContext.class,0);
      }
      public WhileContext(StatementContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterWhile(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitWhile(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitWhile(this);
         else return visitor.visitChildren(this);
      }
   }

   public final StatementContext statement() throws RecognitionException {
      StatementContext _localctx = new StatementContext(_ctx, getState());
      enterRule(_localctx, 24, RULE_statement);
      int _la;
      try {
         setState(164);
         _errHandler.sync(this);
         switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
         case 1:
            _localctx = new NestedBlockContext(_localctx);
            enterOuterAlt(_localctx, 1);
            {
            setState(119);
            block();
            }
            break;
         case 2:
            _localctx = new AssignmentContext(_localctx);
            enterOuterAlt(_localctx, 2);
            {
            setState(120);
            lvalue(0);
            setState(121);
            match(T__11);
            setState(122);
            expression(0);
            setState(123);
            match(T__3);
            }
            break;
         case 3:
            _localctx = new PrintContext(_localctx);
            enterOuterAlt(_localctx, 3);
            {
            setState(125);
            match(T__12);
            setState(126);
            expression(0);
            setState(127);
            match(T__3);
            }
            break;
         case 4:
            _localctx = new PrintLnContext(_localctx);
            enterOuterAlt(_localctx, 4);
            {
            setState(129);
            match(T__12);
            setState(130);
            expression(0);
            setState(131);
            match(T__13);
            setState(132);
            match(T__3);
            }
            break;
         case 5:
            _localctx = new ConditionalContext(_localctx);
            enterOuterAlt(_localctx, 5);
            {
            setState(134);
            match(T__14);
            setState(135);
            match(T__8);
            setState(136);
            expression(0);
            setState(137);
            match(T__9);
            setState(138);
            ((ConditionalContext)_localctx).thenBlock = statement();
            setState(141);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
            case 1:
               {
               setState(139);
               match(T__15);
               setState(140);
               ((ConditionalContext)_localctx).elseBlock = statement();
               }
               break;
            }
            }
            break;
         case 6:
            _localctx = new WhileContext(_localctx);
            enterOuterAlt(_localctx, 6);
            {
            setState(143);
            match(T__16);
            setState(144);
            match(T__8);
            setState(145);
            expression(0);
            setState(146);
            match(T__9);
            setState(147);
            statement();
            }
            break;
         case 7:
            _localctx = new DeleteContext(_localctx);
            enterOuterAlt(_localctx, 7);
            {
            setState(149);
            match(T__17);
            setState(150);
            expression(0);
            setState(151);
            match(T__3);
            }
            break;
         case 8:
            _localctx = new ReturnContext(_localctx);
            enterOuterAlt(_localctx, 8);
            {
            setState(153);
            match(T__18);
            setState(155);
            _errHandler.sync(this);
            _la = _input.LA(1);
            if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__20) | (1L << T__21) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << ID) | (1L << INTEGER))) != 0)) {
               {
               setState(154);
               expression(0);
               }
            }

            setState(157);
            match(T__3);
            }
            break;
         case 9:
            _localctx = new InvocationContext(_localctx);
            enterOuterAlt(_localctx, 9);
            {
            setState(158);
            match(ID);
            setState(159);
            match(T__8);
            setState(160);
            arguments();
            setState(161);
            match(T__9);
            setState(162);
            match(T__3);
            }
            break;
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class BlockContext extends ParserRuleContext {
      public StatementListContext statementList() {
         return getRuleContext(StatementListContext.class,0);
      }
      public BlockContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_block; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterBlock(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitBlock(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitBlock(this);
         else return visitor.visitChildren(this);
      }
   }

   public final BlockContext block() throws RecognitionException {
      BlockContext _localctx = new BlockContext(_ctx, getState());
      enterRule(_localctx, 26, RULE_block);
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(166);
         match(T__1);
         setState(167);
         statementList();
         setState(168);
         match(T__2);
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class StatementListContext extends ParserRuleContext {
      public List<StatementContext> statement() {
         return getRuleContexts(StatementContext.class);
      }
      public StatementContext statement(int i) {
         return getRuleContext(StatementContext.class,i);
      }
      public StatementListContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_statementList; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterStatementList(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitStatementList(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitStatementList(this);
         else return visitor.visitChildren(this);
      }
   }

   public final StatementListContext statementList() throws RecognitionException {
      StatementListContext _localctx = new StatementListContext(_ctx, getState());
      enterRule(_localctx, 28, RULE_statementList);
      int _la;
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(173);
         _errHandler.sync(this);
         _la = _input.LA(1);
         while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__12) | (1L << T__14) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << ID))) != 0)) {
            {
            {
            setState(170);
            statement();
            }
            }
            setState(175);
            _errHandler.sync(this);
            _la = _input.LA(1);
         }
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public static class LvalueContext extends ParserRuleContext {
      public LvalueContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_lvalue; }
    
      public LvalueContext() { }
      public void copyFrom(LvalueContext ctx) {
         super.copyFrom(ctx);
      }
   }
   public static class LvalueIdContext extends LvalueContext {
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public LvalueIdContext(LvalueContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterLvalueId(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitLvalueId(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitLvalueId(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class LvalueDotContext extends LvalueContext {
      public LvalueContext lvalue() {
         return getRuleContext(LvalueContext.class,0);
      }
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public LvalueDotContext(LvalueContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterLvalueDot(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitLvalueDot(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitLvalueDot(this);
         else return visitor.visitChildren(this);
      }
   }

   public final LvalueContext lvalue() throws RecognitionException {
      return lvalue(0);
   }

   private LvalueContext lvalue(int _p) throws RecognitionException {
      ParserRuleContext _parentctx = _ctx;
      int _parentState = getState();
      LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
      LvalueContext _prevctx = _localctx;
      int _startState = 30;
      enterRecursionRule(_localctx, 30, RULE_lvalue, _p);
      try {
         int _alt;
         enterOuterAlt(_localctx, 1);
         {
         {
         _localctx = new LvalueIdContext(_localctx);
         _ctx = _localctx;
         _prevctx = _localctx;

         setState(177);
         match(ID);
         }
         _ctx.stop = _input.LT(-1);
         setState(184);
         _errHandler.sync(this);
         _alt = getInterpreter().adaptivePredict(_input,13,_ctx);
         while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
            if ( _alt==1 ) {
               if ( _parseListeners!=null ) triggerExitRuleEvent();
               _prevctx = _localctx;
               {
               {
               _localctx = new LvalueDotContext(new LvalueContext(_parentctx, _parentState));
               pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
               setState(179);
               if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
               setState(180);
               match(T__19);
               setState(181);
               match(ID);
               }
               } 
            }
            setState(186);
            _errHandler.sync(this);
            _alt = getInterpreter().adaptivePredict(_input,13,_ctx);
         }
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         unrollRecursionContexts(_parentctx);
      }
      return _localctx;
   }

   public static class ExpressionContext extends ParserRuleContext {
      public ExpressionContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_expression; }
    
      public ExpressionContext() { }
      public void copyFrom(ExpressionContext ctx) {
         super.copyFrom(ctx);
      }
   }
   public static class BoolExprContext extends ExpressionContext {
      public BoolExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterBoolExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitBoolExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitBoolExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class ReadExprContext extends ExpressionContext {
      public ReadExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterReadExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitReadExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitReadExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class IdentifierExprContext extends ExpressionContext {
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public IdentifierExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterIdentifierExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitIdentifierExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitIdentifierExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class BinaryExprContext extends ExpressionContext {
      public ExpressionContext lft;
      public Token op;
      public ExpressionContext rht;
      public List<ExpressionContext> expression() {
         return getRuleContexts(ExpressionContext.class);
      }
      public ExpressionContext expression(int i) {
         return getRuleContext(ExpressionContext.class,i);
      }
      public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterBinaryExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitBinaryExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitBinaryExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class NewExprContext extends ExpressionContext {
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterNewExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitNewExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitNewExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class NestedExprContext extends ExpressionContext {
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public NestedExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterNestedExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitNestedExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitNestedExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class IntExprContext extends ExpressionContext {
      public TerminalNode INTEGER() { return getToken(MiniParser.INTEGER, 0); }
      public IntExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterIntExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitIntExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitIntExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class DotExprContext extends ExpressionContext {
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public DotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterDotExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitDotExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitDotExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class UnaryExprContext extends ExpressionContext {
      public Token op;
      public ExpressionContext expression() {
         return getRuleContext(ExpressionContext.class,0);
      }
      public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterUnaryExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitUnaryExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitUnaryExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class InvocationExprContext extends ExpressionContext {
      public TerminalNode ID() { return getToken(MiniParser.ID, 0); }
      public ArgumentsContext arguments() {
         return getRuleContext(ArgumentsContext.class,0);
      }
      public InvocationExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterInvocationExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitInvocationExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitInvocationExpr(this);
         else return visitor.visitChildren(this);
      }
   }
   public static class NullExprContext extends ExpressionContext {
      public NullExprContext(ExpressionContext ctx) { copyFrom(ctx); }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterNullExpr(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitNullExpr(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitNullExpr(this);
         else return visitor.visitChildren(this);
      }
   }

   public final ExpressionContext expression() throws RecognitionException {
      return expression(0);
   }

   private ExpressionContext expression(int _p) throws RecognitionException {
      ParserRuleContext _parentctx = _ctx;
      int _parentState = getState();
      ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
      ExpressionContext _prevctx = _localctx;
      int _startState = 32;
      enterRecursionRule(_localctx, 32, RULE_expression, _p);
      int _la;
      try {
         int _alt;
         enterOuterAlt(_localctx, 1);
         {
         setState(206);
         _errHandler.sync(this);
         switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
         case 1:
            {
            _localctx = new InvocationExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;

            setState(188);
            match(ID);
            setState(189);
            match(T__8);
            setState(190);
            arguments();
            setState(191);
            match(T__9);
            }
            break;
         case 2:
            {
            _localctx = new UnaryExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(193);
            ((UnaryExprContext)_localctx).op = _input.LT(1);
            _la = _input.LA(1);
            if ( !(_la==T__20 || _la==T__21) ) {
               ((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
            }
            else {
               if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
               _errHandler.reportMatch(this);
               consume();
            }
            setState(194);
            expression(14);
            }
            break;
         case 3:
            {
            _localctx = new IdentifierExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(195);
            match(ID);
            }
            break;
         case 4:
            {
            _localctx = new IntExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(196);
            match(INTEGER);
            }
            break;
         case 5:
            {
            _localctx = new BoolExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(197);
            _la = _input.LA(1);
            if ( !(_la==T__33 || _la==T__34) ) {
            _errHandler.recoverInline(this);
            }
            else {
               if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
               _errHandler.reportMatch(this);
               consume();
            }
            }
            break;
         case 6:
            {
            _localctx = new NewExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(198);
            match(T__35);
            setState(199);
            match(ID);
            }
            break;
         case 7:
            {
            _localctx = new NullExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(200);
            match(T__36);
            }
            break;
         case 8:
            {
            _localctx = new ReadExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(201);
            match(T__37);
            }
            break;
         case 9:
            {
            _localctx = new NestedExprContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(202);
            match(T__8);
            setState(203);
            expression(0);
            setState(204);
            match(T__9);
            }
            break;
         }
         _ctx.stop = _input.LT(-1);
         setState(231);
         _errHandler.sync(this);
         _alt = getInterpreter().adaptivePredict(_input,16,_ctx);
         while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
            if ( _alt==1 ) {
               if ( _parseListeners!=null ) triggerExitRuleEvent();
               _prevctx = _localctx;
               {
               setState(229);
               _errHandler.sync(this);
               switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
               case 1:
                  {
                  _localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
                  ((BinaryExprContext)_localctx).lft = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(208);
                  if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
                  setState(209);
                  ((BinaryExprContext)_localctx).op = _input.LT(1);
                  _la = _input.LA(1);
                  if ( !(_la==T__22 || _la==T__23) ) {
                     ((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
                  }
                  else {
                     if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                     _errHandler.reportMatch(this);
                     consume();
                  }
                  setState(210);
                  ((BinaryExprContext)_localctx).rht = expression(14);
                  }
                  break;
               case 2:
                  {
                  _localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
                  ((BinaryExprContext)_localctx).lft = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(211);
                  if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
                  setState(212);
                  ((BinaryExprContext)_localctx).op = _input.LT(1);
                  _la = _input.LA(1);
                  if ( !(_la==T__20 || _la==T__24) ) {
                     ((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
                  }
                  else {
                     if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                     _errHandler.reportMatch(this);
                     consume();
                  }
                  setState(213);
                  ((BinaryExprContext)_localctx).rht = expression(13);
                  }
                  break;
               case 3:
                  {
                  _localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
                  ((BinaryExprContext)_localctx).lft = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(214);
                  if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
                  setState(215);
                  ((BinaryExprContext)_localctx).op = _input.LT(1);
                  _la = _input.LA(1);
                  if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
                     ((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
                  }
                  else {
                     if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                     _errHandler.reportMatch(this);
                     consume();
                  }
                  setState(216);
                  ((BinaryExprContext)_localctx).rht = expression(12);
                  }
                  break;
               case 4:
                  {
                  _localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
                  ((BinaryExprContext)_localctx).lft = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(217);
                  if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
                  setState(218);
                  ((BinaryExprContext)_localctx).op = _input.LT(1);
                  _la = _input.LA(1);
                  if ( !(_la==T__29 || _la==T__30) ) {
                     ((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
                  }
                  else {
                     if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
                     _errHandler.reportMatch(this);
                     consume();
                  }
                  setState(219);
                  ((BinaryExprContext)_localctx).rht = expression(11);
                  }
                  break;
               case 5:
                  {
                  _localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
                  ((BinaryExprContext)_localctx).lft = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(220);
                  if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
                  setState(221);
                  ((BinaryExprContext)_localctx).op = match(T__31);
                  setState(222);
                  ((BinaryExprContext)_localctx).rht = expression(10);
                  }
                  break;
               case 6:
                  {
                  _localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
                  ((BinaryExprContext)_localctx).lft = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(223);
                  if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                  setState(224);
                  ((BinaryExprContext)_localctx).op = match(T__32);
                  setState(225);
                  ((BinaryExprContext)_localctx).rht = expression(9);
                  }
                  break;
               case 7:
                  {
                  _localctx = new DotExprContext(new ExpressionContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(226);
                  if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
                  setState(227);
                  match(T__19);
                  setState(228);
                  match(ID);
                  }
                  break;
               }
               } 
            }
            setState(233);
            _errHandler.sync(this);
            _alt = getInterpreter().adaptivePredict(_input,16,_ctx);
         }
         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         unrollRecursionContexts(_parentctx);
      }
      return _localctx;
   }

   public static class ArgumentsContext extends ParserRuleContext {
      public List<ExpressionContext> expression() {
         return getRuleContexts(ExpressionContext.class);
      }
      public ExpressionContext expression(int i) {
         return getRuleContext(ExpressionContext.class,i);
      }
      public ArgumentsContext(ParserRuleContext parent, int invokingState) {
         super(parent, invokingState);
      }
      @Override public int getRuleIndex() { return RULE_arguments; }
      @Override
      public void enterRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).enterArguments(this);
      }
      @Override
      public void exitRule(ParseTreeListener listener) {
         if ( listener instanceof MiniListener ) ((MiniListener)listener).exitArguments(this);
      }
      @Override
      public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
         if ( visitor instanceof MiniVisitor ) return ((MiniVisitor<? extends T>)visitor).visitArguments(this);
         else return visitor.visitChildren(this);
      }
   }

   public final ArgumentsContext arguments() throws RecognitionException {
      ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
      enterRule(_localctx, 34, RULE_arguments);
      int _la;
      try {
         enterOuterAlt(_localctx, 1);
         {
         setState(242);
         _errHandler.sync(this);
         _la = _input.LA(1);
         if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__20) | (1L << T__21) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << ID) | (1L << INTEGER))) != 0)) {
            {
            setState(234);
            expression(0);
            setState(239);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la==T__6) {
               {
               {
               setState(235);
               match(T__6);
               setState(236);
               expression(0);
               }
               }
               setState(241);
               _errHandler.sync(this);
               _la = _input.LA(1);
            }
            }
         }

         }
      }
      catch (RecognitionException re) {
         _localctx.exception = re;
         _errHandler.reportError(this, re);
         _errHandler.recover(this, re);
      }
      finally {
         exitRule();
      }
      return _localctx;
   }

   public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
      switch (ruleIndex) {
      case 15:
         return lvalue_sempred((LvalueContext)_localctx, predIndex);
      case 16:
         return expression_sempred((ExpressionContext)_localctx, predIndex);
      }
      return true;
   }
   private boolean lvalue_sempred(LvalueContext _localctx, int predIndex) {
      switch (predIndex) {
      case 0:
         return precpred(_ctx, 1);
      }
      return true;
   }
   private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
      switch (predIndex) {
      case 1:
         return precpred(_ctx, 13);
      case 2:
         return precpred(_ctx, 12);
      case 3:
         return precpred(_ctx, 11);
      case 4:
         return precpred(_ctx, 10);
      case 5:
         return precpred(_ctx, 9);
      case 6:
         return precpred(_ctx, 8);
      case 7:
         return precpred(_ctx, 15);
      }
      return true;
   }

   public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u00f7\4\2\t\2\4"+
      "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
      "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
      "\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\4\3\4\3"+
      "\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\6\5<\n\5\r\5\16\5=\3\6\3\6\3\6\3\7\3\7"+
      "\3\7\3\7\5\7G\n\7\3\b\7\bJ\n\b\f\b\16\bM\13\b\3\t\3\t\3\t\3\t\7\tS\n\t"+
      "\f\t\16\tV\13\t\3\t\3\t\3\n\7\n[\n\n\f\n\16\n^\13\n\3\13\3\13\3\13\3\13"+
      "\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7\fm\n\f\f\f\16\fp\13\f\5\f"+
      "r\n\f\3\f\3\f\3\r\3\r\5\rx\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
      "\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
      "\5\16\u0090\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
      "\3\16\5\16\u009e\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00a7\n"+
      "\16\3\17\3\17\3\17\3\17\3\20\7\20\u00ae\n\20\f\20\16\20\u00b1\13\20\3"+
      "\21\3\21\3\21\3\21\3\21\3\21\7\21\u00b9\n\21\f\21\16\21\u00bc\13\21\3"+
      "\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
      "\22\3\22\3\22\3\22\3\22\5\22\u00d1\n\22\3\22\3\22\3\22\3\22\3\22\3\22"+
      "\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
      "\3\22\7\22\u00e8\n\22\f\22\16\22\u00eb\13\22\3\23\3\23\3\23\7\23\u00f0"+
      "\n\23\f\23\16\23\u00f3\13\23\5\23\u00f5\n\23\3\23\2\4 \"\24\2\4\6\b\n"+
      "\f\16\20\22\24\26\30\32\34\36 \"$\2\b\3\2\27\30\3\2$%\3\2\31\32\4\2\27"+
      "\27\33\33\3\2\34\37\3\2 !\2\u010b\2&\3\2\2\2\4.\3\2\2\2\6\61\3\2\2\2\b"+
      ";\3\2\2\2\n?\3\2\2\2\fF\3\2\2\2\16K\3\2\2\2\20N\3\2\2\2\22\\\3\2\2\2\24"+
      "_\3\2\2\2\26h\3\2\2\2\30w\3\2\2\2\32\u00a6\3\2\2\2\34\u00a8\3\2\2\2\36"+
      "\u00af\3\2\2\2 \u00b2\3\2\2\2\"\u00d0\3\2\2\2$\u00f4\3\2\2\2&\'\5\4\3"+
      "\2\'(\5\16\b\2()\5\22\n\2)*\7\2\2\3*\3\3\2\2\2+-\5\6\4\2,+\3\2\2\2-\60"+
      "\3\2\2\2.,\3\2\2\2./\3\2\2\2/\5\3\2\2\2\60.\3\2\2\2\61\62\7\3\2\2\62\63"+
      "\7)\2\2\63\64\7\4\2\2\64\65\5\b\5\2\65\66\7\5\2\2\66\67\7\6\2\2\67\7\3"+
      "\2\2\289\5\n\6\29:\7\6\2\2:<\3\2\2\2;8\3\2\2\2<=\3\2\2\2=;\3\2\2\2=>\3"+
      "\2\2\2>\t\3\2\2\2?@\5\f\7\2@A\7)\2\2A\13\3\2\2\2BG\7\7\2\2CG\7\b\2\2D"+
      "E\7\3\2\2EG\7)\2\2FB\3\2\2\2FC\3\2\2\2FD\3\2\2\2G\r\3\2\2\2HJ\5\20\t\2"+
      "IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\17\3\2\2\2MK\3\2\2\2NO\5\f\7"+
      "\2OT\7)\2\2PQ\7\t\2\2QS\7)\2\2RP\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2"+
      "UW\3\2\2\2VT\3\2\2\2WX\7\6\2\2X\21\3\2\2\2Y[\5\24\13\2ZY\3\2\2\2[^\3\2"+
      "\2\2\\Z\3\2\2\2\\]\3\2\2\2]\23\3\2\2\2^\\\3\2\2\2_`\7\n\2\2`a\7)\2\2a"+
      "b\5\26\f\2bc\5\30\r\2cd\7\4\2\2de\5\16\b\2ef\5\36\20\2fg\7\5\2\2g\25\3"+
      "\2\2\2hq\7\13\2\2in\5\n\6\2jk\7\t\2\2km\5\n\6\2lj\3\2\2\2mp\3\2\2\2nl"+
      "\3\2\2\2no\3\2\2\2or\3\2\2\2pn\3\2\2\2qi\3\2\2\2qr\3\2\2\2rs\3\2\2\2s"+
      "t\7\f\2\2t\27\3\2\2\2ux\5\f\7\2vx\7\r\2\2wu\3\2\2\2wv\3\2\2\2x\31\3\2"+
      "\2\2y\u00a7\5\34\17\2z{\5 \21\2{|\7\16\2\2|}\5\"\22\2}~\7\6\2\2~\u00a7"+
      "\3\2\2\2\177\u0080\7\17\2\2\u0080\u0081\5\"\22\2\u0081\u0082\7\6\2\2\u0082"+
      "\u00a7\3\2\2\2\u0083\u0084\7\17\2\2\u0084\u0085\5\"\22\2\u0085\u0086\7"+
      "\20\2\2\u0086\u0087\7\6\2\2\u0087\u00a7\3\2\2\2\u0088\u0089\7\21\2\2\u0089"+
      "\u008a\7\13\2\2\u008a\u008b\5\"\22\2\u008b\u008c\7\f\2\2\u008c\u008f\5"+
      "\32\16\2\u008d\u008e\7\22\2\2\u008e\u0090\5\32\16\2\u008f\u008d\3\2\2"+
      "\2\u008f\u0090\3\2\2\2\u0090\u00a7\3\2\2\2\u0091\u0092\7\23\2\2\u0092"+
      "\u0093\7\13\2\2\u0093\u0094\5\"\22\2\u0094\u0095\7\f\2\2\u0095\u0096\5"+
      "\32\16\2\u0096\u00a7\3\2\2\2\u0097\u0098\7\24\2\2\u0098\u0099\5\"\22\2"+
      "\u0099\u009a\7\6\2\2\u009a\u00a7\3\2\2\2\u009b\u009d\7\25\2\2\u009c\u009e"+
      "\5\"\22\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2"+
      "\u009f\u00a7\7\6\2\2\u00a0\u00a1\7)\2\2\u00a1\u00a2\7\13\2\2\u00a2\u00a3"+
      "\5$\23\2\u00a3\u00a4\7\f\2\2\u00a4\u00a5\7\6\2\2\u00a5\u00a7\3\2\2\2\u00a6"+
      "y\3\2\2\2\u00a6z\3\2\2\2\u00a6\177\3\2\2\2\u00a6\u0083\3\2\2\2\u00a6\u0088"+
      "\3\2\2\2\u00a6\u0091\3\2\2\2\u00a6\u0097\3\2\2\2\u00a6\u009b\3\2\2\2\u00a6"+
      "\u00a0\3\2\2\2\u00a7\33\3\2\2\2\u00a8\u00a9\7\4\2\2\u00a9\u00aa\5\36\20"+
      "\2\u00aa\u00ab\7\5\2\2\u00ab\35\3\2\2\2\u00ac\u00ae\5\32\16\2\u00ad\u00ac"+
      "\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
      "\37\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\b\21\1\2\u00b3\u00b4\7)\2"+
      "\2\u00b4\u00ba\3\2\2\2\u00b5\u00b6\f\3\2\2\u00b6\u00b7\7\26\2\2\u00b7"+
      "\u00b9\7)\2\2\u00b8\u00b5\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2"+
      "\2\2\u00ba\u00bb\3\2\2\2\u00bb!\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be"+
      "\b\22\1\2\u00be\u00bf\7)\2\2\u00bf\u00c0\7\13\2\2\u00c0\u00c1\5$\23\2"+
      "\u00c1\u00c2\7\f\2\2\u00c2\u00d1\3\2\2\2\u00c3\u00c4\t\2\2\2\u00c4\u00d1"+
      "\5\"\22\20\u00c5\u00d1\7)\2\2\u00c6\u00d1\7*\2\2\u00c7\u00d1\t\3\2\2\u00c8"+
      "\u00c9\7&\2\2\u00c9\u00d1\7)\2\2\u00ca\u00d1\7\'\2\2\u00cb\u00d1\7(\2"+
      "\2\u00cc\u00cd\7\13\2\2\u00cd\u00ce\5\"\22\2\u00ce\u00cf\7\f\2\2\u00cf"+
      "\u00d1\3\2\2\2\u00d0\u00bd\3\2\2\2\u00d0\u00c3\3\2\2\2\u00d0\u00c5\3\2"+
      "\2\2\u00d0\u00c6\3\2\2\2\u00d0\u00c7\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d0"+
      "\u00ca\3\2\2\2\u00d0\u00cb\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d1\u00e9\3\2"+
      "\2\2\u00d2\u00d3\f\17\2\2\u00d3\u00d4\t\4\2\2\u00d4\u00e8\5\"\22\20\u00d5"+
      "\u00d6\f\16\2\2\u00d6\u00d7\t\5\2\2\u00d7\u00e8\5\"\22\17\u00d8\u00d9"+
      "\f\r\2\2\u00d9\u00da\t\6\2\2\u00da\u00e8\5\"\22\16\u00db\u00dc\f\f\2\2"+
      "\u00dc\u00dd\t\7\2\2\u00dd\u00e8\5\"\22\r\u00de\u00df\f\13\2\2\u00df\u00e0"+
      "\7\"\2\2\u00e0\u00e8\5\"\22\f\u00e1\u00e2\f\n\2\2\u00e2\u00e3\7#\2\2\u00e3"+
      "\u00e8\5\"\22\13\u00e4\u00e5\f\21\2\2\u00e5\u00e6\7\26\2\2\u00e6\u00e8"+
      "\7)\2\2\u00e7\u00d2\3\2\2\2\u00e7\u00d5\3\2\2\2\u00e7\u00d8\3\2\2\2\u00e7"+
      "\u00db\3\2\2\2\u00e7\u00de\3\2\2\2\u00e7\u00e1\3\2\2\2\u00e7\u00e4\3\2"+
      "\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
      "#\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00f1\5\"\22\2\u00ed\u00ee\7\t\2\2"+
      "\u00ee\u00f0\5\"\22\2\u00ef\u00ed\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef"+
      "\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4"+
      "\u00ec\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5%\3\2\2\2\25.=FKT\\nqw\u008f\u009d"+
      "\u00a6\u00af\u00ba\u00d0\u00e7\u00e9\u00f1\u00f4";
   public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());
   static {
      _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
      for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
         _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
      }
   }
}