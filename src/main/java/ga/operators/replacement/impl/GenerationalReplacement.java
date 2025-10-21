package ga.operators.replacement.impl;

import ga.chromosome.Chromosome;
import ga.core.Population;
import ga.operators.replacement.ReplacementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerationalReplacement implements ReplacementStrategy {
    @Override
    public Population replace(Population currentPopulation, List<Chromosome> offspring, int populationSize) {
        List<Chromosome> newPopulation = new ArrayList<>(offspring);

        while (newPopulation.size() < populationSize) {
            newPopulation.add(offspring.get(new Random().nextInt(offspring.size())).clone());
        }

        if (newPopulation.size() > populationSize) {
            newPopulation = newPopulation.subList(0, populationSize);
        }

        return new Population(newPopulation);
    }
}
