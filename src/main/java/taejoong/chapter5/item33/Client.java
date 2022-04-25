package taejoong.chapter5.item33;

public class Client {
    public static void main(String[] args) {
        Favorites f = new Favorites();

        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 1);
        f.putFavorite(Class.class, Favorites.class);

        String favorite = f.getFavorite(String.class);
        Integer favorite1 = f.getFavorite(Integer.class);
        Class favorite2 = f.getFavorite(Class.class);

        System.out.printf("%s %x %s%n", favorite, favorite1, favorite2);
    }
}
