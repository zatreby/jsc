package ga.operators.crossover.impl;

import ga.chromosome.*;
import ga.operators.crossover.CrossoverOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SinglePointCrossover implements CrossoverOperator {
    @Override
    public List<Chromosome> crossover(Chromosome parent1, Chromosome parent2, ChromosomeType type) {
        int length = parent1.getLength();
        Random random = new Random();
        int point = random.nextInt(1,length - 1);

        List<Object> genes1 = parent1.getGenes();
        List<Object> genes2 = parent2.getGenes();

        List<Object> child1Genes = new ArrayList<>(genes1.subList(0, point));
        child1Genes.addAll(genes2.subList(point, genes2.size()));

        List<Object> child2Genes = new ArrayList<>(genes2.subList(0, point));
        child2Genes.addAll(genes1.subList(point, genes1.size()));

        Chromosome chromosome1 = type.get();
        chromosome1.setGenes(child1Genes);

        Chromosome chromosome2 = type.get();
        chromosome2.setGenes(child2Genes);

        return List.of(chromosome1, chromosome2);
    }
}
