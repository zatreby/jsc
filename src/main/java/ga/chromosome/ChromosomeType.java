package ga.chromosome;

import java.util.function.Supplier;

public enum ChromosomeType {
    BINARY(BinaryChromosome::new),
    INTEGER(null),
    FLOATING_POINT(null);

    private final Supplier<Chromosome> chromosome;

    ChromosomeType(Supplier<Chromosome> chromosome) {
        this.chromosome = chromosome;
    }

    public Chromosome get() {
        return chromosome.get();
    }
}
