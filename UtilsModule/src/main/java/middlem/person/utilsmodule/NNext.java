package middlem.person.utilsmodule;


import android.support.annotation.Nullable;

/***********************************************
 * <P> desc:
 * <P> Author: gongTong
 * <P> Date: 2018/5/2 16:05
 ***********************************************/

public class NNext {

    static final int[] array={1,3,4,5,7,9,10,11,12,13,15,18,19,20};
    private static final int num=10;
     public static void main(String [] args){


        check();
    }

    private static void check() {
        int index = 0; // 检索的时候
        int start = 0;  //用start和end两个索引控制它的查询范围
        int length=array.length;
        int end = length - 1;
        int count = 0;
        for (int i = 0; i < length; i++) {
            index=(start+end)/2;


            if (num-array[index]<-1){
                System.out.println("拿出中间,大于值"+array[index]);
                start=index;
            }
            if (num-array[index]>1){
                System.out.println("拿出中间,小于值" + array[index]);
                start=index;
            }
            if (num -array[index]==1||num-array[index]==-1|| num-array[index]==0){
                System.out.println("拿出中间,等于值" + array[index]);
                start=index;
            }
        }
    }
}
