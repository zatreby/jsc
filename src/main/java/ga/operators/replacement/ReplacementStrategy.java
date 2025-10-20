package ga.operators.replacement;

import ga.chromosome.Chromosome;
import ga.core.Population;

import java.util.List;

public interface ReplacementStrategy {
    Population replace(Population currentPopulation, List<Chromosome> offspring, int populationSize);
}
