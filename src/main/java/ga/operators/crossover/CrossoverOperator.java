package ga.operators.crossover;

import ga.chromosome.Chromosome;
import ga.chromosome.ChromosomeType;

import java.util.List;

@FunctionalInterface
public interface CrossoverOperator {
    List<Chromosome> crossover(Chromosome parent1, Chromosome parent2, ChromosomeType type);
}
