package com.company;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Test {
    public static void main(String args[]) {
        double d = second(1582877126000d);
        String s = "40";
        String[] paramStr = s.split(",");
        Object[] objects0 = {1582877126000d,1582877126000d};
        boolean bbb = isNumeric(objects0[0].toString());
        String s2 = "[40,0,null], 20.0";
        String s3 = "[\"cid_1\",\"cid_2\"],{[\"cid_1\":1],[\"cid_2\":2]}";
        //s3 = "[2,3,4],0";
        s3 = "null,20.0";
        Object feature = new Object();
        s3 = "null";
        s3 = "1.79E+308";
        s = null;
        s = "-2.1";
        double d0 = Double.parseDouble(s.toString());
        boolean b1 = isNumeric(s.toString());

        BigDecimal bigValue = new BigDecimal(s3);
        BigDecimal bigValue2 =  bigValue.multiply(bigValue);
        Double res = bigValue2 .doubleValue();
        if(res.isInfinite()){
            if(res<0){
                res =  -Double.MAX_VALUE;
            }else {
                res =  Double.MAX_VALUE;
            }
        }
        if(res.isNaN()){
            if(res<0){
                res =  -Double.MIN_VALUE;
            }else {
                res =  Double.MIN_VALUE;
            }
        }

        boolean b2 =Number.class.isInstance(s) ;


        //开始匹配
        if(s3.startsWith("[")){
            String feaStr = s3.substring(s3.indexOf("[") + 1 ,s3.indexOf("]"));
            feature = strToList (feaStr);
            if(s3.indexOf("]")+2 < s3.length()){
                String paramString = s3.substring(s3.indexOf("]")+2);
                if(paramString.startsWith("{")){
                    Map map = new HashMap<String,Number>();
                    paramString = paramString.substring(1,paramString.length()-1);
                    map = strToMap(paramString);
                }else if(paramString.startsWith("[")){
                    List list = new ArrayList();
                    list = strToList(paramString);
                }else {
//                mOperatorConfig.paramList = new ArrayList<Object>();
//                for(int i = 1;i< objects.length;i++){
//                    mOperatorConfig.paramList.add(Double.parseDouble(objects[i].toString()));
//                }
                }
            }else {
                //ToastUtil.showToast(getActivity(), "参数不合法");
            }
        }else {
            String feaStr = s3.substring(0,s3.indexOf(","));
            if(feaStr.equals("null")){
                feature = null;
            }
            feature =strToNumber(s3.substring(0,s3.indexOf(",")));
            String paramString = s3.substring(s3.indexOf(",")+1);
            if(paramString.startsWith("{")){
                Map map = new HashMap<String,Number>();
                paramString = paramString.substring(1,paramString.length()-1);
                map = strToMap(paramString);
            }else if(paramString.startsWith("[")){
                List list = new ArrayList();
                list = strToList(paramString);
            }else {
                Object[] objects = paramString.split(",");
//                mOperatorConfig.paramList = new ArrayList<Object>();
//                for(int i = 1;i< objects.length;i++){
//                    mOperatorConfig.paramList.add(Double.parseDouble(objects[i].toString()));
//                }
            }
//            System.out.println("是否是数字？"+Number.class.isInstance(feature));
//            System.out.println("转成double"+((Number)feature).doubleValue());
        }

    }
    public static Number strToNumber(String input){
        return Double.parseDouble(input);
    }
    public static List strToList(String input){
        input = input.replace("\"", "");
        Object[] objects = input.toString().split(",");
        boolean b = String.class.isInstance(objects[0]);
        if(isNumeric(objects[0].toString()) || objects[0].equals("null") ){
            List<Double> doubleList = new ArrayList<Double>();
            for(Object o : objects){
                if(o.equals("null")){
                    doubleList.add(null);
                }else {
                    doubleList.add(Double.parseDouble(o.toString()));
                }
            }
            return doubleList;
        }else {
            return Arrays.asList(objects);
        }
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("-*\\d+(\\.\\d+)?");
        return pattern.matcher(str).matches();
    }
    public static Map strToMap(String paramList){
        Map map = new HashMap<String,Number>();
        List<String> stringList = strToList(paramList);
        for (String string : stringList){
            String key = string.substring(1,string.indexOf(":"));
            Number value = Double.parseDouble(string.substring(string.indexOf(":")+1,string.indexOf("]")));
            map.put(key,value);
        }
        return map;
    }

    public static double year(double timestamp){
        return dateDoubleFormat(timestamp,"y");
    }
    //获取小时
    public static double hour(double timestamp){
        return dateDoubleFormat(timestamp,"H");
    }

    //获取全天第n分钟
    public static double minute(double timestamp){
        return  dateDoubleFormat(timestamp,"m")+hour(timestamp)*60;
    }
    public static double second(double timestamp){
        return  dateDoubleFormat(timestamp,"s")+minute(timestamp)*60+hour(timestamp)*3600;
    }
    private static double dateDoubleFormat(double timestamp,String pattern){
        double ret = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        try {
            ret = Double.parseDouble(dateFormat.format(timestamp));
        } catch (Exception e) {
        }
        return ret;
    }

}

