public class TestMode{

}
//提供了游戏角色接口
interface GameRole{
    public void get();
}
//倩女幽魂：实现游戏角色中的抽象方法
class GetQianNvYouHun implements GameRole{
    public void get(){

    }
}
//逆水寒：实现游戏角色中的抽象方法
class GetNiShuiHan implements GameRole{
    public void get(){

    }
}
//抽象工厂：提供了游戏角色的生成方法
interface AbstractFactory{
    public GameRole newGameRole();
}
//倩女幽魂：实现了游戏角色的生成方法
class GetFactory1 implements AbstractFactory{
    public GameRole newGameRole(){
        return new GetQianNvYouHun();
    }
}
//逆水寒：实现了游戏角色的生成方法
class GetFactory2 implements AbstractFactory{
    public GameRole newGameRole(){
        return new GetNiShuiHan();
    }
}
