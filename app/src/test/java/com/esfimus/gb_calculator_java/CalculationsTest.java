package com.esfimus.gb_calculator_java;

import org.junit.Assert;
import org.junit.Test;

public class CalculationsTest {

    private final Presenter p = new Presenter();

    @Test
    public void digitCheck1() {
        this.p.passValue("c");
        this.p.passValue("5");
        Assert.assertEquals("5", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("50", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("505", p.getMainResult());
    }

    @Test
    public void digitCheck2() {
        this.p.passValue("c");
        this.p.addSymbols("-");
        this.p.passValue("5");
        Assert.assertEquals("-5", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("-50", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("-505", p.getMainResult());
    }

    @Test
    public void digitCheck3() {
        this.p.passValue("c");
        this.p.addSymbols("35+");
        this.p.passValue("5");
        Assert.assertEquals("35+5", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("35+50", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("35+505", p.getMainResult());
    }

    @Test
    public void digitCheck4() {
        this.p.passValue("c");
        this.p.addSymbols("(8+35)x");
        this.p.passValue("5");
        Assert.assertEquals("(8+35)x5", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("(8+35)x50", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("(8+35)x505", p.getMainResult());
    }

    @Test
    public void digitCheck5() {
        this.p.passValue("c");
        this.p.addSymbols("8+(");
        this.p.passValue("5");
        Assert.assertEquals("8+(5", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("8+(50", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("8+(505", p.getMainResult());
    }

    @Test
    public void digitCheck6() {
        this.p.passValue("c");
        this.p.addSymbols("(5+7)");
        this.p.passValue("5");
        Assert.assertEquals("(5+7)", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("(5+7)", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("(5+7)", p.getMainResult());
    }

    @Test
    public void zeroCheck1() {
        this.p.passValue("c");
        this.p.passValue("0");
        Assert.assertEquals("0", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("0.0", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("0.05", p.getMainResult());
        this.p.passValue("c");
        this.p.passValue("0");
        Assert.assertEquals("0", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("0.5", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("0.55", p.getMainResult());
    }

    @Test
    public void zeroCheck2() {
        this.p.passValue("c");
        this.p.addSymbols("35+");
        this.p.passValue("0");
        Assert.assertEquals("35+0", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("35+0.0", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("35+0.05", p.getMainResult());
    }

    @Test
    public void zeroCheck3() {
        this.p.passValue("c");
        this.p.addSymbols("(8+35)x");
        this.p.passValue("0");
        Assert.assertEquals("(8+35)x0", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("(8+35)x0.0", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("(8+35)x0.05", p.getMainResult());
    }

    @Test
    public void zeroCheck4() {
        this.p.passValue("c");
        this.p.addSymbols("8+(");
        this.p.passValue("0");
        Assert.assertEquals("8+(0", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("8+(0.0", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("8+(0.05", p.getMainResult());
    }

    @Test
    public void zeroCheck5() {
        this.p.passValue("c");
        this.p.addSymbols("8+(");
        this.p.passValue("0");
        Assert.assertEquals("8+(0", p.getMainResult());
        this.p.passValue("3");
        Assert.assertEquals("8+(0.3", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("8+(0.35", p.getMainResult());
    }

    @Test
    public void zeroCheck6() {
        this.p.passValue("c");
        this.p.addSymbols("(5+7)");
        this.p.passValue("0");
        Assert.assertEquals("(5+7)", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("(5+7)", p.getMainResult());
    }

    @Test
    public void dotCheck1() {
        this.p.passValue("c");
        this.p.passValue(".");
        Assert.assertEquals("0.", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("0.0", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("0.01", p.getMainResult());
    }

    @Test
    public void dotCheck2() {
        this.p.passValue("c");
        this.p.passValue(".");
        Assert.assertEquals("0.", p.getMainResult());
        this.p.passValue("4");
        Assert.assertEquals("0.4", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("0.40", p.getMainResult());
    }

    @Test
    public void dotCheck3() {
        this.p.passValue("c");
        this.p.passValue(".");
        Assert.assertEquals("0.", p.getMainResult());
        this.p.passValue("4");
        Assert.assertEquals("0.4", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("0.40", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("0.401", p.getMainResult());
        this.p.passValue(".");
        Assert.assertEquals("0.401", p.getMainResult());
    }

    @Test
    public void dotCheck4() {
        this.p.passValue("c");
        this.p.addSymbols("7");
        Assert.assertEquals("7", p.getMainResult());
        this.p.passValue(".");
        Assert.assertEquals("7.", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("7.0", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("7.01", p.getMainResult());
        this.p.passValue(".");
        Assert.assertEquals("7.01", p.getMainResult());
    }

    @Test
    public void dotCheck5() {
        this.p.passValue("c");
        this.p.addSymbols("-");
        this.p.passValue(".");
        Assert.assertEquals("-0.", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("-0.0", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("-0.01", p.getMainResult());
        this.p.passValue(".");
        Assert.assertEquals("-0.01", p.getMainResult());
    }

    @Test
    public void dotCheck6() {
        this.p.passValue("c");
        this.p.addSymbols("3+");
        this.p.passValue(".");
        Assert.assertEquals("3+0.", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("3+0.0", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("3+0.01", p.getMainResult());
        this.p.passValue(".");
        Assert.assertEquals("3+0.01", p.getMainResult());
    }

    @Test
    public void dotCheck7() {
        this.p.passValue("c");
        this.p.addSymbols("(3+5)");
        this.p.passValue(".");
        Assert.assertEquals("(3+5)", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("(3+5)", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("(3+5)", p.getMainResult());
        this.p.passValue(".");
        Assert.assertEquals("(3+5)", p.getMainResult());
    }

    @Test
    public void dotCheck8() {
        this.p.passValue("c");
        this.p.addSymbols("9*(");
        this.p.passValue(".");
        Assert.assertEquals("9*(0.", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("9*(0.0", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("9*(0.01", p.getMainResult());
        this.p.passValue(".");
        Assert.assertEquals("9*(0.01", p.getMainResult());
    }

    @Test
    public void minusCheck1() {
        this.p.passValue("c");
        this.p.passValue("-");
        Assert.assertEquals("-", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("-0", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("-0.1", p.getMainResult());
        this.p.passValue("9");
        Assert.assertEquals("-0.19", p.getMainResult());
    }

    @Test
    public void minusCheck2() {
        this.p.passValue("c");
        this.p.passValue("-");
        Assert.assertEquals("-", p.getMainResult());
        this.p.passValue("5");
        Assert.assertEquals("-5", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("-51", p.getMainResult());
        this.p.passValue("9");
        Assert.assertEquals("-519", p.getMainResult());
    }

    @Test
    public void minusCheck3() {
        this.p.passValue("c");
        this.p.addSymbols("932");
        this.p.passValue("-");
        Assert.assertEquals("932-", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("932-0", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("932-0.1", p.getMainResult());
        this.p.passValue("9");
        Assert.assertEquals("932-0.19", p.getMainResult());
    }

    @Test
    public void minusCheck4() {
        this.p.passValue("c");
        this.p.addSymbols("932/2");
        this.p.passValue("-");
        Assert.assertEquals("932/2-", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("932/2-0", p.getMainResult());
        this.p.passValue("1");
        Assert.assertEquals("932/2-0.1", p.getMainResult());
        this.p.passValue("9");
        Assert.assertEquals("932/2-0.19", p.getMainResult());
    }

    @Test
    public void minusCheck5() {
        this.p.passValue("c");
        this.p.addSymbols("932-");
        this.p.passValue("-");
        Assert.assertEquals("932-", p.getMainResult());
        this.p.passValue("0");
        Assert.assertEquals("932-0", p.getMainResult());
        this.p.passValue("-");
        Assert.assertEquals("932-0-", p.getMainResult());
        this.p.passValue("9");
        Assert.assertEquals("932-0-9", p.getMainResult());
    }

    @Test
    public void minusCheck6() {
        this.p.passValue("c");
        this.p.addSymbols("(932-7)");
        this.p.passValue("-");
        Assert.assertEquals("(932-7)-", p.getMainResult());
        this.p.passValue("7");
        Assert.assertEquals("(932-7)-7", p.getMainResult());
        this.p.passValue("-");
        Assert.assertEquals("(932-7)-7-", p.getMainResult());
        this.p.passValue("9");
        Assert.assertEquals("(932-7)-7-9", p.getMainResult());
    }

    @Test
    public void minusCheck7() {
        this.p.passValue("c");
        this.p.addSymbols("932-7x(");
        this.p.passValue("-");
        Assert.assertEquals("932-7x(", p.getMainResult());
        this.p.passValue("7");
        Assert.assertEquals("932-7x(7", p.getMainResult());
        this.p.passValue("-");
        Assert.assertEquals("932-7x(7-", p.getMainResult());
        this.p.passValue("9");
        Assert.assertEquals("932-7x(7-9", p.getMainResult());
    }

    @Test
    public void minusCheck8() {
        this.p.passValue("c");
        this.p.addSymbols("932-7x");
        this.p.passValue("-");
        Assert.assertEquals("932-7-", p.getMainResult());
        this.p.passValue("-");
        Assert.assertEquals("932-7-", p.getMainResult());
        this.p.passValue("7");
        Assert.assertEquals("932-7-7", p.getMainResult());
        this.p.passValue("-");
        Assert.assertEquals("932-7-7-", p.getMainResult());
    }

    @Test
    public void minusCheck9() {
        this.p.passValue("c");
        this.p.addSymbols("932+");
        this.p.passValue("-");
        Assert.assertEquals("932-", p.getMainResult());
        this.p.passValue("-");
        Assert.assertEquals("932-", p.getMainResult());
        this.p.passValue("7");
        Assert.assertEquals("932-7", p.getMainResult());
    }

    @Test
    public void minusCheck10() {
        this.p.passValue("c");
        this.p.addSymbols("932+");
        this.p.passValue(".");
        Assert.assertEquals("932+0.", p.getMainResult());
        this.p.passValue("-");
        Assert.assertEquals("932+0-", p.getMainResult());
        this.p.passValue("7");
        Assert.assertEquals("932+0-7", p.getMainResult());
    }

    @Test
    public void operatorCheck1() {
        this.p.passValue("c");
        this.p.passValue("+");
        Assert.assertEquals("", p.getMainResult());
        this.p.passValue("/");
        Assert.assertEquals("", p.getMainResult());
        this.p.passValue("x");
        Assert.assertEquals("", p.getMainResult());
    }

    @Test
    public void operatorCheck2() {
        this.p.passValue("c");
        this.p.addSymbols("932");
        this.p.passValue("+");
        Assert.assertEquals("932+", p.getMainResult());
        this.p.passValue("/");
        Assert.assertEquals("932/", p.getMainResult());
        this.p.passValue("x");
        Assert.assertEquals("932x", p.getMainResult());
    }

    @Test
    public void operatorCheck3() {
        this.p.passValue("c");
        this.p.addSymbols("-");
        this.p.passValue("+");
        Assert.assertEquals("-", p.getMainResult());
    }

    @Test
    public void operatorCheck4() {
        this.p.passValue("c");
        this.p.addSymbols("932x(");
        this.p.passValue("+");
        Assert.assertEquals("932x(", p.getMainResult());
        this.p.passValue("/");
        Assert.assertEquals("932x(", p.getMainResult());
        this.p.passValue("x");
        Assert.assertEquals("932x(", p.getMainResult());
    }

    @Test
    public void operatorCheck5() {
        this.p.passValue("c");
        this.p.addSymbols("40*(932-57)");
        this.p.passValue("+");
        Assert.assertEquals("40*(932-57)+", p.getMainResult());
        this.p.passValue("/");
        Assert.assertEquals("40*(932-57)/", p.getMainResult());
        this.p.passValue("x");
        Assert.assertEquals("40*(932-57)x", p.getMainResult());
    }

    @Test
    public void bracketsCheck1() {
        this.p.passValue("c");
        this.p.passValue("(");
        Assert.assertEquals("", p.getMainResult());
    }

    @Test
    public void bracketsCheck2() {
        this.p.passValue("c");
        this.p.addSymbols("-");
        this.p.passValue("(");
        Assert.assertEquals("-", p.getMainResult());
    }

    @Test
    public void bracketsCheck3() {
        this.p.passValue("c");
        this.p.addSymbols("5");
        this.p.passValue("(");
        Assert.assertEquals("5", p.getMainResult());
    }

    @Test
    public void bracketsCheck4() {
        this.p.passValue("c");
        this.p.addSymbols("0.");
        this.p.passValue("(");
        Assert.assertEquals("0.", p.getMainResult());
    }

    @Test
    public void bracketsCheck5() {
        this.p.passValue("c");
        this.p.addSymbols("3-5");
        this.p.passValue("(");
        Assert.assertEquals("3-5", p.getMainResult());
    }

    @Test
    public void bracketsCheck6() {
        this.p.passValue("c");
        this.p.addSymbols("3-5x");
        this.p.passValue("(");
        Assert.assertEquals("3-5x(", p.getMainResult());
    }

    @Test
    public void bracketsCheck7() {
        this.p.passValue("c");
        this.p.addSymbols("3-5x");
        this.p.passValue("(");
        Assert.assertEquals("3-5x(", p.getMainResult());
    }

    @Test
    public void bracketsCheck8() {
        this.p.passValue("c");
        this.p.addSymbols("3-5x(45");
        this.p.passValue("(");
        Assert.assertEquals("3-5x(45)", p.getMainResult());
    }

    @Test
    public void bracketsCheck9() {
        this.p.passValue("c");
        this.p.addSymbols("3-5x(45-");
        this.p.passValue("(");
        Assert.assertEquals("3-5x(45-(", p.getMainResult());
    }

    @Test
    public void bracketsCheck11() {
        this.p.passValue("c");
        this.p.addSymbols("3-5x(45-5");
        this.p.passValue("(");
        Assert.assertEquals("3-5x(45-5)", p.getMainResult());
    }

    @Test
    public void bracketsCheck12() {
        this.p.passValue("c");
        this.p.addSymbols("3-5x(45-5/(4-2");
        this.p.passValue("(");
        Assert.assertEquals("3-5x(45-5/(4-2)", p.getMainResult());
        this.p.passValue("(");
        Assert.assertEquals("3-5x(45-5/(4-2))", p.getMainResult());
    }

    @Test
    public void equalsCheck1() {
        this.p.passValue("c");
        this.p.passValue("=");
        Assert.assertEquals("", p.getSubResult());
        Assert.assertEquals("", p.getMainResult());
    }

    @Test
    public void equalsCheck2() {
        this.p.passValue("c");
        this.p.addSymbols("3");
        this.p.passValue("=");
        Assert.assertEquals("3", p.getSubResult());
        Assert.assertEquals("3", p.getMainResult());
    }

    @Test
    public void equalsCheck3() {
        this.p.passValue("c");
        this.p.addSymbols("3x");
        this.p.passValue("=");
        Assert.assertEquals("(3x3)", p.getSubResult());
        Assert.assertEquals("9", p.getMainResult());
    }

    @Test
    public void equalsCheck4() {
        this.p.passValue("c");
        this.p.addSymbols("-3+");
        this.p.passValue("=");
        Assert.assertEquals("-(3+3)", p.getSubResult());
        Assert.assertEquals("-6", p.getMainResult());
    }

    @Test
    public void equalsCheck5() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x");
        this.p.passValue("=");
        Assert.assertEquals("34+(5x5)", p.getSubResult());
        Assert.assertEquals("59", p.getMainResult());
    }

    @Test
    public void equalsCheck6() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x(");
        this.p.passValue("=");
        Assert.assertEquals("34+(5x5)", p.getSubResult());
        Assert.assertEquals("59", p.getMainResult());
    }

    @Test
    public void equalsCheck7() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x(45");
        this.p.passValue("=");
        Assert.assertEquals("34+5x(45)", p.getSubResult());
        Assert.assertEquals("259", p.getMainResult());
    }

    @Test
    public void equalsCheck8() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x(45-");
        this.p.passValue("=");
        Assert.assertEquals("34+5x((45-45))", p.getSubResult());
        Assert.assertEquals("34", p.getMainResult());
    }

    @Test
    public void equalsCheck9() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x(45-5/(");
        this.p.passValue("=");
        Assert.assertEquals("34+5x(45-(5/5))", p.getSubResult());
        Assert.assertEquals("254", p.getMainResult());
    }

    @Test
    public void equalsCheck10() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x(45-5/(7");
        this.p.passValue("=");
        Assert.assertEquals("34+5x(45-5/(7))", p.getSubResult());
        Assert.assertEquals("255.4285714", p.getMainResult());
    }

    @Test
    public void equalsCheck11() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x(45-5/(7x9/");
        this.p.passValue("=");
        Assert.assertEquals("34+5x(45-5/(7x(9/9)))", p.getSubResult());
        Assert.assertEquals("255.4285714", p.getMainResult());
    }

    @Test
    public void equalsCheck12() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x(45-5/(7x9/(4-9x(5-32+341/(43");
        this.p.passValue("=");
        Assert.assertEquals("34+5x(45-5/(7x9/(4-9x(5-32+341/(43)))))", p.getSubResult());
        Assert.assertEquals("189.3063861", p.getMainResult());
    }

    @Test
    public void equalsCheck13() {
        this.p.passValue("c");
        this.p.addSymbols("34+5x(45-5/(7x9/(4-9x(5-32+341/(43-423523/1255x435x");
        this.p.passValue("=");
        Assert.assertEquals("34+5x(45-5/(7x9/(4-9x(5-32+341/(43-423523/1255x(435x435))))))", p.getSubResult());
        Assert.assertEquals("160.9841079", p.getMainResult());
    }

    @Test
    public void equalsCheck14() {
        this.p.passValue("c");
        this.p.addSymbols("586.52+88.35x(7.42+0.0523/(91.58-34.756x(475.5+3.0/(587.896x752.5053))))");
        this.p.passValue("=");
        Assert.assertEquals("586.52+88.35x(7.42+0.0523/(91.58-34.756x(475.5+3.0/(587.896x752.5053))))", p.getSubResult());
        Assert.assertEquals("1242.0767188", p.getMainResult());
    }

    @Test
    public void equalsCheck15() {
        this.p.passValue("c");
        this.p.addSymbols("32-4x(54-21x3)+12-200/5/2+11+30/(4+6)+");
        this.p.passValue("=");
        Assert.assertEquals("32-4x(54-21x3)+12-200/5/2+11+30/(4+6)", p.getSubResult());
        Assert.assertEquals("74", p.getMainResult());
    }

    @Test
    public void equalsCheck16() {
        this.p.passValue("c");
        this.p.addSymbols("32-4x(54-21x(3+12)-200/5/2+11)+30/(4+6x(5-1");
        this.p.passValue("=");
        Assert.assertEquals("32-4x(54-21x(3+12)-200/5/2+11)+30/(4+6x(5-1))", p.getSubResult());
        Assert.assertEquals("1113.0714286", p.getMainResult());
    }

    @Test
    public void equalsCheck17() {
        this.p.passValue("c");
        this.p.addSymbols("3-4x(4-2x(3+2)-20/5/2+1)+30/(6+6x(5-1))x");
        this.p.passValue("=");
        Assert.assertEquals("3-4x(4-2x(3+2)-20/5/2+1)+30/(6+6x(5-1))", p.getSubResult());
        Assert.assertEquals("32", p.getMainResult());
    }

    @Test
    public void equalsCheck18() {
        this.p.passValue("c");
        this.p.addSymbols("5-(2x3-7)x(4-2)/2+5x(3-6/(1+2)");
        this.p.passValue("=");
        Assert.assertEquals("5-(2x3-7)x(4-2)/2+5x(3-6/(1+2))", p.getSubResult());
        Assert.assertEquals("11", p.getMainResult());
    }

    @Test
    public void equalsCheck19() {
        this.p.passValue("c");
        this.p.addSymbols("-589");
        this.p.passValue("=");
        Assert.assertEquals("-589", p.getSubResult());
        Assert.assertEquals("-589", p.getMainResult());
    }

    @Test
    public void equalsCheck20() {
        this.p.passValue("c");
        this.p.addSymbols("-57+72");
        this.p.passValue("=");
        Assert.assertEquals("-57+72", p.getSubResult());
        Assert.assertEquals("15", p.getMainResult());
    }

    @Test
    public void equalsCheck21() {
        this.p.passValue("c");
        this.p.addSymbols("-57.001x(13.5431+4.2451)/(5.2515-0.4125x4.415-1.415236x0.04/(45.07-24.1x2.41");
        this.p.passValue("=");
        Assert.assertEquals("-57.001x(13.5431+4.2451)/(5.2515-0.4125x4.415-1.415236x0.04/(45.07-24.1x2.41))", p.getSubResult());
        Assert.assertEquals("-295.2094784", p.getMainResult());
    }

}