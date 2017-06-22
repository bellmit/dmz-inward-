package com.dmz.basic.exception;

import com.dmz.service.exceptions.BasicException;

/**
 * Created by dmz on 2016/8/19.
 */
public class DBException {

    public static class DBServerException extends BasicException {
        private static final long serialVersionUID = 7117830162896955116L;

        public DBServerException(String msg) {
            super(msg);
        }

        public DBServerException(String msg, String jsonContext) {
            super(msg, jsonContext);
        }

        public DBServerException(String msg, Throwable ex) {
            super(msg, ex);
        }

        public DBServerException(String msg, Throwable ex, String jsonContext) {
            super(msg, ex, jsonContext);
        }
    }

    //  SQL Execution Error
    public static class BadExecution extends BasicException {
        private static final long serialVersionUID = 3740554241091348191L;

        public BadExecution(String msg) {
            super(msg);
        }

        public BadExecution(String msg, String jsonContext) {
            super(msg, jsonContext);
        }

        public BadExecution(String msg, Throwable ex) {
            super(msg, ex);
        }

        public BadExecution(String msg, Throwable ex, String jsonContext) {
            super(msg, ex, jsonContext);
        }
    }

    //  No data exist  Where we expect at least one row
    public static class EmptyData extends BasicException {
        private static final long serialVersionUID = 2300389301254432906L;

        public EmptyData(String msg) {
            super(msg);
        }

        public EmptyData(String msg, String jsonContext) {
            super(msg, jsonContext);
        }

        public EmptyData(String msg, Throwable ex) {
            super(msg, ex);
        }

        public EmptyData(String msg, Throwable ex, String jsonContext) {
            super(msg, ex, jsonContext);
        }

    }

    //  More than one row data , where we expect only one row
    public static class MultipleData extends BasicException {
        private static final long serialVersionUID = -2315568577737755903L;

        public MultipleData(String msg) {
            super(msg);
        }

        public MultipleData(String msg, String jsonContext) {
            super(msg, jsonContext);
        }

        public MultipleData(String msg, Throwable ex) {
            super(msg, ex);
        }

        public MultipleData(String msg, Throwable ex, String jsonContext) {
            super(msg, ex, jsonContext);
        }
    }

    //  Invalid Params
    public static class InvalidParams extends BasicException {
        private static final long serialVersionUID = -662446490959478882L;

        public InvalidParams(String msg) {
            super(msg);
        }

        public InvalidParams(String msg, String jsonContext) {
            super(msg, jsonContext);
        }

        public InvalidParams(String msg, Throwable ex) {
            super(msg, ex);
        }

        public InvalidParams(String msg, Throwable ex, String jsonContext) {
            super(msg, ex, jsonContext);
        }
    }

    public static class NoReaction extends BasicException {
        public NoReaction(String msg) {
            super(msg);
        }

        public NoReaction(String msg, String jsonContext) {
            super(msg, jsonContext);
        }

        public NoReaction(String msg, Throwable ex) {
            super(msg, ex);
        }

        public NoReaction(String msg, Throwable ex, String jsonContext) {
            super(msg, ex, jsonContext);
        }

    }


}
