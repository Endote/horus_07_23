import java.util.*;

interface Structure {
    // zwraca dowolny element o podanym kolorze
    Optional<Block> findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List<Block> findBlocksByMaterial(String material);

    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall() {}

    public Wall(List<Block> blocks){
        this.blocks = blocks;
    }

    public int count(){
        int sum = 0;

    for (Block b : blocks){
        if(b instanceof CompositeBlock){
            List<Block> tmp = ((CompositeBlock) b).getBlocks();
            int summ = tmp.size();

            sum += summ;
        } else sum += 1;
    }

        return sum;
    }

    public Optional<Block> findBlockByColor(String color){
        for (Block b : blocks){
            String c = b.getColor();
            if ( c.equals(color) ) {
                return Optional.ofNullable(b);
            }
        }
        return Optional.empty();
    }

    public List<Block> findBlocksByMaterial(String material){
        ArrayList<Block> result = new ArrayList<>();
        for (Block b : blocks){
            if( b.getMaterial().equals(material) ){
                result.add(b);
            }
        }
        return result;
    }

}

interface Block {
    String getColor();
    String getMaterial();
}

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}