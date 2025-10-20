package ga.operators.crossover;

import ga.chromosome.Chromosome;

import java.util.List;

public interface CrossoverOperator {
    List<Chromosome> crossover(Chromosome parent1, Chromosome parent2);
}
