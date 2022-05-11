package taejoong.chapter6.item34;

public class Test {
    public static void main(String[] args) {
        System.out.println(Planet.MERCURY.mass());
        System.out.println(Planet.MERCURY.radius());
        System.out.println(Planet.MERCURY.surfaceGravity());
        System.out.println(Planet.MERCURY.surfaceWeight(Planet.MERCURY.mass()));
    }

    enum Planet{
        MERCURY(3.302e+23, 2.439e6), EARTH(5.975e+24,6.378e6);

        private final double mass;
        private final double radius;
        private final double surfaceGravity;

        private static final double G = 6.67300E-11;

        Planet(double mass, double radius){
            this.mass = mass;
            this.radius = radius;
            this.surfaceGravity = G*mass/(radius*radius);
        }

        public double mass(){return mass;}
        public double radius(){return radius;}
        public double surfaceGravity(){return surfaceGravity;}

        public double surfaceWeight(double mass){
            return mass*surfaceGravity;
        }
    }
}
