package com.example;

import org.springframework.stereotype.Component;

import com.example.tutorial.Calculator;
import com.example.tutorial.InvalidOperation;
import com.example.tutorial.Work;

@Component
public class CalculatorHandler implements Calculator.Iface {
    @Override
    public int calculate(Work work) throws InvalidOperation {
        int val;
        switch (work.op) {
            case ADD:
                val = work.num1 + work.num2;
                break;
            case SUBTRACT:
                val = work.num1 - work.num2;
                break;
            case MULTIPLY:
                val = work.num1 * work.num2;
                break;
            case DIVIDE:
                if (work.num2 == 0) {
                    InvalidOperation io = new InvalidOperation();
                    io.whatOp = work.op.getValue();
                    io.why = "Cannot divide by 0";
                    throw io;
                }
                val = work.num1 / work.num2;
                break;
            default:
                InvalidOperation io = new InvalidOperation();
                io.whatOp = work.op.getValue();
                io.why = "Unknown operation";
                throw io;
        }

        return val;
    }
}
