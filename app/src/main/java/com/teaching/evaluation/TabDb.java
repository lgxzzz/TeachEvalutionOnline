package com.teaching.evaluation;

public class TabDb {
    public static String[] getTabsTxt(){
        String[] tabs={"课程","评分","我的"};
        return tabs;
    }
    public static int[] getTabsImg(){
        int[] ids={
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
        };
        return ids;
    }
    public static int[] getTabsImgLight(){
        int[] ids={
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
        };
        return ids;
    }
    public static Class[] getFragments(){
        Class[] clz={CourseFragment.class,EvaluationFragment.class,MyFragment.class};
        return clz;
    }
}
