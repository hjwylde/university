package whilelang.testing.tests;

import org.junit.Test;

import whilelang.testing.TestHarness;

public class InterpreterValidTests extends TestHarness {

    public InterpreterValidTests() {
        super("tests/valid", "tests/valid", "sysout");
    }

    @Test
    public void BoolAssign_Valid_1() { runInterpreterTest("BoolAssign_Valid_1"); }

    @Test
    public void BoolAssign_Valid_2() { runInterpreterTest("BoolAssign_Valid_2"); }

    @Test
    public void BoolAssign_Valid_3() { runInterpreterTest("BoolAssign_Valid_3"); }

    @Test
    public void BoolAssign_Valid_4() { runInterpreterTest("BoolAssign_Valid_4"); }

    @Test
    public void BoolIfElse_Valid_1() { runInterpreterTest("BoolIfElse_Valid_1"); }

    @Test
    public void BoolIfElse_Valid_2() { runInterpreterTest("BoolIfElse_Valid_2"); }

    @Test
    public void BoolList_Valid_1() { runInterpreterTest("BoolList_Valid_1"); }

    @Test
    public void BoolList_Valid_2() { runInterpreterTest("BoolList_Valid_2"); }

    @Test
    public void BoolRecord_Valid_1() { runInterpreterTest("BoolRecord_Valid_1"); }

    @Test
    public void BoolRecord_Valid_2() { runInterpreterTest("BoolRecord_Valid_2"); }

    @Test
    public void BoolReturn_Valid_1() { runInterpreterTest("BoolReturn_Valid_1"); }

    @Test
    public void Cast_Valid_1() { runInterpreterTest("Cast_Valid_1"); }

    @Test
    public void Cast_Valid_2() { runInterpreterTest("Cast_Valid_2"); }

    @Test
    public void Cast_Valid_3() { runInterpreterTest("Cast_Valid_3"); }

    @Test
    public void Cast_Valid_4() { runInterpreterTest("Cast_Valid_4"); }

    @Test
    public void Cast_Valid_5() { runInterpreterTest("Cast_Valid_5"); }

    @Test
    public void Cast_Valid_6() { runInterpreterTest("Cast_Valid_6"); }

    @Test
    public void Char_Valid_1() { runInterpreterTest("Char_Valid_1"); }

    @Test
    public void Char_Valid_2() { runInterpreterTest("Char_Valid_2"); }

    @Test
    public void Char_Valid_3() { runInterpreterTest("Char_Valid_3"); }

    @Test
    public void Const_Valid_1() { runInterpreterTest("Const_Valid_1"); }

    @Test
    public void Const_Valid_2() { runInterpreterTest("Const_Valid_2"); }

    @Test
    public void Const_Valid_3() { runInterpreterTest("Const_Valid_3"); }

    @Test
    public void Const_Valid_4() { runInterpreterTest("Const_Valid_4"); }

    @Test
    public void Const_Valid_5() { runInterpreterTest("Const_Valid_5"); }

    @Test
    public void Const_Valid_6() { runInterpreterTest("Const_Valid_6"); }

    @Test
    public void Const_Valid_7() { runInterpreterTest("Const_Valid_7"); }

    @Test
    public void Define_Valid_1() { runInterpreterTest("Define_Valid_1"); }

    @Test
    public void Define_Valid_2() { runInterpreterTest("Define_Valid_2"); }

    @Test
    public void DefiniteAssign_Valid_1() { runInterpreterTest("DefiniteAssign_Valid_1"); }

    @Test
    public void DefiniteAssign_Valid_2() { runInterpreterTest("DefiniteAssign_Valid_2"); }

    @Test
    public void DefiniteAssign_Valid_3() { runInterpreterTest("DefiniteAssign_Valid_3"); }

    @Test
    public void For_Valid_1() { runInterpreterTest("For_Valid_1"); }

    @Test
    public void Function_Valid_1() { runInterpreterTest("Function_Valid_1"); }

    @Test
    public void Function_Valid_2() { runInterpreterTest("Function_Valid_2"); }

    @Test
    public void Function_Valid_4() { runInterpreterTest("Function_Valid_4"); }

    @Test
    public void IfElse_Valid_1() { runInterpreterTest("IfElse_Valid_1"); }

    @Test
    public void IfElse_Valid_2() { runInterpreterTest("IfElse_Valid_2"); }

    @Test
    public void IfElse_Valid_3() { runInterpreterTest("IfElse_Valid_3"); }

    @Test
    public void IfElse_Valid_4() { runInterpreterTest("IfElse_Valid_4"); }

    @Test
    public void IfElse_Valid_7() { runInterpreterTest("IfElse_Valid_7"); }

    @Test
    public void IntDefine_Valid_1() { runInterpreterTest("IntDefine_Valid_1"); }

    @Test
    public void IntDiv_Valid_1() { runInterpreterTest("IntDiv_Valid_1"); }

    @Test
    public void IntDiv_Valid_2() { runInterpreterTest("IntDiv_Valid_2"); }

    @Test
    public void IntEquals_Valid_1() { runInterpreterTest("IntEquals_Valid_1"); }

    @Test
    public void IntMul_Valid_1() { runInterpreterTest("IntMul_Valid_1"); }

    @Test
    public void Is_Valid_1() { runInterpreterTest("Is_Valid_1"); }

    @Test
    public void Is_Valid_2() { runInterpreterTest("Is_Valid_2"); }

    @Test
    public void Is_Valid_3() { runInterpreterTest("Is_Valid_3"); }

    @Test
    public void Is_Valid_4() { runInterpreterTest("Is_Valid_4"); }

    @Test
    public void Is_Valid_5() { runInterpreterTest("Is_Valid_5"); }

    @Test
    public void LengthOf_Valid_1() { runInterpreterTest("LengthOf_Valid_1"); }

    @Test
    public void LengthOf_Valid_5() { runInterpreterTest("LengthOf_Valid_5"); }

    @Test
    public void ListAccess_Valid_1() { runInterpreterTest("ListAccess_Valid_1"); }

    @Test
    public void ListAccess_Valid_3() { runInterpreterTest("ListAccess_Valid_3"); }

    @Test
    public void ListAccess_Valid_4() { runInterpreterTest("ListAccess_Valid_4"); }

    @Test
    public void ListAccess_Valid_5() { runInterpreterTest("ListAccess_Valid_5"); }

    @Test
    public void ListAppend_Valid_1() { runInterpreterTest("ListAppend_Valid_1"); }

    @Test
    public void ListAppend_Valid_2() { runInterpreterTest("ListAppend_Valid_2"); }

    @Test
    public void ListAppend_Valid_3() { runInterpreterTest("ListAppend_Valid_3"); }

    @Test
    public void ListAppend_Valid_4() { runInterpreterTest("ListAppend_Valid_4"); }

    @Test
    public void ListAppend_Valid_5() { runInterpreterTest("ListAppend_Valid_5"); }

    @Test
    public void ListAppend_Valid_6() { runInterpreterTest("ListAppend_Valid_6"); }

    @Test
    public void ListAppend_Valid_7() { runInterpreterTest("ListAppend_Valid_7"); }

    @Test
    public void ListAssign_Valid_1() { runInterpreterTest("ListAssign_Valid_1"); }

    @Test
    public void ListAssign_Valid_10() { runInterpreterTest("ListAssign_Valid_10"); }

    @Test
    public void ListAssign_Valid_11() { runInterpreterTest("ListAssign_Valid_11"); }

    @Test
    public void ListAssign_Valid_12() { runInterpreterTest("ListAssign_Valid_12"); }

    @Test
    public void ListAssign_Valid_13() { runInterpreterTest("ListAssign_Valid_13"); }

    @Test
    public void ListAssign_Valid_2() { runInterpreterTest("ListAssign_Valid_2"); }

    @Test
    public void ListAssign_Valid_3() { runInterpreterTest("ListAssign_Valid_3"); }

    @Test
    public void ListAssign_Valid_4() { runInterpreterTest("ListAssign_Valid_4"); }

    @Test
    public void ListAssign_Valid_5() { runInterpreterTest("ListAssign_Valid_5"); }

    @Test
    public void ListAssign_Valid_6() { runInterpreterTest("ListAssign_Valid_6"); }

    @Test
    public void ListConversion_Valid_1() { runInterpreterTest("ListConversion_Valid_1"); }

    @Test
    public void ListEmpty_Valid_1() { runInterpreterTest("ListEmpty_Valid_1"); }

    @Test
    public void ListEquals_Valid_1() { runInterpreterTest("ListEquals_Valid_1"); }

    @Test
    public void ListGenerator_Valid_1() { runInterpreterTest("ListGenerator_Valid_1"); }

    @Test
    public void ListGenerator_Valid_2() { runInterpreterTest("ListGenerator_Valid_2"); }

    @Test
    public void ListGenerator_Valid_3() { runInterpreterTest("ListGenerator_Valid_3"); }

    @Test
    public void ListLength_Valid_1() { runInterpreterTest("ListLength_Valid_1"); }

    @Test
    public void ListLength_Valid_2() { runInterpreterTest("ListLength_Valid_2"); }

    @Test
    public void MultiLineComment_Valid_1() { runInterpreterTest("MultiLineComment_Valid_1"); }

    @Test
    public void MultiLineComment_Valid_2() { runInterpreterTest("MultiLineComment_Valid_2"); }

    @Test
    public void MultiLineComment_Valid_3() { runInterpreterTest("MultiLineComment_Valid_3"); }

    @Test
    public void MultiLineComment_Valid_4() { runInterpreterTest("MultiLineComment_Valid_4"); }

    @Test
    public void NullEquals_Valid_1() { runInterpreterTest("NullEquals_Valid_1"); }

    @Test
    public void NullSwitch_Valid_1() { runInterpreterTest("NullSwitch_Valid_1"); }

    @Test
    public void Print_Valid_1() { runInterpreterTest("Print_Valid_1"); }

    @Test
    public void Print_Valid_2() { runInterpreterTest("Print_Valid_2"); }

    @Test
    public void Print_Valid_3() { runInterpreterTest("Print_Valid_3"); }

    @Test
    public void Print_Valid_4() { runInterpreterTest("Print_Valid_4"); }

    @Test
    public void Print_Valid_5() { runInterpreterTest("Print_Valid_5"); }

    @Test
    public void Print_Valid_6() { runInterpreterTest("Print_Valid_6"); }

    @Test
    public void Print_Valid_7() { runInterpreterTest("Print_Valid_7"); }

    @Test
    public void Print_Valid_8() { runInterpreterTest("Print_Valid_8"); }

    @Test
    public void Print_Valid_9() { runInterpreterTest("Print_Valid_9"); }

    @Test
    public void RealDiv_Valid_1() { runInterpreterTest("RealDiv_Valid_1"); }

    @Test
    public void RealDiv_Valid_3() { runInterpreterTest("RealDiv_Valid_3"); }

    @Test
    public void RealDiv_Valid_4() { runInterpreterTest("RealDiv_Valid_4"); }

    @Test
    public void RealNeg_Valid_1() { runInterpreterTest("RealNeg_Valid_1"); }

    @Test
    public void RealSub_Valid_1() { runInterpreterTest("RealSub_Valid_1"); }

    @Test
    public void RealSub_Valid_2() { runInterpreterTest("RealSub_Valid_2"); }

    @Test
    public void Real_Valid_1() { runInterpreterTest("Real_Valid_1"); }

    @Test
    public void RecordAccess_Valid_2() { runInterpreterTest("RecordAccess_Valid_2"); }

    @Test
    public void RecordAssign_Valid_1() { runInterpreterTest("RecordAssign_Valid_1"); }

    @Test
    public void RecordAssign_Valid_2() { runInterpreterTest("RecordAssign_Valid_2"); }

    @Test
    public void RecordAssign_Valid_3() { runInterpreterTest("RecordAssign_Valid_3"); }

    @Test
    public void RecordAssign_Valid_4() { runInterpreterTest("RecordAssign_Valid_4"); }

    @Test
    public void RecordAssign_Valid_5() { runInterpreterTest("RecordAssign_Valid_5"); }

    @Test
    public void RecordAssign_Valid_6() { runInterpreterTest("RecordAssign_Valid_6"); }

    @Test
    public void RecordDefine_Valid_1() { runInterpreterTest("RecordDefine_Valid_1"); }

    @Test
    public void Remainder_Valid_1() { runInterpreterTest("Remainder_Valid_1"); }

    @Test
    public void SingleLineComment_Valid_1() { runInterpreterTest("SingleLineComment_Valid_1"); }

    @Test
    public void SingleLineComment_Valid_2() { runInterpreterTest("SingleLineComment_Valid_2"); }

    @Test
    public void SingleLineComment_Valid_3() { runInterpreterTest("SingleLineComment_Valid_3"); }

    @Test
    public void StringAssign_Valid_1() { runInterpreterTest("StringAssign_Valid_1"); }

    @Test
    public void StringAssign_Valid_10() { runInterpreterTest("StringAssign_Valid_10"); }

    @Test
    public void StringAssign_Valid_11() { runInterpreterTest("StringAssign_Valid_11"); }

    @Test
    public void StringAssign_Valid_2() { runInterpreterTest("StringAssign_Valid_2"); }

    @Test
    public void StringAssign_Valid_4() { runInterpreterTest("StringAssign_Valid_4"); }

    @Test
    public void StringAssign_Valid_5() { runInterpreterTest("StringAssign_Valid_5"); }

    @Test
    public void StringAssign_Valid_6() { runInterpreterTest("StringAssign_Valid_6"); }

    @Test
    public void StringAssign_Valid_7() { runInterpreterTest("StringAssign_Valid_7"); }

    @Test
    public void StringAssign_Valid_8() { runInterpreterTest("StringAssign_Valid_8"); }

    @Test
    public void StringAssign_Valid_9() { runInterpreterTest("StringAssign_Valid_9"); }

    @Test
    public void String_Valid_1() { runInterpreterTest("String_Valid_1"); }

    @Test
    public void String_Valid_2() { runInterpreterTest("String_Valid_2"); }

    @Test
    public void String_Valid_3() { runInterpreterTest("String_Valid_3"); }

    @Test
    public void String_Valid_4() { runInterpreterTest("String_Valid_4"); }

    @Test
    public void Switch_Valid_1() { runInterpreterTest("Switch_Valid_1"); }

    @Test
    public void Switch_Valid_10() { runInterpreterTest("Switch_Valid_10"); }

    @Test
    public void Switch_Valid_11() { runInterpreterTest("Switch_Valid_11"); }

    @Test
    public void Switch_Valid_12() { runInterpreterTest("Switch_Valid_12"); }

    @Test
    public void Switch_Valid_13() { runInterpreterTest("Switch_Valid_13"); }

    @Test
    public void Switch_Valid_14() { runInterpreterTest("Switch_Valid_14"); }

    @Test
    public void Switch_Valid_15() { runInterpreterTest("Switch_Valid_15"); }

    @Test
    public void Switch_Valid_2() { runInterpreterTest("Switch_Valid_2"); }

    @Test
    public void Switch_Valid_3() { runInterpreterTest("Switch_Valid_3"); }

    @Test
    public void Switch_Valid_4() { runInterpreterTest("Switch_Valid_4"); }

    @Test
    public void Switch_Valid_6() { runInterpreterTest("Switch_Valid_6"); }

    @Test
    public void Switch_Valid_7() { runInterpreterTest("Switch_Valid_7"); }

    @Test
    public void Switch_Valid_8() { runInterpreterTest("Switch_Valid_8"); }

    @Test
    public void Switch_Valid_9() { runInterpreterTest("Switch_Valid_9"); }

    @Test
    public void TypeEquals_Valid_11_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_11"); }

    @Test
    public void TypeEquals_Valid_14_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_14"); }

    @Test
    public void TypeEquals_Valid_16_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_16"); }

    @Test
    public void TypeEquals_Valid_1_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_1"); }

    @Test
    public void TypeEquals_Valid_20_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_20"); }

    @Test
    public void TypeEquals_Valid_21_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_21"); }

    @Test
    public void TypeEquals_Valid_2_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_2"); }

    @Test
    public void TypeEquals_Valid_5_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_5"); }

    @Test
    public void TypeEquals_Valid_8_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_8"); }

    @Test
    public void TypeEquals_Valid_9_RuntimeTest() { runInterpreterTest("TypeEquals_Valid_9"); }

    @Test
    public void UnionType_Valid_1() { runInterpreterTest("UnionType_Valid_1"); }

    @Test
    public void UnionType_Valid_10() { runInterpreterTest("UnionType_Valid_10"); }

    @Test
    public void UnionType_Valid_11() { runInterpreterTest("UnionType_Valid_11"); }

    @Test
    public void UnionType_Valid_2() { runInterpreterTest("UnionType_Valid_2"); }

    @Test
    public void UnionType_Valid_4() { runInterpreterTest("UnionType_Valid_4"); }

    @Test
    public void UnionType_Valid_5() { runInterpreterTest("UnionType_Valid_5"); }

    @Test
    public void UnionType_Valid_6() { runInterpreterTest("UnionType_Valid_6"); }

    @Test
    public void UnionType_Valid_7() { runInterpreterTest("UnionType_Valid_7"); }

    @Test
    public void UnionType_Valid_8() { runInterpreterTest("UnionType_Valid_8"); }

    @Test
    public void UnionType_Valid_9() { runInterpreterTest("UnionType_Valid_9"); }

    @Test
    public void While_Valid_1() { runInterpreterTest("While_Valid_1"); }

    @Test
    public void While_Valid_2() { runInterpreterTest("While_Valid_2"); }

    @Test
    public void While_Valid_4() { runInterpreterTest("While_Valid_4"); }

    @Test
    public void While_Valid_6() { runInterpreterTest("While_Valid_6"); }

    @Test
    public void While_Valid_7() { runInterpreterTest("While_Valid_7"); }
}
