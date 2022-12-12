package visitor;

import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class PrintVisitor implements TokenVisitor {
    PrintStream out;
    StringBuilder result;

    public PrintVisitor(OutputStream out) {
        this.out = new PrintStream(out);
    }
    @Override
    public void visit(NumberToken token) {
        result.append(token.toString())
                .append(" ");
    }

    @Override
    public void visit(Brace token) {
        throw new IllegalStateException();
    }

    @Override
    public void visit(Operation token) {
        result.append(token.toString())
                .append(" ");
    }

    public void print(List<Token> tokens) {
        result = new StringBuilder();
        for (Token token : tokens) {
            token.accept(this);
        }
        out.print(result);
    }
}
