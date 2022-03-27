package brickset;

import repository.Repository;
import java.util.Comparator;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns the number of LEGO sets with the tag specified.
     *
     * @param tag a LEGO set tag
     * @return the number of LEGO sets with the tag specified
     */
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }

    /**
     * Prints out the name of the Lego sets in alphabetical order
     */
    public void printLegoSetsOrdByName() {
        getAll().stream()
                .map(LegoSet::getName)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * Prints out the name of the Lego sets (in alphabetical order), which are below the piece limit specified
     *
     * @param limit an upper limit for the pieces
     */
    public void printLegoSetsWithPieceLimit(int limit) {
        getAll().stream()
                .filter(legoSet -> legoSet.getPieces() < limit && legoSet.getPieces() != 0)
                .map(LegoSet::getName)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * Prints out the packaging types
     */
    public void printPackagingTypes() {
        getAll().stream()
                .map(LegoSet::getPackagingType)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * Returns the sum of Lego Set pieces with the specified theme
     *
     * @param theme a Lego Set theme
     * @return sum of pieces with the specified theme
     */
    public long sumLegoPieces(String theme) {
        return getAll().stream()
                    .filter(legoSet -> legoSet.getTheme().equals(theme))
                    .mapToLong(LegoSet::getPieces)
                    .sum();
    }

    /**
     * Function for returning all the data of the largest Lego set with Star Wars theme
     *
     * @return all the data of the largest Lego Set with Star Wars theme
     */
    public brickset.LegoSet largestLegoSet() {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals("Star Wars"))
                .max(Comparator.comparingLong(LegoSet::getPieces))
                .get();
    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.countLegoSetsWithTag("Microscale"));

        System.out.println();

        //Calling First Method
        System.out.println("LEGO SETS ORDERED BY NAME:");
        repository.printLegoSetsOrdByName();

        System.out.println();

        //Calling Second Method
        System.out.println("LEGO SETS WITH THE UPPER PIECE LIMIT OF 40");
        repository.printLegoSetsWithPieceLimit(40);

        System.out.println();

        //Calling Third Method
        System.out.println("PACKAGING TYPES");
        repository.printPackagingTypes();

        System.out.println();

        //Calling Fourth Method
        String theme = "Duplo";
        System.out.println("AMOUNT OF PIECES IN " + theme + " THEMED LEGO SETS: " + repository.sumLegoPieces(theme));

        System.out.println();

        //Calling Fifth Method
        System.out.println("LARGEST LEGO SET: " + repository.largestLegoSet());
    }

}
