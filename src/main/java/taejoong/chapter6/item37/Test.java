package taejoong.chapter6.item37;

public class Test {
    public static void main(String[] args) {
        Phase.Transition transition1 = Phase.Transition.from(Phase.GAS, Phase.LIQUID);
        Phase.Transition transition2 = Phase.Transition.from(Phase.SOLID, Phase.GAS);
        Phase.Transition transition3 = Phase.Transition.from(Phase.PLASMA, Phase.GAS);
        System.out.println("transition1 = " + transition1);
        System.out.println("transition2 = " + transition2);
        System.out.println("transition3 = " + transition3);
    }
}
