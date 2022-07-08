package BananaFructa.thah;

public enum Climates {

    HOT("hot","gui/hot.png",20000,true),
    WARM("warm","gui/warm.png",10000,false),
    TEMPERATE("temperate","gui/temperate.png",0,true),
    SNOWY("snowy","gui/snowy.png",-10000,false),
    COLD("cold","gui/cold.png",-20000,true);

    public String name;
    public String resourceLocation;
    public int z;
    public boolean enabled;
    // desc
    public int dif = 0;
    public String desc = "Some description.";

    Climates(String name,String resourceLocation,int z,boolean enabled) {
        this.name = name;
        this.resourceLocation = resourceLocation;
        this.z = z;
        this.enabled = enabled;
    }


}
