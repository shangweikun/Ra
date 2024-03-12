package org.example;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main1 {

    public static void main(String[] args) throws JSQLParserException {

        String sqlStr = "select 1 from dual where a<=b";

        PlainSelect select = (PlainSelect) CCJSqlParserUtil.parse(sqlStr);

        SelectItem selectItem =
                select.getSelectItems().get(0);

        Table table = (Table) select.getFromItem();

        Object obj = select.getWhere();

        if (obj instanceof MinorThanEquals) {
            System.out.println(obj);
        }
    }
}