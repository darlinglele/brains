package com.pwc.hotel;

public class Condition {
    private String type;
    public int[] days;

    public Condition(String type, int[] days) {
        this.type = type;
        this.days = days;
    }

    public static Condition parse(String condition) {
        String type = getType(condition);
        int[] days = getDays(condition);
        return new Condition(type, days);
    }

    private static int[] getDays(String condition) {
        int[] result = new int[7];
        String[] week = new String[]{"mon", "tues", "wed", "thur", "fri", "sat", "sun"};
        String[] days = condition.split(",");
        for (String d : days) {
            for (int i = 0; i < week.length; i++) {
                if (d.contains(week[i])) {
                    result[i]++;
                }
            }
        }
        return result;
    }

    private static String getType(String condition) {
        return condition.substring(0, condition.indexOf(':'));
    }

    public String type() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        Condition con = null;
        if (o instanceof Condition) {
            con = (Condition) o;
        } else {
            throw new ClassCastException();
        }
        return this.type.equals(con.type) && isDaysSame(this, con);
    }

    private boolean isDaysSame(Condition condition, Condition con) {
        for (int i = 0; i < condition.days.length; i++) {
            if (con.days[i] != condition.days[i]) {
                return false;
            }
        }
        return true;
    }
    }
