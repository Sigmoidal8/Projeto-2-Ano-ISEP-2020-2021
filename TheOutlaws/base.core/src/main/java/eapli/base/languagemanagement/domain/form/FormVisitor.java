package eapli.base.languagemanagement.domain.form;

import eapli.base.servicemanagement.domain.Attribute;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Raúl Coelho
 */
public class FormVisitor extends FormValidationBaseVisitor {

    private List<Attribute> requestAttribute = new ArrayList<>();
    private List<String> requestData = new ArrayList<>();
    private List<String> errors = new ArrayList<>();
    private Boolean errorDetected = false;

    public FormVisitor(List<String> requestData, List<Attribute> requestAttribute) {
        this.requestData = requestData;
        this.requestAttribute = requestAttribute;
    }

    public Boolean visitProg(FormValidationParser.ProgContext ctx) {
        try {
            if (ctx.prog() != null) {
                if ((Boolean) this.visit(ctx.prog()) == false) {
                }
            }
            errors = new ArrayList<>();

            if (ctx.config() != null) {
                if ((Boolean) this.visit(ctx.config()) == false) {
                    for (String s : errors) {
                        System.out.println(s);
                    }
                    errorDetected = true;
                    return false;
                }
            }

            if (ctx.ifVerification() != null) {
                if ((Boolean) this.visit(ctx.ifVerification()) == false) {
                    for (String s : errors) {
                        System.out.println(s);
                    }
                    errorDetected = true;
                    return false;
                }
            }

            return !errorDetected;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean visitIfVerification(FormValidationParser.IfVerificationContext ctx) {
        try {
            if ((Boolean) this.visit(ctx.conditions()) == false) {
                return true;
            }

            FormValidationParser.ContentContext valueContent = ctx.content();
            Object evaluated = null;
            Boolean verify = true;
            do {
                if (valueContent.contentconditions() != null) {
                    evaluated = this.visit(valueContent.contentconditions().conditions());
                    if ((Boolean) evaluated == false) {
                        verify = false;
                    }
                }

                if (valueContent.config() != null) {
                    evaluated = this.visit(valueContent.config());
                    if ((Boolean) evaluated == false) {
                        verify = false;
                    }
                }

                if (valueContent.ifVerification() != null) {
                    evaluated = this.visit(valueContent.ifVerification());
                    if ((Boolean) evaluated == false) {
                        verify = false;
                    }
                }
                valueContent = valueContent.content();
            } while (valueContent != null);
            return verify;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitConditionsNoOperator(FormValidationParser.ConditionsNoOperatorContext ctx) {
        try {
            return (Boolean) this.visit(ctx.condition());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean visitConditionsOperator(FormValidationParser.ConditionsOperatorContext ctx) {
        try {
            Object evaluated = null;
            FormValidationParser.ConditionsContext valueConditions = ctx.conditions();
            do {
                evaluated = this.visit(valueConditions);
                if ((Boolean) evaluated == false) {
                    return false;
                }
            } while (valueConditions != null);

            String operator = ctx.ConditionOperator().toString();
            Object evaluated2 = this.visit(ctx.condition());

            switch (operator) {
                case "&&":
                    if ((Boolean) evaluated && (Boolean) evaluated2) {
                        return true;
                    } else {
                        return false;
                    }
                case "||":
                    if ((Boolean) evaluated || (Boolean) evaluated2) {
                        return true;
                    } else {
                        return false;
                    }
                default:
                    return false;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitValueSizeIs(FormValidationParser.ValueSizeIsContext ctx) {
        String value = requestData.get(Integer.parseInt(ctx.Integer().get(0).toString()) - 1);
        int length = Integer.parseInt(ctx.Integer().get(1).toString());
        try {
            if (value.length() == length) {
                return true;
            }
            errors.add(String.format("The value %s length is not %s\n", value, length));
            return false;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValueIsBetween(FormValidationParser.ValueIsBetweenContext ctx) {
        try {
            int value = Integer.parseInt(requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1));
            int between1 = Integer.parseInt(ctx.gap().Integer().get(0).toString());
            int between2 = Integer.parseInt(ctx.gap().Integer().get(1).toString());
            if (value <= between1 && value >= between2) {
                return true;
            }
            errors.add(String.format("The value %d is not between %d and %d\n", value, between1, between2));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitValueIsMandatory(FormValidationParser.ValueIsMandatoryContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            String mandatory = ctx.Mandatory().toString();

            if (mandatory.equalsIgnoreCase("obligatory")) {
                if (value.isBlank()) {
                    errors.add(String.format("You didn't respond to the attribute %s and it was mandatory\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name()));
                    return false;
                }
                return true;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitValueVerifyLogicOrEqual(FormValidationParser.ValueVerifyLogicOrEqualContext ctx) {
        try {
            double value = Double.parseDouble(requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1));
            double calculatedValue = visitIntExpression(ctx.intExpression());
            if (calculatedValue == 0.0) {
                System.out.printf("There was an error in calculation\n");
                return false;
            }
            if (ctx.EqualOrNot() == null && ctx.LogicOperator() != null) {
                String operator = ctx.LogicOperator().toString();
                switch (operator) {
                    case "<":
                        if (value < calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not under the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                    case ">":
                        if (value > calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not over the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                    case "<=":
                        if (value <= calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not under or equal the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                    case ">=":
                        if (value >= calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not over or equal the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                }
            } else if (ctx.EqualOrNot() != null && ctx.LogicOperator() == null) {
                String operator = ctx.EqualOrNot().toString();
                switch (operator) {
                    case "==":
                        if (value == calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not equal to the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                    case "!=":
                        if (value != calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not different than the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValueVerifyLogicOrEqualConfig(FormValidationParser.ValueVerifyLogicOrEqualConfigContext ctx){
        try {
            double value = Double.parseDouble(requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1));
            double calculatedValue = visitIntExpression(ctx.intExpression());
            if (calculatedValue == 0.0) {
                System.out.printf("There was an error in calculation\n");
                return false;
            }
            if (ctx.EqualOrNot() == null && ctx.LogicOperator() != null) {
                String operator = ctx.LogicOperator().toString();
                switch (operator) {
                    case "<":
                        if (value < calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not under the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                    case ">":
                        if (value > calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not over the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                    case "<=":
                        if (value <= calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not under or equal the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                    case ">=":
                        if (value >= calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not over or equal the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                }
            } else if (ctx.EqualOrNot() != null && ctx.LogicOperator() == null) {
                String operator = ctx.EqualOrNot().toString();
                switch (operator) {
                    case "==":
                        if (value == calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not equal to the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                    case "!=":
                        if (value != calculatedValue) {
                            return true;
                        }
                        errors.add(String.format("The value of attribute %s was not different than the calculated value %.2f\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), calculatedValue));
                        return false;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValueState(FormValidationParser.ValueStateContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            String state = ctx.StringState().toString();

            if (state.equalsIgnoreCase("filled")) {
                if (value.isBlank()) {
                    errors.add(String.format("You didn't fill the attribute %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name()));
                    return false;
                }
                return true;
            } else if (state.equalsIgnoreCase("empty")) {
                if (value.isBlank()) {
                    return true;
                }
                errors.add(String.format("You filled the attribute %s and you shouldn't\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name()));
                return false;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValueIsEqualString(FormValidationParser.ValueIsEqualStringContext ctx) {
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
                        return true;
                    }
                    errors.add(String.format("The attribute %s should be %s and wasn't\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), valueToBeEqual));
                    return false;

                case "!=":
                    if (!value.equals(valueToBeEqual)) {
                        return true;
                    }
                    errors.add(String.format("The attribute %s couldn't be %s and was\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), valueToBeEqual));
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValueIsBoolean(FormValidationParser.ValueIsBooleanContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            String operator = ctx.EqualOrNot().toString();
            String boolValue = ctx.BoolValue().toString();

            switch (operator) {
                case "==":
                    if (value.equals(boolValue)) {
                        return true;
                    }
                    errors.add(String.format("The attribute %s wasn't %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), boolValue));
                    return false;
                case "!=":
                    if (!value.equals(boolValue)) {
                        return true;
                    }
                    errors.add(String.format("The attribute %s wasn't %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), boolValue));
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitBooleanIsValue(FormValidationParser.BooleanIsValueContext ctx) {
        try {
            String value = requestData.get(Integer.parseInt(ctx.Integer().toString()) - 1);
            String operator = ctx.EqualOrNot().toString();
            String boolValue = ctx.BoolValue().toString();

            switch (operator) {
                case "==":
                    if (value.equals(boolValue)) {
                        return true;
                    }
                    errors.add(String.format("The attribute %s wasn't %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), boolValue));
                    return false;
                case "!=":
                    if (!value.equals(boolValue)) {
                        return true;
                    }
                    errors.add(String.format("The attribute %s wasn't %s\n", requestAttribute.get(Integer.parseInt(ctx.Integer().toString()) - 1).name(), boolValue));
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public Double visitIntExpression(FormValidationParser.IntExpressionContext ctx) {
        try {
            String mathOperator = ctx.MathOperator().toString();
            double firstValue = Double.parseDouble(requestData.get(Integer.parseInt(ctx.intValue().Integer().toString())-1));
            if (ctx.intExpression().intExpression() == null) {
                double secondValue = Double.parseDouble(requestData.get(Integer.parseInt(ctx.intExpression().intValue().Integer().toString())-1));
                switch (mathOperator) {
                    case "+":
                        return firstValue + secondValue;
                    case "-":
                        return firstValue - secondValue;
                    case "*":
                        return firstValue * secondValue;
                    case "/":
                        return firstValue / secondValue;
                    default:
                        return 0.0;
                }
            } else {
                double secondValue = visitIntExpression(ctx.intExpression());
                switch (mathOperator) {
                    case "+":
                        return firstValue + secondValue;
                    case "-":
                        return firstValue - secondValue;
                    case "*":
                        return firstValue * secondValue;
                    case "/":
                        return firstValue / secondValue;
                    default:
                        return 0.0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            return 0.0;
        }
    }
}
