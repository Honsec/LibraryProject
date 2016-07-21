package genius.baselib.bus;

import de.greenrobot.event.EventBus;

/**
 * Created by Hongsec on 2016-04-17.
 * email : piaohongshi0506@gmail.com
 * QQ: 251520264
 */
public class BusTool {


    public static void sendBus(BusBuilder busBuilder){
        EventBus.getDefault().post(busBuilder);
    }

    /**
     *
     * @param busBuilder  버스 데이터 정보 타깃등
     * @param bustype   현재 함수의 type
     * @param simplename   대상 클라스 네임
     * @return   true이면 액션 더 안함 , false이면 해도됨
     */
    public static  boolean onEventBusFilter(BusBuilder busBuilder, BusBuilder.BUSTYPE bustype,String simplename){

        // 대상이 전체혹은 자신이 맞을 경우 계속하게 함
        if(busBuilder.getBustype()==bustype){
            //it's to  this class
            if(busBuilder.isTarget_all()||busBuilder.getTarget_name().contains(simplename)){
                //액션 수행가능,
                return false;
            }

        }

        //부합되지않아 아래 액션 안함
        return  true;
    }
}
