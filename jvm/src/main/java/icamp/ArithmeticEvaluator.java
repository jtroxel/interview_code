package icamp;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
   - Right to left,
   - repeat as long as next op prec <= last
     - read exp:
     ex - new expression
       - if ch is (, continue
       - if ch is rand, new.addRan
       - if ch is op, prec <= last: ex.addOp
                      - else recurse
 */
public class ArithmeticEvaluator {
    private String ex;

    public ArithmeticEvaluator(String ex) {
        this.ex = ex;
    }

    public int eval() {
        int result = 0;
        //while not done consuming
        return evalExpStr(0, new ArithmeticExpression());
    }

    /*
    - 2 /( 1 + 2 ), last = 2, /
    - 1 / 2( 2 + 3), last = 1, /, 2
    -
     */
    private int evalExpStr(int pos, ArithmeticExpression lastEx) {

        ArithmeticExpression nextEx = new ArithmeticExpression();
        // Read 1 char
//        char next = this.ex.charAt(pos);
//
//        // end or ')' -> return
//        if (ArithmeticToken.opFor(next) == ArithmeticToken.ExpClose) {
//            nextEx.pushOperand(next + 32);
//        } else
//            // rand ? -> push rand
//            if (ArithmeticToken.tokenIsOperand(next)) {
//                nextEx.pushOperand(next + 32);
//            } else
//                // op ?
//                if (ArithmeticToken.opFor(next) == ArithmeticToken.ExpOpen) {
//                    nextEx.pushOperand(evalExpStr(pos + 1, nextEx));
//                } else if (ArithmeticToken.tokenIsOp(next)) {
//                    ArithmeticToken op = ArithmeticToken.opFor(next);
//                    //    op <= lastEx.getOp ? return newEx
//                    if (op.getPrecedence() <= lastEx.op.getPrecedence()) {
//                        return lastEx.eval();
//                    } else {
//                        nextEx.pushOperand(evalExpStr(pos + 1, nextEx));
//                    }
//                }
//                return 0;
//    }}
        return 0;
    }

    private class ArithmeticExpression {
        private Stack<Integer> operands = new Stack<>();
        private ArithmeticToken op = ArithmeticToken.Plus;

        public Stack<Integer> getOperands() {
            return operands;
        }

        public ArithmeticToken getOp() {
            return op;
        }

        public void setOp(ArithmeticToken op) {
            this.op = op;
        }

        private Integer pushOperand(int next) {
            return getOperands().push(next);
        }

        public int eval() {
            int right = getOperands().pop();
            int left = getOperands().pop();
            switch (this.op.getOpStr()) {
                case '/':
                    return left / right;
                case '*':
                    return left * right;
                case '-':
                    return left - right;
                case '+':
                    return left + right;
                default:
                    throw new IllegalStateException("Unexpected value: " + this.op.getOpStr());
            }
        }
    }

    public enum ArithmeticToken {

        ExpOpen('(', 1),
        ExpClose(')', 1),
        Mult('*', 2),
        Div('/', 2),
        Plus('+', 3),
        Minus('-', 3),

        Operand('-', 0);


        private final char opStr;
        private final int precedence;

        ArithmeticToken(char opStr, int precedence) {
            this.opStr = opStr;
            this.precedence = precedence;
        }

        private static final Map<Character, ArithmeticToken> BY_CHAR = new HashMap<>();

        private static boolean tokenIsOperand(char token) {
            return (token >= '0' && token <= '9');
        }

        static {
            for (ArithmeticToken val : values()) {
                BY_CHAR.put(val.opStr, val);
            }
        }

        public static ArithmeticToken opFor(char ch) {
            return BY_CHAR.get(ch);
        }

        public char getOpStr() {
            return opStr;
        }

        public int getPrecedence() {
            return precedence;
        }

        public static boolean tokenIsEx(char token) {
            return (token == '(' || token == ')');
        }

        public static boolean tokenIsOp(char token) {
            return (token == '*' || token == '/' ||
                    token == '+' || token == '-');
        }

    }

}
