package com.zhouruxuan.api.compare;

import com.alibaba.fastjson2.JSONObject;
import entity.Goods;
import entity.LogicRoom;
import entity.RoomGood;
import entity.RoomGoodsResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;

public class CompareTest {
    @Test
    public void testBooleanCmp() {
        CompareBean trueBean = new CompareBean(true);
        CompareBean falseBean = new CompareBean(false);
        List<CompareBean> list = new ArrayList<>();
        list.add(trueBean);
        list.add(falseBean);
        list.add(new CompareBean(null));
        list.sort(Comparator.comparing(CompareBean::getA, Comparator.nullsFirst(Boolean::compare)).reversed());
        System.out.println(list);
    }

    @Test
    public void testArrCmp() {
        List<Integer> A = Arrays.asList(1, 3, 5, 7, 9);
        List<CompareBean> B = Arrays.asList(new CompareBean(3), new CompareBean(9), new CompareBean(1), new CompareBean(7), new CompareBean(5));

        // 使用自定义的Comparator进行排序
        B.sort((o1, o2) -> {
            int index1 = A.indexOf(o1.getNo());
            int index2 = A.indexOf(o2.getNo());
            return Integer.compare(index1, index2);
        });

        System.out.println(B); // 打印排序后的序列B
    }

    @Test
    public void testObjCmp() {
        String js = "{\"roomGoods\":[{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":843619427}],\"roomType\":{\"roomId\":353782463}},{\"goodsList\":[{\"goodsId\":893926037}],\"roomType\":{\"roomId\":353782463}}],\"realRoomId\":10922235},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":938727953},{\"goodsId\":938736132},{\"goodsId\":923145414},{\"goodsId\":928458766},{\"goodsId\":916042346},{\"goodsId\":889961986},{\"goodsId\":853061463},{\"goodsId\":809429478},{\"goodsId\":894612297},{\"goodsId\":887684811},{\"goodsId\":887682887},{\"goodsId\":809435914},{\"goodsId\":809445113},{\"goodsId\":819258346},{\"goodsId\":809350038},{\"goodsId\":809335116},{\"goodsId\":834153071},{\"goodsId\":827260659},{\"goodsId\":880348769},{\"goodsId\":860576009},{\"goodsId\":860412940},{\"goodsId\":809438122},{\"goodsId\":834246951},{\"goodsId\":847407840},{\"goodsId\":929353538},{\"goodsId\":923164091},{\"goodsId\":941385459},{\"goodsId\":871187067},{\"goodsId\":844956972},{\"goodsId\":852887397},{\"goodsId\":812606233},{\"goodsId\":871205910},{\"goodsId\":851472188},{\"goodsId\":851498173}],\"roomType\":{\"roomId\":337433873}},{\"goodsList\":[{\"goodsId\":864665504}],\"roomType\":{\"roomId\":337433873}}],\"realRoomId\":3553155},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":785105738},{\"goodsId\":785118157},{\"goodsId\":785128257},{\"goodsId\":784936516},{\"goodsId\":785129309},{\"goodsId\":785115225},{\"goodsId\":785115228},{\"goodsId\":785091795},{\"goodsId\":785080663},{\"goodsId\":785080662},{\"goodsId\":785091797},{\"goodsId\":785116642},{\"goodsId\":785113185},{\"goodsId\":798982832},{\"goodsId\":785113316},{\"goodsId\":784922873},{\"goodsId\":785113721},{\"goodsId\":784965874},{\"goodsId\":785113716},{\"goodsId\":785117451},{\"goodsId\":785135119},{\"goodsId\":785135115},{\"goodsId\":785118467},{\"goodsId\":785117314},{\"goodsId\":785126022},{\"goodsId\":785118208},{\"goodsId\":785091974},{\"goodsId\":785118085},{\"goodsId\":785091973},{\"goodsId\":785119129},{\"goodsId\":785024152},{\"goodsId\":785044115},{\"goodsId\":785118352},{\"goodsId\":785116055},{\"goodsId\":785126160},{\"goodsId\":785118486},{\"goodsId\":689356873},{\"goodsId\":785126163},{\"goodsId\":784929558},{\"goodsId\":785013291},{\"goodsId\":785128110},{\"goodsId\":784925867},{\"goodsId\":785129002},{\"goodsId\":785115692},{\"goodsId\":785011234},{\"goodsId\":785118503},{\"goodsId\":785117479},{\"goodsId\":785118501},{\"goodsId\":785016249},{\"goodsId\":785128255},{\"goodsId\":785082812},{\"goodsId\":785007293},{\"goodsId\":784997299},{\"goodsId\":785126321},{\"goodsId\":785128242},{\"goodsId\":785119412}],\"roomType\":{\"roomId\":200211507}}],\"realRoomId\":25944982},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":804536089},{\"goodsId\":804526279},{\"goodsId\":688980114}],\"roomType\":{\"roomId\":199175082}}],\"realRoomId\":26012077},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":776417447},{\"goodsId\":776418384},{\"goodsId\":803931667},{\"goodsId\":770037656},{\"goodsId\":804553482},{\"goodsId\":795918351},{\"goodsId\":732292378},{\"goodsId\":770167832}],\"roomType\":{\"roomId\":200211748}},{\"goodsList\":[{\"goodsId\":716580767}],\"roomType\":{\"roomId\":200211748}}],\"realRoomId\":21788018},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":742566674},{\"goodsId\":749750586},{\"goodsId\":747692661},{\"goodsId\":689376128}],\"roomType\":{\"roomId\":200211869}}],\"realRoomId\":19315462},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":770215776},{\"goodsId\":740447434},{\"goodsId\":742687377}],\"roomType\":{\"roomId\":200211975}}],\"realRoomId\":15815870},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":783755215},{\"goodsId\":794326271}],\"roomType\":{\"roomId\":200211636}}],\"realRoomId\":25811938},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":762736076},{\"goodsId\":772199803},{\"goodsId\":689351968},{\"goodsId\":732282635},{\"goodsId\":772213040},{\"goodsId\":732270866}],\"roomType\":{\"roomId\":200211397}}],\"realRoomId\":26010098},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":689390954}],\"roomType\":{\"roomId\":200212053}}],\"realRoomId\":19315503},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":761637944},{\"goodsId\":785049946},{\"goodsId\":749574925}],\"roomType\":{\"roomId\":258667688}},{\"goodsList\":[{\"goodsId\":804558864},{\"goodsId\":706157828},{\"goodsId\":740452217}],\"roomType\":{\"roomId\":198933077}}],\"realRoomId\":26010124},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":843619427}],\"roomType\":{\"roomId\":353782463}},{\"goodsList\":[{\"goodsId\":893926037}],\"roomType\":{\"roomId\":353782463}}],\"realRoomId\":10922235},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":938727953},{\"goodsId\":938736132},{\"goodsId\":923145414},{\"goodsId\":928458766},{\"goodsId\":916042346},{\"goodsId\":889961986},{\"goodsId\":853061463},{\"goodsId\":809429478},{\"goodsId\":894612297},{\"goodsId\":887684811},{\"goodsId\":887682887},{\"goodsId\":809435914},{\"goodsId\":809445113},{\"goodsId\":819258346},{\"goodsId\":809350038},{\"goodsId\":809335116},{\"goodsId\":834153071},{\"goodsId\":827260659},{\"goodsId\":880348769},{\"goodsId\":860576009},{\"goodsId\":860412940},{\"goodsId\":809438122},{\"goodsId\":834246951},{\"goodsId\":847407840},{\"goodsId\":929353538},{\"goodsId\":923164091},{\"goodsId\":941385459},{\"goodsId\":871187067},{\"goodsId\":844956972},{\"goodsId\":852887397},{\"goodsId\":812606233},{\"goodsId\":871205910},{\"goodsId\":851472188},{\"goodsId\":851498173}],\"roomType\":{\"roomId\":337433873}},{\"goodsList\":[{\"goodsId\":864665504}],\"roomType\":{\"roomId\":337433873}}],\"realRoomId\":3553155},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":785105738},{\"goodsId\":785118157},{\"goodsId\":785128257},{\"goodsId\":784936516},{\"goodsId\":785129309},{\"goodsId\":785115225},{\"goodsId\":785115228},{\"goodsId\":785091795},{\"goodsId\":785080663},{\"goodsId\":785080662},{\"goodsId\":785091797},{\"goodsId\":785116642},{\"goodsId\":785113185},{\"goodsId\":798982832},{\"goodsId\":785113316},{\"goodsId\":784922873},{\"goodsId\":785113721},{\"goodsId\":784965874},{\"goodsId\":785113716},{\"goodsId\":785117451},{\"goodsId\":785135119},{\"goodsId\":785135115},{\"goodsId\":785118467},{\"goodsId\":785117314},{\"goodsId\":785126022},{\"goodsId\":785118208},{\"goodsId\":785091974},{\"goodsId\":785118085},{\"goodsId\":785091973},{\"goodsId\":785119129},{\"goodsId\":785024152},{\"goodsId\":785044115},{\"goodsId\":785118352},{\"goodsId\":785116055},{\"goodsId\":785126160},{\"goodsId\":785118486},{\"goodsId\":689356873},{\"goodsId\":785126163},{\"goodsId\":784929558},{\"goodsId\":785013291},{\"goodsId\":785128110},{\"goodsId\":784925867},{\"goodsId\":785129002},{\"goodsId\":785115692},{\"goodsId\":785011234},{\"goodsId\":785118503},{\"goodsId\":785117479},{\"goodsId\":785118501},{\"goodsId\":785016249},{\"goodsId\":785128255},{\"goodsId\":785082812},{\"goodsId\":785007293},{\"goodsId\":784997299},{\"goodsId\":785126321},{\"goodsId\":785128242},{\"goodsId\":785119412}],\"roomType\":{\"roomId\":200211507}}],\"realRoomId\":25944982},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":804536089},{\"goodsId\":804526279},{\"goodsId\":688980114}],\"roomType\":{\"roomId\":199175082}}],\"realRoomId\":26012077},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":776417447},{\"goodsId\":776418384},{\"goodsId\":803931667},{\"goodsId\":770037656},{\"goodsId\":804553482},{\"goodsId\":795918351},{\"goodsId\":732292378},{\"goodsId\":770167832}],\"roomType\":{\"roomId\":200211748}},{\"goodsList\":[{\"goodsId\":716580767}],\"roomType\":{\"roomId\":200211748}}],\"realRoomId\":21788018},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":742566674},{\"goodsId\":749750586},{\"goodsId\":747692661},{\"goodsId\":689376128}],\"roomType\":{\"roomId\":200211869}}],\"realRoomId\":19315462},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":770215776},{\"goodsId\":740447434},{\"goodsId\":742687377}],\"roomType\":{\"roomId\":200211975}}],\"realRoomId\":15815870},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":783755215},{\"goodsId\":794326271}],\"roomType\":{\"roomId\":200211636}}],\"realRoomId\":25811938},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":762736076},{\"goodsId\":772199803},{\"goodsId\":689351968},{\"goodsId\":732282635},{\"goodsId\":772213040},{\"goodsId\":732270866}],\"roomType\":{\"roomId\":200211397}}],\"realRoomId\":26010098},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":689390954}],\"roomType\":{\"roomId\":200212053}}],\"realRoomId\":19315503},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":761637944},{\"goodsId\":785049946},{\"goodsId\":749574925}],\"roomType\":{\"roomId\":258667688}},{\"goodsList\":[{\"goodsId\":804558864},{\"goodsId\":706157828},{\"goodsId\":740452217}],\"roomType\":{\"roomId\":198933077}}],\"realRoomId\":26010124},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":843619427}],\"roomType\":{\"roomId\":353782463}},{\"goodsList\":[{\"goodsId\":893926037}],\"roomType\":{\"roomId\":353782463}}],\"realRoomId\":10922235},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":938727953},{\"goodsId\":938736132},{\"goodsId\":923145414},{\"goodsId\":928458766},{\"goodsId\":916042346},{\"goodsId\":889961986},{\"goodsId\":853061463},{\"goodsId\":809429478},{\"goodsId\":894612297},{\"goodsId\":887684811},{\"goodsId\":887682887},{\"goodsId\":809435914},{\"goodsId\":809445113},{\"goodsId\":819258346},{\"goodsId\":809350038},{\"goodsId\":809335116},{\"goodsId\":834153071},{\"goodsId\":827260659},{\"goodsId\":880348769},{\"goodsId\":860576009},{\"goodsId\":860412940},{\"goodsId\":809438122},{\"goodsId\":834246951},{\"goodsId\":847407840},{\"goodsId\":929353538},{\"goodsId\":923164091},{\"goodsId\":941385459},{\"goodsId\":871187067},{\"goodsId\":844956972},{\"goodsId\":852887397},{\"goodsId\":812606233},{\"goodsId\":871205910},{\"goodsId\":851472188},{\"goodsId\":851498173}],\"roomType\":{\"roomId\":337433873}},{\"goodsList\":[{\"goodsId\":864665504}],\"roomType\":{\"roomId\":337433873}}],\"realRoomId\":3553155},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":785105738},{\"goodsId\":785118157},{\"goodsId\":785128257},{\"goodsId\":784936516},{\"goodsId\":785129309},{\"goodsId\":785115225},{\"goodsId\":785115228},{\"goodsId\":785091795},{\"goodsId\":785080663},{\"goodsId\":785080662},{\"goodsId\":785091797},{\"goodsId\":785116642},{\"goodsId\":785113185},{\"goodsId\":798982832},{\"goodsId\":785113316},{\"goodsId\":784922873},{\"goodsId\":785113721},{\"goodsId\":784965874},{\"goodsId\":785113716},{\"goodsId\":785117451},{\"goodsId\":785135119},{\"goodsId\":785135115},{\"goodsId\":785118467},{\"goodsId\":785117314},{\"goodsId\":785126022},{\"goodsId\":785118208},{\"goodsId\":785091974},{\"goodsId\":785118085},{\"goodsId\":785091973},{\"goodsId\":785119129},{\"goodsId\":785024152},{\"goodsId\":785044115},{\"goodsId\":785118352},{\"goodsId\":785116055},{\"goodsId\":785126160},{\"goodsId\":785118486},{\"goodsId\":689356873},{\"goodsId\":785126163},{\"goodsId\":784929558},{\"goodsId\":785013291},{\"goodsId\":785128110},{\"goodsId\":784925867},{\"goodsId\":785129002},{\"goodsId\":785115692},{\"goodsId\":785011234},{\"goodsId\":785118503},{\"goodsId\":785117479},{\"goodsId\":785118501},{\"goodsId\":785016249},{\"goodsId\":785128255},{\"goodsId\":785082812},{\"goodsId\":785007293},{\"goodsId\":784997299},{\"goodsId\":785126321},{\"goodsId\":785128242},{\"goodsId\":785119412}],\"roomType\":{\"roomId\":200211507}}],\"realRoomId\":25944982},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":804536089},{\"goodsId\":804526279},{\"goodsId\":688980114}],\"roomType\":{\"roomId\":199175082}}],\"realRoomId\":26012077},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":776417447},{\"goodsId\":776418384},{\"goodsId\":803931667},{\"goodsId\":770037656},{\"goodsId\":804553482},{\"goodsId\":795918351},{\"goodsId\":732292378},{\"goodsId\":770167832}],\"roomType\":{\"roomId\":200211748}},{\"goodsList\":[{\"goodsId\":716580767}],\"roomType\":{\"roomId\":200211748}}],\"realRoomId\":21788018},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":742566674},{\"goodsId\":749750586},{\"goodsId\":747692661},{\"goodsId\":689376128}],\"roomType\":{\"roomId\":200211869}}],\"realRoomId\":19315462},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":770215776},{\"goodsId\":740447434},{\"goodsId\":742687377}],\"roomType\":{\"roomId\":200211975}}],\"realRoomId\":15815870},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":783755215},{\"goodsId\":794326271}],\"roomType\":{\"roomId\":200211636}}],\"realRoomId\":25811938},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":762736076},{\"goodsId\":772199803},{\"goodsId\":689351968},{\"goodsId\":732282635},{\"goodsId\":772213040},{\"goodsId\":732270866}],\"roomType\":{\"roomId\":200211397}}],\"realRoomId\":26010098},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":689390954}],\"roomType\":{\"roomId\":200212053}}],\"realRoomId\":19315503},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":761637944},{\"goodsId\":785049946},{\"goodsId\":749574925}],\"roomType\":{\"roomId\":258667688}},{\"goodsList\":[{\"goodsId\":804558864},{\"goodsId\":706157828},{\"goodsId\":740452217}],\"roomType\":{\"roomId\":198933077}}],\"realRoomId\":26010124},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":843619427}],\"roomType\":{\"roomId\":353782463}},{\"goodsList\":[{\"goodsId\":893926037}],\"roomType\":{\"roomId\":353782463}}],\"realRoomId\":10922235},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":938727953},{\"goodsId\":938736132},{\"goodsId\":923145414},{\"goodsId\":928458766},{\"goodsId\":916042346},{\"goodsId\":889961986},{\"goodsId\":853061463},{\"goodsId\":809429478},{\"goodsId\":894612297},{\"goodsId\":887684811},{\"goodsId\":887682887},{\"goodsId\":809435914},{\"goodsId\":809445113},{\"goodsId\":819258346},{\"goodsId\":809350038},{\"goodsId\":809335116},{\"goodsId\":834153071},{\"goodsId\":827260659},{\"goodsId\":880348769},{\"goodsId\":860576009},{\"goodsId\":860412940},{\"goodsId\":809438122},{\"goodsId\":834246951},{\"goodsId\":847407840},{\"goodsId\":929353538},{\"goodsId\":923164091},{\"goodsId\":941385459},{\"goodsId\":871187067},{\"goodsId\":844956972},{\"goodsId\":852887397},{\"goodsId\":812606233},{\"goodsId\":871205910},{\"goodsId\":851472188},{\"goodsId\":851498173}],\"roomType\":{\"roomId\":337433873}},{\"goodsList\":[{\"goodsId\":864665504}],\"roomType\":{\"roomId\":337433873}}],\"realRoomId\":3553155},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":785105738},{\"goodsId\":785118157},{\"goodsId\":785128257},{\"goodsId\":784936516},{\"goodsId\":785129309},{\"goodsId\":785115225},{\"goodsId\":785115228},{\"goodsId\":785091795},{\"goodsId\":785080663},{\"goodsId\":785080662},{\"goodsId\":785091797},{\"goodsId\":785116642},{\"goodsId\":785113185},{\"goodsId\":798982832},{\"goodsId\":785113316},{\"goodsId\":784922873},{\"goodsId\":785113721},{\"goodsId\":784965874},{\"goodsId\":785113716},{\"goodsId\":785117451},{\"goodsId\":785135119},{\"goodsId\":785135115},{\"goodsId\":785118467},{\"goodsId\":785117314},{\"goodsId\":785126022},{\"goodsId\":785118208},{\"goodsId\":785091974},{\"goodsId\":785118085},{\"goodsId\":785091973},{\"goodsId\":785119129},{\"goodsId\":785024152},{\"goodsId\":785044115},{\"goodsId\":785118352},{\"goodsId\":785116055},{\"goodsId\":785126160},{\"goodsId\":785118486},{\"goodsId\":689356873},{\"goodsId\":785126163},{\"goodsId\":784929558},{\"goodsId\":785013291},{\"goodsId\":785128110},{\"goodsId\":784925867},{\"goodsId\":785129002},{\"goodsId\":785115692},{\"goodsId\":785011234},{\"goodsId\":785118503},{\"goodsId\":785117479},{\"goodsId\":785118501},{\"goodsId\":785016249},{\"goodsId\":785128255},{\"goodsId\":785082812},{\"goodsId\":785007293},{\"goodsId\":784997299},{\"goodsId\":785126321},{\"goodsId\":785128242},{\"goodsId\":785119412}],\"roomType\":{\"roomId\":200211507}}],\"realRoomId\":25944982},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":804536089},{\"goodsId\":804526279},{\"goodsId\":688980114}],\"roomType\":{\"roomId\":199175082}}],\"realRoomId\":26012077},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":776417447},{\"goodsId\":776418384},{\"goodsId\":803931667},{\"goodsId\":770037656},{\"goodsId\":804553482},{\"goodsId\":795918351},{\"goodsId\":732292378},{\"goodsId\":770167832}],\"roomType\":{\"roomId\":200211748}},{\"goodsList\":[{\"goodsId\":716580767}],\"roomType\":{\"roomId\":200211748}}],\"realRoomId\":21788018},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":742566674},{\"goodsId\":749750586},{\"goodsId\":747692661},{\"goodsId\":689376128}],\"roomType\":{\"roomId\":200211869}}],\"realRoomId\":19315462},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":770215776},{\"goodsId\":740447434},{\"goodsId\":742687377}],\"roomType\":{\"roomId\":200211975}}],\"realRoomId\":15815870},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":783755215},{\"goodsId\":794326271}],\"roomType\":{\"roomId\":200211636}}],\"realRoomId\":25811938},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":762736076},{\"goodsId\":772199803},{\"goodsId\":689351968},{\"goodsId\":732282635},{\"goodsId\":772213040},{\"goodsId\":732270866}],\"roomType\":{\"roomId\":200211397}}],\"realRoomId\":26010098},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":689390954}],\"roomType\":{\"roomId\":200212053}}],\"realRoomId\":19315503},{\"logicRoomRelations\":[{\"goodsList\":[{\"goodsId\":761637944},{\"goodsId\":785049946},{\"goodsId\":749574925}],\"roomType\":{\"roomId\":258667688}},{\"goodsList\":[{\"goodsId\":804558864},{\"goodsId\":706157828},{\"goodsId\":740452217}],\"roomType\":{\"roomId\":198933077}}],\"realRoomId\":26010124}]}\n";
        RoomGoodsResult roomGoodsResult = JSONObject.parseObject(js, RoomGoodsResult.class);
        RoomGoodsResult dbSort = new RoomGoodsResult();
    }

    /**
     * 将roomGoodsResult中的物理房型、逻辑房型、产品按照dbSort的顺序排序
     *
     * @param roomGoodsResult 未排序的信息
     * @param dbSort          存储着最终的排序顺序
     */
    public void sortRoomGoods(RoomGoodsResult roomGoodsResult, RoomGoodsResult dbSort) {
        // 先将物理房型排序
        roomGoodsResult.getRoomGoods().sort((r1, r2) -> getIndexInRoomGoods(dbSort.getRoomGoods(), r1.getRealRoomId()) - getIndexInRoomGoods(dbSort.getRoomGoods(), r2.getRealRoomId()));
        for (int i = 0; i < roomGoodsResult.getRoomGoods().size(); i++) {
            // 存储着逻辑房型和产品的排序顺序
            RoomGood roomGoodSort = dbSort.getRoomGoods().get(i);
            // 需要被排序的物理房型
            RoomGood roomGood = roomGoodsResult.getRoomGoods().get(i);

            roomGood.getLogicRoomRelations().sort((l1, l2) -> getIndexInLogicRooms(roomGoodSort.getLogicRoomRelations(), l1.getRoomType().getRoomId()) - getIndexInLogicRooms(roomGoodSort.getLogicRoomRelations(), l2.getRoomType().getRoomId()));

            for (int j = 0; j < roomGood.getLogicRoomRelations().size(); j++) {
                // 存储着产品的排序顺序
                LogicRoom logicRoomSort = roomGoodSort.getLogicRoomRelations().get(j);
                // 需要被排序的逻辑房型
                LogicRoom logicRoom = roomGood.getLogicRoomRelations().get(j);

                logicRoom.getGoodsList().sort((g1, g2) -> getIndexInGoods(logicRoomSort.getGoodsList(), g1.getGoodsId()) - getIndexInGoods(logicRoomSort.getGoodsList(), g2.getGoodsId()));
            }
        }
    }

    private int getIndexInGoods(List<Goods> goodsList, Long goodsId) {
        for (int i = 0; i < goodsList.size(); i++) {
            if (goodsList.get(i).getGoodsId().equals(goodsId)) {
                return i;
            }
        }
        return -1;
    }

    private int getIndexInLogicRooms(List<LogicRoom> logicRooms, Long roomId) {
        for (int i = 0; i < logicRooms.size(); i++) {
            if (logicRooms.get(i).getRoomType().getRoomId().equals(roomId)) {
                return i;
            }
        }
        return -1;
    }

    private int getIndexInRoomGoods(List<RoomGood> roomGoods, Long realRoomId) {
        for (int i = 0; i < roomGoods.size(); i++) {
            if (roomGoods.get(i).getRealRoomId().equals(realRoomId)) {
                return i;
            }
        }
        return -1;
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class CompareBean {
    Boolean a;
    int no;

    public CompareBean(Boolean a) {
        this.a = a;
    }

    public CompareBean(int no) {
        this.no = no;
    }
}