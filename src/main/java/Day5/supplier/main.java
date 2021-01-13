package Day5.supplier;

import java.util.List;

public class main {
    public static void main(String[] args) {
        MosaicCreator mosaicCreator = new MosaicCreator();

        Mosaic mosaic1 = mosaicCreator.create(() -> new Tile("Flower"));
        Mosaic mosaic2 = mosaicCreator.create(() -> new Tile("Star"));

        // List<Tile> star = mosaic2.getTiles();
        List<Tile> flowers = mosaic1.getTiles();
        for (Tile tile : flowers) {
            System.out.println(tile.getType());
        }

        Tile tile = new Tile("123");
        Tile cpytile = tile;

        tile.setType("222");

        System.out.println(tile.getType());
        System.out.println(cpytile.getType());
        System.out.println(tile == cpytile);
    }
}
