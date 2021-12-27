/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.languagemanagement.domain.form;

import eapli.base.servicemanagement.domain.Attribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author migue
 */
public class FormListener extends FormValidationBaseListener{
    
    private List<Attribute> requestAttribute = new ArrayList<>();
    private List<String> requestData = new ArrayList<>();
    private List<String> errors = new ArrayList<>();
    private Boolean errorDetected = false;
    private final Stack<Integer> stack = new Stack<>();
    private final Stack<Double> stackDouble = new Stack<>();
    private final Stack<Boolean> stackBoolean = new Stack<>();
    private final Stack<Object> stackObject = new Stack<>();

    public FormListener(List<String> requestData, List<Attribute> requestAttribute) {
        this.requestData = requestData;
        this.requestAttribute = requestAttribute;
    }

    @Override
    public void exitProg(FormValidationParser.ProgContext ctx) {
        try {
            Boolean prog = stackBoolean.pop();
            Boolean config = stackBoolean.pop();
            Boolean ifVerification = stackBoolean.pop();
            Boolean result;
            
            if (prog != null) {
                if ( prog == false) {
                }
            }
            errors = new ArrayList<>();

            if (config != null) {
                if (config == false) {
                    for (String s : errors) {
                        System.out.println(s);
                    }
                    errorDetected = true;
                    stackBoolean.push(false);
                }
            }

            if (ifVerification != null) {
                if (ifVerification == false) {
                    for (String s : errors) {
                        System.out.println(s);
                    }
                    errorDetected = true;
                    stackBoolean.push(false);
                }
            }

            stackBoolean.push(!errorDetected);
        } catch (Exception e) {
            e.printStackTrace();
            stackBoolean.push(false);
        }
    }
    
    @Override
    public void exitIfVerification(FormValidationParser.IfVerificationContext ctx) {
        try {
            Boolean conditions = stackBoolean.pop();
            if (conditions == false) {
                stackBoolean.push(true);
            }

            FormValidationParser.ContentContext valueContent = ctx.content();
            Object evaluated = null;
            Boolean verify = true;
            do {
                if (valueContent.contentconditions() != null) {
                    evaluated = stackObject.pop();
                    if ((Boolean) evaluated == false) {
                        verify = false;
                    }
                }

                if (valueContent.config() != null) {
                    evaluated = stackObject.pop();
                    if ((Boolean) evaluated == false) {
                        verify = false;
                    }
                }

                if (valueContent.ifVerification() != null) {
                    evaluated = stackObject.pop();
                    if ((Boolean) evaluated == false) {
                        verify = false;
                    }
                }
                valueContent = valueContent.content();
            } while (valueContent != null);
            stackBoolean.push(verify);
        } catch (Exception e) {
            e.printStackTrace();
            stackBoolean.push(false);
        }
    }

    @Override
    public void exitConditionsNoOperator(FormValidationParser.ConditionsNoOperatorContext ctx) {
        try {
            Boolean conditions = stackBoolean.pop();
            stackBoolean.push(conditions);
        } catch (Exception e) {
            e.printStackTrace();
            stackBoolean.push(false);
        }
    }

    @Override
    public void exitConditionsOperator(FormValidationParser.ConditionsOperatorContext ctx) {
        try {
            Object evaluated = null;
            FormValidationParser.ConditionsContext valueConditions = ctx.conditions();
            do {
                evaluated = stackObject.pop();
                if ((Boolean) evaluated == false) {
                    stackObject.push(false);
                }
            } while (valueConditions != null);

            String operator = ctx.ConditionOperator().toString();
            Object evaluated2 = stackObject.pop();

            switch (operator) {
                case "&&":
                    if ((Boolean) evaluated && (Boolean) evaluated2) {
                        stackObject.push(true);
                    } else {
                        stackObject.push(false);
                    }
                case "||":
                    if ((Boolean) evaluated || (Boolean) evaluated2) {
                        stackObject.push(true);
                    } else {
                        stackObject.push(false);
                    }
                default:
                    stackObject.push(false);

            }
        } catch (Exception e) {
            e.printStackTrace();
            stackObject.push(false);
        }
    }

    @Override
    public void exitValueSizeIs(FormValidationParser.ValueSizeIsContext ctx) {
        String value = requestData.get(Integer.parseInt(ctx.Integer().get(0).toString()) - 1);
        int length = Integer.parseInt(ctx.Integer().get(1).toString());
        try {
            if (value.length() == length) {
                stackObject.push(true);
            }
            errors.add(String.format("The value %s length is not %s\n", value, length));
            stackObject.push(false);
        } catch (Exception e) {
            e.printStackTrace();

            stackObject.push(false);
        }
    }

    @Override
    public void exitValueIsBetween(FormValidationParser.ValueIsBetweenContext ctx) {
        try {
            int value = Integer.parseInt(requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1));
            int between1 = Integer.parseInt(ctx.gap().Integer().get(0).toString());
            int between2 = Integer.parseInt(ctx.gap().Integer().get(1).toString());
            if (value <= between1 && value >= between2) {
                stackObject.push(true);
            }
            errors.add(String.format("The value %d is not between %d and %d\n", value, between1, between2));
            stackObject.push(false);
        } catch (Exception e) {
            e.printStackTrace();
            stackObject.push(false);
        }
    }

    @Override
    public void exitValueIsMandatory(FormValidationParser.ValueIsMandatoryContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            String mandatory = ctx.Mandatory().toString();

            if (mandatory.equalsIgnoreCase("obligatory")) {
                if (value.isBlank()) {
                    errors.add(String.format("You didn't respond to the attribute %s and it was mandatory\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name()));
                    stackObject.push(false);
                }
                stackObject.push(true);
            } else {
                stackObject.push(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            stackObject.push(false);
        }
    }

    @Override
    public void exitValueVerifyLogicOrEqual(FormValidationParser.ValueVerifyLogicOrEqualContext ctx) {
        try {
            double value = Double.parseDouble(requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1));
            int calculatedValue = stack.pop();
            if (calculatedValue == 0.0) {
                System.out.printf("There was an error in calculation\n");
                 stackObject.push(false);
            }
            if (ctx.EqualOrNot() == null && ctx.LogicOperator() != null) {
                String operator = ctx.LogicOperator().toString();
                switch (operator) {
                    case "<":
                        if (value < calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not under the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                         stackObject.push(false);
                    case ">":
                        if (value > calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not over the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                         stackObject.push(false);
                    case "<=":
                        if (value <= calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not under or equal the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                         stackObject.push(false);
                    case ">=":
                        if (value >= calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not over or equal the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                         stackObject.push(false);
                }
            } else if (ctx.EqualOrNot() != null && ctx.LogicOperator() == null) {
                String operator = ctx.EqualOrNot().toString();
                switch (operator) {
                    case "==":
                        if (value == calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not equal to the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                         stackObject.push(false);
                    case "!=":
                        if (value != calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not different than the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                         stackObject.push(false);
                }
            }
            stackObject.push(false);
        } catch (Exception e) {
            e.printStackTrace();

            stackObject.push(false);
        }
    }

    @Override
    public void exitValueVerifyLogicOrEqualConfig(FormValidationParser.ValueVerifyLogicOrEqualConfigContext ctx){
        try {
            double value = Double.parseDouble(requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1));
            double calculatedValue = stackDouble.pop();
            if (calculatedValue == 0.0) {
                System.out.printf("There was an error in calculation\n");
                stackObject.push(false);
            }
            if (ctx.EqualOrNot() == null && ctx.LogicOperator() != null) {
                String operator = ctx.LogicOperator().toString();
                switch (operator) {
                    case "<":
                        if (value < calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not under the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        stackObject.push(false);
                    case ">":
                        if (value > calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not over the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        stackObject.push(false);
                    case "<=":
                        if (value <= calculatedValue) {
                           stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not under or equal the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        stackObject.push(false);
                    case ">=":
                        if (value >= calculatedValue) {
                           stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not over or equal the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        stackObject.push(false);
                }
            } else if (ctx.EqualOrNot() != null && ctx.LogicOperator() == null) {
                String operator = ctx.EqualOrNot().toString();
                switch (operator) {
                    case "==":
                        if (value == calculatedValue) {
                            stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not equal to the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        stackObject.push(false);
                    case "!=":
                        if (value != calculatedValue) {
                           stackObject.push(true);
                        }
                        errors.add(String.format("The value of attribute %s was not different than the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        stackObject.push(false);
                }
            }
            stackObject.push(false);
        } catch (Exception e) {
            e.printStackTrace();

            stackObject.push(false);
        }
    }

    @Override
    public void exitValueState(FormValidationParser.ValueStateContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            String state = ctx.StringState().toString();

            if (state.equalsIgnoreCase("filled")) {
                if (value.isBlank()) {
                    errors.add(String.format("You didn't fill the attribute %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name()));
                    stackObject.push(false);
                }
                 stackObject.push(true);
            } else if (state.equalsIgnoreCase("empty")) {
                if (value.isBlank()) {
                     stackObject.push(true);
                }
                errors.add(String.format("You filled the attribute %s and you shouldn't\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name()));
                stackObject.push(false);
            }
            stackObject.push(false);
        } catch (Exception e) {
            e.printStackTrace();

            stackObject.push(false);
        }
    }

    @Override
    public void exitValueIsEqualString(FormValidationParser.ValueIsEqualStringContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            StringBuilder aux = new StringBuilder();

            FormValidationParser.StringValueContext context = ctx.stringCondition().stringQuoteMarks().stringValue();
            do {
                aux.append(context.String().toString());
                aux.append(" ");
                context = context.stringValue();
            } while (context != null);

            String valueToBeEqual = aux.toString();
            valueToBeEqual = valueToBeEqual.replace("\"", "");

            String operator = ctx.EqualOrNot().toString();

            switch (operator) {
                case "==":
                    if (value.trim().equalsIgnoreCase((valueToBeEqual.trim()))) {
                        stackObject.push(true);
                    }
                    errors.add(String.format("The attribute %s should be %s and wasn't\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), valueToBeEqual));
                    stackObject.push(false);

                case "!=":
                    if (!value.equals(valueToBeEqual)) {
                        stackObject.push(true);
                    }
                    errors.add(String.format("The attribute %s couldn't be %s and was\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), valueToBeEqual));
                    stackObject.push(false);
                default:
                    stackObject.push(false);
            }
        } catch (Exception e) {
            e.printStackTrace();

            stackObject.push(false);
        }
    }

    @Override
    public void exitValueIsBoolean(FormValidationParser.ValueIsBooleanContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            String operator = ctx.EqualOrNot().toString();
            String boolValue = ctx.BoolValue().toString();

            switch (operator) {
                case "==":
                    if (value.equals(boolValue)) {
                        stackObject.push(true);
                    }
                    errors.add(String.format("The attribute %s wasn't %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), boolValue));
                     stackObject.push(false);
                case "!=":
                    if (!value.equals(boolValue)) {
                        stackObject.push(true);
                    }
                    errors.add(String.format("The attribute %s wasn't %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), boolValue));
                    stackObject.push(false);
                default:
                     stackObject.push(false);
            }
        } catch (Exception e) {
            e.printStackTrace();

             stackObject.push(false);
        }
    }

    @Override
    public void exitBooleanIsValue(FormValidationParser.BooleanIsValueContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            String operator = ctx.EqualOrNot().toString();
            String boolValue = ctx.BoolValue().toString();

            switch (operator) {
                case "==":
                    if (value.equals(boolValue)) {
                        stackObject.push(true);
                    }
                    errors.add(String.format("The attribute %s wasn't %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), boolValue));
                     stackObject.push(false);
                case "!=":
                    if (!value.equals(boolValue)) {
                        stackObject.push(true);
                    }
                    errors.add(String.format("The attribute %s wasn't %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), boolValue));
                     stackObject.push(false);
                default:
                     stackObject.push(false);
            }
        } catch (Exception e) {
            e.printStackTrace();

             stackObject.push(false);
        }
    }

    public void exitIntExpression(FormValidationParser.IntExpressionContext ctx) {
        try {
            double result = 0.0;
            String mathOperator = ctx.MathOperator().toString();
            double firstValue = Double.parseDouble(requestData.get(Integer.parseInt(ctx.intValue().Integer().toString())-1));
            if (ctx.intExpression().intExpression() == null) {
                double secondValue = Double.parseDouble(requestData.get(Integer.parseInt(ctx.intExpression().intValue().Integer().toString())-1));
                switch (mathOperator) {
                    case "+":
                        result = firstValue + secondValue;
                        stackDouble.push(result);
                    case "-":
                        result = firstValue - secondValue;
                        stackDouble.push(result);
                    case "*":
                        result = firstValue * secondValue;
                        stackDouble.push(result);
                    case "/":
                        result = firstValue / secondValue;
                        stackDouble.push(result);
                    default:
                        result = 0.0;
                        stackDouble.push(result);
                }
            } else {
                double secondValue = stackDouble.pop();;
                switch (mathOperator) {
                    case "+":
                        result = firstValue + secondValue;
                        stackDouble.push(result);
                    case "-":
                        result = firstValue - secondValue;
                        stackDouble.push(result);
                    case "*":
                        result = firstValue * secondValue;
                        stackDouble.push(result);
                    case "/":
                        result = firstValue / secondValue;
                        stackDouble.push(result);
                    default:
                        result = 0.0;
                        stackDouble.push(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(0.0);
        }
    }
}