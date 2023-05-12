package org.cyclic.action.batch.config.writer.sql;

import static org.cyclic.action.batch.util.Constants.CARRYOVER;
import static org.cyclic.action.batch.util.Constants.MANAGER;
import static org.cyclic.action.batch.util.Constants.NAME;

public interface PositionSQL {
    String INSERT = "INSERT";
    String INTO = " INTO ";
    String VALUES = " VALUES ";
    String ON = "ON";
    String CONFLICT = " CONFLICT ";
    String CONSTRAINT = " CONSTRAINT ";
    String DO = " DO ";
    String UPDATE = "UPDATE ";
    String SET = "SET ";
    String COMMA_SPACE = ", ";


    String POSITION_TABLE = "position";
    String POSITION_CONSTRAINT = "position_store_action_code_action_type_action_start_date_ac_key";
    String NAME_COLUMN = "name";
    String STORE_COLUMN = "store";
    String MANAGER_COLUMN = "manager";
    String CARRYOVER_COLUMN = "carryover";
    String ACTION_CODE_COLUMN = "action_code";
    String STORE_FORMAT_COLUMN = "store_format";
    String THIRD_GROUP_COLUMN = "third_group";
    String ACTION_TYPE_COLUMN = "action_type";
    String ACTION_START_DATE_COLUMN = "action_start_date";
    String ACTION_END_DATE_COLUMN = "action_end_date";
    String BEFORE_ACTION_START_DATE_COLUMN = "before_action_start_date";
    String BEFORE_ACTION_END_DATE_COLUMN = "before_action_end_date";
    String ACTION_AVERAGE_SALES_COLUMN = "action_average_sales";
    String BEFORE_ACTION_AVERAGE_SALES_COLUMN = "before_action_average_sales";
    String ACTUAL_ACTION_AVERAGE_SALES_COLUMN = "actual_average_sales";
    String ALGORITHM_COLUMN = "algorithm";
    String EXCLUDED = " = excluded.";


    String INSERT_POSITION = INSERT + INTO + POSITION_TABLE + VALUES + "("
            + ":" + NAME_COLUMN + COMMA_SPACE
            + ":" + STORE_COLUMN + COMMA_SPACE
            + ":" + MANAGER_COLUMN + COMMA_SPACE
            + ":" + CARRYOVER_COLUMN + COMMA_SPACE
            + ":" + "actionCode" + COMMA_SPACE
            + ":" + "storeFormat" + COMMA_SPACE
            + ":" + "thirdGroup" + COMMA_SPACE
            + ":" + "actionType" + COMMA_SPACE
            + ":" + "actionStartDate" + COMMA_SPACE
            + ":" + "actionEndDate" + COMMA_SPACE
            + ":" + "beforeActionStartDate" + COMMA_SPACE
            + ":" + "beforeActionEndDate" + COMMA_SPACE
            + ":" + "actionAverageSales" + COMMA_SPACE
            + ":" + "beforeActionAverageSales" + COMMA_SPACE
            + ":" + "actualAverageSales" + COMMA_SPACE
            + ":" + ALGORITHM_COLUMN + ") " ;
    String ON_CONFLICT_POSITION = ON + CONFLICT + ON + CONSTRAINT + POSITION_CONSTRAINT + DO + UPDATE + SET
            + NAME_COLUMN + EXCLUDED + NAME_COLUMN + COMMA_SPACE
            + MANAGER_COLUMN + EXCLUDED + MANAGER_COLUMN + COMMA_SPACE
            + CARRYOVER_COLUMN + EXCLUDED + CARRYOVER_COLUMN + COMMA_SPACE
            + STORE_FORMAT_COLUMN + EXCLUDED + STORE_FORMAT_COLUMN + COMMA_SPACE
            + THIRD_GROUP_COLUMN + EXCLUDED + THIRD_GROUP_COLUMN + COMMA_SPACE
            + BEFORE_ACTION_START_DATE_COLUMN + EXCLUDED + BEFORE_ACTION_START_DATE_COLUMN + COMMA_SPACE
            + BEFORE_ACTION_END_DATE_COLUMN + EXCLUDED + BEFORE_ACTION_END_DATE_COLUMN + COMMA_SPACE
            + ACTION_AVERAGE_SALES_COLUMN + EXCLUDED + ACTION_AVERAGE_SALES_COLUMN + COMMA_SPACE
            + BEFORE_ACTION_AVERAGE_SALES_COLUMN + EXCLUDED + BEFORE_ACTION_AVERAGE_SALES_COLUMN + COMMA_SPACE
            + ACTUAL_ACTION_AVERAGE_SALES_COLUMN + EXCLUDED + ACTUAL_ACTION_AVERAGE_SALES_COLUMN + COMMA_SPACE
            + ALGORITHM_COLUMN + EXCLUDED + ALGORITHM_COLUMN + ";";

    String POSITION_QUERY = INSERT_POSITION + ON_CONFLICT_POSITION;
}
