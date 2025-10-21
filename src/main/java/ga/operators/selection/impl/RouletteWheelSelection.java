package ga.operators.selection.impl;

import ga.chromosome.Chromosome;
import ga.core.Population;
import ga.operators.selection.SelectionOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteWheelSelection implements SelectionOperator {
    @Override
    public List<Chromosome> select(Population population, int numberOfParents) {
        List<Chromosome> selected = new ArrayList<>();
        double totalFitness = population.getTotalFitness();

        double minFitness = population.getChromosomes().stream()
                .mapToDouble(Chromosome::getFitness)
                .min()
                .orElse(0.0);

        double offset = minFitness < 0 ? Math.abs(minFitness) + 1 : 0;
        double adjustedTotalFitness = totalFitness + offset * population.size();

        Random random = new Random();

        for (int i = 0; i < numberOfParents; i++) {
            double randomValue = random.nextDouble() * adjustedTotalFitness;
            double cumulative = 0.0;

            for (Chromosome chromosome : population.getChromosomes()) {
                cumulative += chromosome.getFitness() + offset;
                if (cumulative >= randomValue) {
                    selected.add(chromosome);
                    break;
                }
            }
        }

        return selected;
    }
}
