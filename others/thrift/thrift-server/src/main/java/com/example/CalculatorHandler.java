package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.shared.SharedStruct;
import com.example.tutorial.Calculator;
import com.example.tutorial.InvalidOperation;
import com.example.tutorial.Work;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CalculatorHandler implements Calculator.Iface {
    private final Map<Integer, SharedStruct> map;

    public CalculatorHandler() {
        map = new HashMap<>();
    }

    @Override
    public void ping() {
        log.info("ping()");
    }

    @Override
    public int add(int n1, int n2) {
        log.info("add({}, {})", n1, n2);
        return n1 + n2;
    }

    @Override
    public int calculate(int logid, Work work) throws InvalidOperation {
        log.info("calculate({}, {{},{},{}})", logid, work.op, work.num1, work.num2);
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

        SharedStruct entry = new SharedStruct();
        entry.key = logid;
        entry.value = Integer.toString(val);
        map.put(logid, entry);

        return val;
    }

    @Override
    public SharedStruct getStruct(int key) {
        log.info("getStruct({})", key);
        return map.get(key);
    }

    @Override
    public void zip() {
        log.info("zip()");
    }
}
